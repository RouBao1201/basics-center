package com.roubao.common.pojo.dto;


import java.util.Objects;

/**
 * 结果实体类（用于校验判断）
 *
 * @author SongYanBin
 * @copyright 2023-2099 SongYanBin All Rights Reserved.
 * @since 2023/10/30
 **/
public class IResult {

    /**
     * 成功默认编码
     */
    private static final Integer DEFAULT_OK_CODE = 1;

    /**
     * 成功默认信息
     */
    private static final String DEFAULT_OK_MESSAGE = "ok";

    /**
     * 失败默认编码
     */
    private static final Integer DEFAULT_NO_CODE = 0;

    /**
     * 失败默认信息
     */
    private static final String DEFAULT_NO_MESSAGE = "no";

    /**
     * 结果编码
     */
    private Integer code;

    /**
     * 结果信息
     */
    private StringBuilder message;

    /**
     * 成功对象构造
     *
     * @return IResult
     */
    public static IResult buildOk() {
        IResult iResult = new IResult();
        iResult.setCode(DEFAULT_OK_CODE);
        StringBuilder msgStrBuilder = new StringBuilder(DEFAULT_OK_MESSAGE);
        iResult.setMessage(msgStrBuilder);
        return iResult;
    }

    /**
     * 失败对象构造
     *
     * @return IResult
     */
    public static IResult buildNo() {
        IResult iResult = new IResult();
        iResult.setCode(DEFAULT_NO_CODE);
        StringBuilder msgStrBuilder = new StringBuilder(DEFAULT_NO_MESSAGE);
        iResult.setMessage(msgStrBuilder);
        return iResult;
    }

    /**
     * 转换为成功对象
     *
     * @return IResult
     */
    public IResult transOk() {
        if (this.isNo()) {
            this.code = DEFAULT_OK_CODE;
            this.message.setLength(0);
            this.message.append(DEFAULT_OK_MESSAGE);
        }
        return this;
    }

    /**
     * 转换为成功对象
     *
     * @param message 结果信息（重置）
     * @return IResult
     */
    public IResult transOk(String message) {
        if (this.isNo()) {
            this.code = DEFAULT_OK_CODE;
            if (message != null && !message.isEmpty()) {
                this.message.setLength(0);
                this.message.append(message);
            }
        }
        return this;
    }

    /**
     * 转换为失败对象
     *
     * @return IResult
     */
    public IResult transNo() {
        if (this.isOk()) {
            this.code = DEFAULT_NO_CODE;
            this.message.setLength(0);
            this.message.append(DEFAULT_NO_MESSAGE);
        }
        return this;
    }

    /**
     * 切换状态为失败
     *
     * @param message 结果信息（重置）
     * @return IResult
     */
    public IResult transNo(String message) {
        if (this.isOk()) {
            this.code = DEFAULT_NO_CODE;
            if (message != null && !message.isEmpty()) {
                this.message.setLength(0);
                this.message.append(message);
            }
        }
        return this;
    }

    /**
     * 判断是否成功
     *
     * @return boolean
     */
    public boolean isOk() {
        return Objects.equals(this.code, DEFAULT_OK_CODE);
    }

    /**
     * 判断是否失败
     *
     * @return boolean
     */
    public boolean isNo() {
        return Objects.equals(this.code, DEFAULT_NO_CODE);
    }

    /**
     * 追加结果信息
     *
     * @param message message
     */
    public void appendMessage(String message) {
        this.message.append(message);
    }

    /**
     * 重置结果信息
     *
     * @param message message
     */
    public void resetMessage(String message) {
        this.message.setLength(0);
        this.message.append(message);
    }

    /**
     * 获取结果信息（字符串）
     *
     * @return String
     */
    public String getStrMessage() {
        return String.valueOf(getMessage());
    }

    private IResult() {

    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public StringBuilder getMessage() {
        return message;
    }

    public void setMessage(StringBuilder message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "IResult{" + "code=" + code + ", message=" + message + '}';
    }
}
