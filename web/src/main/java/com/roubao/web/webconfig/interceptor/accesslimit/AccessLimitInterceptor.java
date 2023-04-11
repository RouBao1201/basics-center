package com.roubao.web.webconfig.interceptor.accesslimit;

import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.google.common.util.concurrent.AtomicLongMap;
import com.roubao.common.exception.handler.ExceptionPublisher;

import cn.hutool.core.util.ObjUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 接口防刷拦截器
 * 
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/4/7
 **/
@Slf4j
public class AccessLimitInterceptor implements HandlerInterceptor, Ordered {
    private static final String PREFIX_KEY = "ACCESS_LIMIT_INTERFACE_";

    /**
     * 接口访问次数记录
     */
    private static final AtomicLongMap<String> ACCESS_COUNT_MAP = AtomicLongMap.create();

    /**
     * 接口访问锁定时间
     */
    private static final AtomicLongMap<String> LOCK_TIME_MAP = AtomicLongMap.create();

    /**
     * 接口访问第一次访问时间
     */
    private static final AtomicLongMap<String> API_FIRST_ACCESS_TIME_MAP = AtomicLongMap.create();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {
        if (handler instanceof HandlerMethod) {
            // 判断类或者方法上是否有防刷注解（方法注解优先级大于类）
            AccessSniper annotation = null;
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            annotation = handlerMethod.getMethodAnnotation(AccessSniper.class);
            if (ObjUtil.isEmpty(annotation)) {
                annotation = handlerMethod.getMethod().getDeclaringClass().getAnnotation(AccessSniper.class);
            }
            if (ObjUtil.isEmpty(annotation)) {
                return true;
            }
            // 进行接口防刷校验
            String key = PREFIX_KEY + handlerMethod.getMethod().getDeclaringClass().getName() + "_"
                + handlerMethod.getMethod().getName();
            long currentTime = System.currentTimeMillis();
            AccessSniper finalAnnotation = annotation;

            // 判断是否已达到接口访问限制次数
            long lockFlag = LOCK_TIME_MAP.updateAndGet(key, (oldVal) -> {
                if (oldVal != 0) {
                    long gapTime = currentTime - oldVal;
                    // 未达到限制访问时间,继续锁定接口访问权限
                    if (gapTime < finalAnnotation.lockTime()) {
                        ACCESS_COUNT_MAP.incrementAndGet(key);
                        log.error(
                            "AccessSniper INTERFACE KEY:" + key + " was within the access limit time. LimitConfig:["
                                + finalAnnotation.accessTimes() + " times in " + finalAnnotation.seconds()
                                + " seconds, lockTime: " + finalAnnotation.lockTime() + "s]. Please try again later.");
                        return oldVal;
                    }
                    else {
                        // 达到限制访问时间, 1.解除接口访问限制, 2.设置接口访问次数为0, 3.设置首次访问接口时间
                        ACCESS_COUNT_MAP.updateAndGet(key, (val) -> 1L);
                        API_FIRST_ACCESS_TIME_MAP.updateAndGet(key, (val) -> currentTime);
                        return 0L;
                    }
                }
                else {
                    AtomicLong lockTimeVal = new AtomicLong(0L);
                    API_FIRST_ACCESS_TIME_MAP.updateAndGet(key, (firstVal) -> {
                        // 已不在限制访问时间段内,重新计数
                        if (firstVal == 0 || (currentTime - firstVal) > finalAnnotation.seconds()) {
                            ACCESS_COUNT_MAP.updateAndGet(key, (old) -> 1L);
                            return currentTime;
                        }
                        else {
                            // 超过访问次数限制,锁定接口不允许访问
                            long currentCount = ACCESS_COUNT_MAP.getAndIncrement(key);
                            if (currentCount >= finalAnnotation.accessTimes()) {
                                lockTimeVal.set(currentTime);
                            }
                            return firstVal;
                        }
                    });
                    return lockTimeVal.get();
                }
            });
            if (lockFlag != 0) {
                log.error("Visit too often. INTERFACE KEY:[" + key + "] is restricted.");
                ExceptionPublisher
                    .publishServiceException("Visit too often. INTERFACE KEY:[" + key + "] is restricted.");
            }
        }
        return true;
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
