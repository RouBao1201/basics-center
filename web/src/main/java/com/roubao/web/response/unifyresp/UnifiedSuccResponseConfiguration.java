package com.roubao.web.response.unifyresp;

import com.roubao.web.response.enums.RespCode;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.roubao.web.response.dto.RespResult;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 统一成功响应配置
 *
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/4/19
 **/
@Slf4j
@ControllerAdvice
@EnableConfigurationProperties(IUnifiedResponseProperties.class)
public class UnifiedSuccResponseConfiguration implements ResponseBodyAdvice<Object> {
    private final IUnifiedResponseProperties iUnifiedResponseProperties;

    public UnifiedSuccResponseConfiguration(IUnifiedResponseProperties iUnifiedResponseProperties) {
        log.info("UnifiedSuccResponseConfiguration ==> IUnifiedResponseProperties:[{}].", iUnifiedResponseProperties);
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
        // swagger相关api放行
        String uriStr = serverHttpRequest.getURI().toString();
        for (String excludeUriContain : iUnifiedResponseProperties.getExcludeUriContains()) {
            if (uriStr.contains(excludeUriContain)) {
                return obj;
            }
        }

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

        EnableCustomUnifiedSuccResponse.RunMode runMode = iUnifiedResponseProperties.getRunMode();
        if (runMode == EnableCustomUnifiedSuccResponse.RunMode.AUTO) {
            return convertRespObjByType(RespCode.SUCCESS_200.getCode(), RespCode.SUCCESS_200.getMessage(), obj);
        }
        else {
            UnifySuccResp unifyRespForMethod = methodParameter.getMethodAnnotation(UnifySuccResp.class);
            if (unifyRespForMethod == null) {
                UnifySuccResp unifyRespForClass = methodParameter.getDeclaringClass()
                    .getAnnotation(UnifySuccResp.class);
                if (unifyRespForClass == null) {
                    return obj;
                }
                else {
                    return convertRespObjByType(unifyRespForClass.code(), unifyRespForClass.message(), obj);
                }
            }
            else {
                return convertRespObjByType(unifyRespForMethod.code(), unifyRespForMethod.message(), obj);
            }
        }
    }

    /**
     * 类型转换（当原接口响应类型未String时,封装统一响应结果会导致类型转换报错）
     *
     * @param code 响应编码
     * @param message 响应信息
     * @param obj 响应数据
     * @return Object
     */
    private Object convertRespObjByType(Integer code, String message, Object obj) {
        if (obj == null || obj instanceof String) {
            return JSONUtil.toJsonStr(RespResult.success(code, message, obj));
        }
        return RespResult.success(code, message, obj);
    }
}
