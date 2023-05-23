package com.roubao.common.sensitive.strategy;

import cn.hutool.core.util.StrUtil;
import com.roubao.common.constants.StringConstant;

/**
 * 脱敏引擎
 *
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/3/15
 **/
public abstract class AbstractSensitiveStrategy {
    /**
     * 脱敏
     *
     * @param sourceStr 原字符串
     * @return 脱敏后的字符串
     */
    public String desensitize(String sourceStr) {
        if (StrUtil.isEmpty(sourceStr)) {
            return StrUtil.EMPTY;
        }
        return process(sourceStr);
    }

    /**
     * 脱敏操作实际执行方法
     *
     * @param sourceStr 原字符串
     * @return 脱敏后字符串
     */
    public abstract String process(String sourceStr);

    /**
     * 创建铭感替换符号
     *
     * @param length 长度
     * @return 铭感替换符
     */
    public String forAssembleSymbol(Integer length) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            result.append(this.symbol());
        }
        return result.toString();
    }

    /**
     * 铭感替换符
     *
     * @return 默认为: *
     */
    public String symbol() {
        return StringConstant.ASTERISK;
    }
}
