package com.roubao.web.webconfig.interceptor.requestinfolog;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;

/**
 * 自定义拦截器（请求信息拦截器）
 *
 * @author SongYanBin
 * @copyright 2023-2099 SongYanBin All Rights Reserved.
 * @since 2023/4/4
 **/
@Slf4j
public class RequestInfoInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            StringBuilder sb = new StringBuilder();
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Enumeration<String> parameterNames = request.getParameterNames();
            JSONObject data = new JSONObject();
            while (parameterNames.hasMoreElements()) {
                String name = parameterNames.nextElement();
                String value = request.getParameter(name);
                data.put(name, value);
            }
            sb.append(System.lineSeparator())
                    .append("|| ================================================================================================").append(System.lineSeparator())
                    .append("|| RemoteAddrPort: ").append(request.getRemoteAddr()).append(":").append(request.getRemotePort()).append(System.lineSeparator())
                    .append("|| ------------------------------------------------------------------------------------------------").append(System.lineSeparator())
                    .append("|| Controller : ").append(handlerMethod.getBean().getClass().getName()).append(System.lineSeparator())
                    .append("|| ServerName : ").append(request.getServerName()).append(System.lineSeparator())
                    .append("|| ContentType: ").append(request.getContentType()).append(System.lineSeparator())
                    .append("|| Method     : ").append(handlerMethod.getMethod().getName()).append(System.lineSeparator())
                    .append("|| Params     : ").append(data).append(System.lineSeparator())
                    .append("|| URL        : ").append(request.getRequestURL()).append(System.lineSeparator())
                    .append("|| URI        : ").append(request.getRequestURI()).append(System.lineSeparator())
                    .append("|| ------------------------------------------------------------------------------------------------").append(System.lineSeparator())
                    .append("|| ================================================================================================");
            log.info(String.valueOf(sb));   
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
