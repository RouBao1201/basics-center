package com.roubao.web.response.unifyresp;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.roubao.web.response.dto.RespResult;
import com.roubao.web.response.utils.RespHelper;

/**
 * 统一响应配置
 * 
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/4/19
 **/
@ControllerAdvice
@EnableConfigurationProperties(IUnifiedResponseProperties.class)
public class UnifiedResponseConfiguration implements ResponseBodyAdvice<Object> {

    private final IUnifiedResponseProperties iUnifiedResponseProperties;

    public UnifiedResponseConfiguration(IUnifiedResponseProperties iUnifiedResponseProperties) {
        this.iUnifiedResponseProperties = iUnifiedResponseProperties;
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object obj, MethodParameter methodParameter, MediaType mediaType,
        Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest,
        ServerHttpResponse serverHttpResponse) {
        // 判断类和方法上是否存在跳过统一响应注解
        SkipUnifyResp skipUnifyRespForMethod = methodParameter.getMethodAnnotation(SkipUnifyResp.class);
        SkipUnifyResp skipUnifyRespForClass = methodParameter.getDeclaringClass().getAnnotation(SkipUnifyResp.class);
        if (skipUnifyRespForMethod != null || skipUnifyRespForClass != null) {
            return obj;
        }
        // 原本方法已使用统一响应则无需转换
        if (obj instanceof RespResult) {
            return obj;
        }

        EnableCustomUnifiedResponse.RunMode runMode = iUnifiedResponseProperties.getRunMode();
        if (runMode == EnableCustomUnifiedResponse.RunMode.AUTO) {
            return RespHelper.success(obj);
        }
        else {
            UnifyResp unifyRespForMethod = methodParameter.getMethodAnnotation(UnifyResp.class);
            UnifyResp unifyRespForClass = methodParameter.getDeclaringClass().getAnnotation(UnifyResp.class);
            if (unifyRespForMethod != null || unifyRespForClass != null) {
                return RespHelper.success(obj);
            }
        }
        return obj;
    }
}
