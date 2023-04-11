package com.roubao.web.common.logrecord;

import java.lang.reflect.Method;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.roubao.common.thread.bean.ThreadPoolHandler;

import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.ReflectUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 日志记录切面
 * 
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/4/6
 **/
@Slf4j
@Component
@Aspect
public class LogRecordAspect {

    private final DefaultListableBeanFactory defaultListableBeanFactory;

    private final ThreadPoolHandler threadPoolHandler;

    public LogRecordAspect(DefaultListableBeanFactory defaultListableBeanFactory, ThreadPoolHandler threadPoolHandler) {
        this.defaultListableBeanFactory = defaultListableBeanFactory;
        this.threadPoolHandler = threadPoolHandler;
    }

    @Pointcut("@annotation(com.roubao.web.common.logrecord.LogRecord)")
    public void pointCut() {

    }

    @Around("pointCut()")
    public Object aroundAspect(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method method = methodSignature.getMethod();
        LogRecord annotation = method.getAnnotation(LogRecord.class);

        // 获取请求参数 组装日志记录传递参数
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        assert sra != null;
        HttpServletRequest request = sra.getRequest();
        LogRecordDTO<Object> logRecordDto = new LogRecordDTO<>();
        logRecordDto.setParam(ObjUtil.isNotEmpty(pjp.getArgs()) ? pjp.getArgs()[0] : null);
        logRecordDto.setMethodName(method.getName());
        logRecordDto.setUrl(request.getRequestURL().toString());
        logRecordDto.setDescription(annotation.description());
        logRecordDto.setRequestType(request.getMethod());
        logRecordDto.setAnnotation(annotation);
        logRecordDto.setClassName(pjp.getTarget().getClass().getName());

        // 执行标志位 默认日志记录在方法执行前执行（前置通知）,其余在方法执行后执行（后置通知）
        Class<? extends LogRecordStrategy> aClass = annotation.strategyClass();
        String fullClassName = aClass.getName();
        int classNameStartIndex = fullClassName.lastIndexOf(".") + 1;
        String className = fullClassName.substring(classNameStartIndex);
        String beanName = className.substring(0, 1).toLowerCase(Locale.ROOT) + className.substring(1);

        boolean recordIndexFlag = "com.roubao.web.common.logrecord.DefaultLogRecordStrategy".equals(fullClassName);
        Object obj = null;
        if (recordIndexFlag) {
            ReflectUtil.invoke(ReflectUtil.newInstance(aClass), "afterRecord", logRecordDto);
        }
        obj = pjp.proceed();
        if (!recordIndexFlag) {
            // 自定义日志逻辑判断执行方式（异步/同步）
            if (annotation.runMode() == LogRecord.RunMode.SYNC) {
                executeBeanMethod(aClass, logRecordDto, beanName);
            }
            else {
                threadPoolHandler.execute(() -> {
                    executeBeanMethod(aClass, logRecordDto, beanName);
                });
            }
        }
        return obj;
    }

    public void executeBeanMethod(Class<? extends LogRecordStrategy> aClass, LogRecordDTO<Object> logRecordDto,
        String beanName) {
        LogRecordStrategy bean = defaultListableBeanFactory.getBean(beanName, aClass);
        bean.afterRecord(logRecordDto);
    }
}
