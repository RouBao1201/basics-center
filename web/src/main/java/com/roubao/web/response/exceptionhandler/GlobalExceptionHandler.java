package com.roubao.web.response.exceptionhandler;

import com.roubao.common.exception.enums.ExceptionCode;
import com.roubao.common.exception.model.BaseException;
import com.roubao.common.exception.model.BaseRuntimeException;
import com.roubao.common.exception.model.ServiceException;
import com.roubao.web.response.dto.RespResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

/**
 * 全局异常处理
 *
 * @author SongYanBin
 * @copyright 2023-2099 SongYanBin All Rights Reserved.
 * @since 2023/4/4
 **/
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 空指针异常
     */
    @ExceptionHandler(NullPointerException.class)
    public RespResult<Object> nullPointerExceptionHandler(NullPointerException ex) {
        log.error("GlobalExceptionHandler ==> NullPointerException: {}", ex.getMessage(), ex);
        return RespResult.fail("空指针异常: " + ex.getMessage());
    }

    /**
     * 类型转换异常
     */
    @ExceptionHandler(ClassCastException.class)
    public RespResult<Object> classCastExceptionHandler(ClassCastException ex) {
        log.error("GlobalExceptionHandler ==> ClassCastException: {}", ex.getMessage(), ex);
        return RespResult.fail("类型转换异常: " + ex.getMessage());
    }

    /**
     * 文件未找到异常
     */
    @ExceptionHandler(FileNotFoundException.class)
    public RespResult<Object> fileNotFoundExceptionHandler(FileNotFoundException ex) {
        log.error("GlobalExceptionHandler ==> FileNotFoundException: {}", ex.getMessage(), ex);
        return RespResult.fail("文件未找到异常: " + ex.getMessage());
    }

    /**
     * 数字格式异常
     */
    @ExceptionHandler(NumberFormatException.class)
    public RespResult<Object> numberFormatExceptionHandler(NumberFormatException ex) {
        log.error("GlobalExceptionHandler ==> NumberFormatException: {}", ex.getMessage(), ex);
        return RespResult.fail("数字格式异常: " + ex.getMessage());
    }

    /**
     * 安全异常
     */
    @ExceptionHandler(SecurityException.class)
    public RespResult<Object> securityExceptionHandler(SecurityException ex) {
        log.error("GlobalExceptionHandler ==> SecurityException: {}", ex.getMessage(), ex);
        return RespResult.fail("安全异常: " + ex.getMessage());
    }

    /**
     * sql异常
     */
    @ExceptionHandler(SQLException.class)
    public RespResult<Object> sqlExceptionHandler(SQLException ex) {
        log.error("GlobalExceptionHandler ==> SQLException: {}", ex.getMessage(), ex);
        return RespResult.fail("SQL异常: " + ex.getMessage());
    }

    /**
     * 类型不存在异常
     */
    @ExceptionHandler(TypeNotPresentException.class)
    public RespResult<Object> typeNotPresentExceptionHandler(TypeNotPresentException ex) {
        log.error("GlobalExceptionHandler ==> TypeNotPresentException: {}", ex.getMessage(), ex);
        return RespResult.fail("类型不存在异常: " + ex.getMessage());
    }

    /**
     * IO异常
     */
    @ExceptionHandler(IOException.class)
    public RespResult<Object> ioExceptionHandler(IOException ex) {
        log.error("GlobalExceptionHandler ==> IOException: {}", ex.getMessage(), ex);
        return RespResult.fail("IO异常: " + ex.getMessage());
    }

    /**
     * 未知方法异常
     */
    @ExceptionHandler(NoSuchMethodException.class)
    public RespResult<Object> noSuchMethodExceptionHandler(NoSuchMethodException ex) {
        log.error("GlobalExceptionHandler ==> NoSuchMethodException: {}", ex.getMessage(), ex);
        return RespResult.fail("未知方法异常: " + ex.getMessage());
    }

    /**
     * 数组越界异常
     */
    @ExceptionHandler(IndexOutOfBoundsException.class)
    public RespResult<Object> indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException ex) {
        log.error("GlobalExceptionHandler ==> IndexOutOfBoundsException: {}", ex.getMessage(), ex);
        return RespResult.fail("数组越界异常: " + ex.getMessage());
    }

    /**
     * 无法注入bean异常
     */
    @ExceptionHandler(NoSuchBeanDefinitionException.class)
    public RespResult<Object> noSuchBeanDefinitionExceptionHandler(NoSuchBeanDefinitionException ex) {
        log.error("GlobalExceptionHandler ==> NoSuchBeanDefinitionException: {}", ex.getMessage(), ex);
        return RespResult.fail("无法注入bean异常: " + ex.getMessage());
    }

    /**
     * Http消息不可读异常
     */
    @ExceptionHandler({
        HttpMessageNotReadableException.class
    })
    public RespResult<Object> httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException ex) {
        log.error("GlobalExceptionHandler ==> HttpMessageNotReadableException: {}", ex.getMessage(), ex);
        return RespResult.fail("Http消息不可读: " + ex.getMessage());
    }

    /**
     * 400错误
     */
    @ExceptionHandler({
        TypeMismatchException.class
    })
    public RespResult<Object> typeMismatchExceptionHandler(TypeMismatchException ex) {
        log.error("GlobalExceptionHandler ==> TypeMismatchException: {}", ex.getMessage(), ex);
        return RespResult.fail(400, "服务器异常: " + ex.getMessage());
    }

    /**
     * 500错误
     */
    @ExceptionHandler({
        ConversionNotSupportedException.class, HttpMessageNotWritableException.class
    })
    public RespResult<Object> runtimeExceptionHandler(RuntimeException ex) {
        log.error("GlobalExceptionHandler ==> RuntimeException: {}", ex.getMessage());
        return RespResult.fail("服务器异常: " + ex.getMessage());
    }

    /**
     * 栈溢出
     */
    @ExceptionHandler(StackOverflowError.class)
    public RespResult<Object> stackOverflowExceptionHandler(StackOverflowError ex) {
        log.error("GlobalExceptionHandler ==> StackOverflowError: {}", ex.getMessage(), ex);
        return RespResult.fail("栈溢出异常: " + ex.getMessage());
    }

    /**
     * 业务异常捕获
     *
     * @param ex 异常
     * @return RespResult
     */
    @ExceptionHandler(ServiceException.class)
    public RespResult<Object> serviceExceptionHandler(ServiceException ex) {
        log.error("GlobalExceptionHandler ==> ServiceException: {}", ex.getMessage(), ex);
        return RespResult.fail(ExceptionCode.SERVICE_EXCEPTION.getCode(), "业务异常: " + ex.getMessage());
    }

    @ExceptionHandler(BaseException.class)
    public RespResult<Object> baseExceptionHandler(BaseException ex) {
        log.error("GlobalExceptionHandler ==> BaseException: {}", ex.getMessage(), ex);
        return RespResult.fail(ExceptionCode.SERVICE_EXCEPTION.getCode(), "基础异常: " + ex.getMessage());
    }

    @ExceptionHandler(BaseRuntimeException.class)
    public RespResult<Object> baseExceptionHandler(BaseRuntimeException ex) {
        log.error("GlobalExceptionHandler ==> BaseRuntimeException: {}", ex.getMessage(), ex);
        return RespResult.fail(ExceptionCode.SERVICE_EXCEPTION.getCode(), "基础运行异常: " + ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public RespResult<Object> exceptionHandler(Exception ex) {
        log.error("GlobalExceptionHandler ==> Exception: {}", ex.getMessage(), ex);
        return RespResult.fail(ExceptionCode.SERVICE_EXCEPTION.getCode(), "其他异常: " + ex.getMessage());
    }
}
