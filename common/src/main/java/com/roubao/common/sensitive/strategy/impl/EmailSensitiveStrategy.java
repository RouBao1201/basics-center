package com.roubao.common.sensitive.strategy.impl;

import com.roubao.common.constants.StringConstant;
import com.roubao.common.sensitive.strategy.AbstractSensitiveStrategy;

/**
 * 邮箱脱敏（保留前面1位和@符号后数据）
 *
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/3/15
 **/
public class EmailSensitiveStrategy extends AbstractSensitiveStrategy {
    @Override
    public String process(String sourceStr) {
        int i = sourceStr.indexOf(StringConstant.AT);
        String atSubStr = sourceStr.substring(i);
        // sourceStr.replaceAll("(^\\w)[^@]*(@.*$)", "$1****$2")
        String symbol = this.forAssembleSymbol(sourceStr.length() - atSubStr.length() - 1);
        return sourceStr.charAt(0) + symbol + sourceStr.substring(i);
    }
}
