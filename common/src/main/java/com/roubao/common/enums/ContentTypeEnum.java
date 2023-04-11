package com.roubao.common.enums;

/**
 * 请求ContentType
 * 
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/4/7
 **/
public enum ContentTypeEnum {
    /**
     * 表单
     */
    APPLICATION_X_WWW_FORM_URLENCODED("application/x-www-form-urlencoded"),
    /**
     * 带有file对象表单
     */
    MULTIPART_FORM_DATA("multipart/form-data"),
    /**
     * 请求体JSON
     */
    APPLICATION_JSON("application/json"),
    /**
     * 传统的ajax请求提交
     */
    TEXT_PLAIN("text/plain"),
    /**
     * 使用HTTP作为传输协议, XML作为编码方式的远程调用规范
     */
    TEXT_XML("text/xml");

    /**
     * 校验是否有限类型
     * 
     * @param contentType contentType
     * @return boolean
     */
    public static boolean validContentType(String contentType) {
        if (contentType == null || "".equals(contentType)) {
            return false;
        }
        for (ContentTypeEnum value : ContentTypeEnum.values()) {
            if (contentType.equals(value.getType())) {
                return true;
            }
        }
        return false;
    }

    private final String type;

    ContentTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
