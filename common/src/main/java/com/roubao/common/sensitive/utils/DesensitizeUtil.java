package com.roubao.common.sensitive.utils;


import com.roubao.common.sensitive.enums.DesensitizeStrategy;
import com.roubao.common.sensitive.strategy.AbstractSensitiveStrategy;

/**
 * 脱敏工具类
 *
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/3/15
 **/
public class DesensitizeUtil {
    /**
     * 脱敏
     *
     * @param sensitiveMode 脱敏方式
     * @param sourceStr     原字符串
     * @return 脱敏后字符串
     */
    public static String desensitize(DesensitizeStrategy sensitiveMode, String sourceStr) {
        AbstractSensitiveStrategy strategyEngine = sensitiveMode.getStrategy();
        return strategyEngine.desensitize(sourceStr);
    }

    /**
     * 脱敏（采用默认脱敏公式：后半部数据脱敏）
     *
     * @param sourceStr 原字符串
     * @return 脱敏后字符串
     */
    public static String desensitize(String sourceStr) {
        return desensitize(DesensitizeStrategy.DEFAULT, sourceStr);
    }

    private DesensitizeUtil() {

    }
}
