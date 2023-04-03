package com.roubao.common.sensitive.strategy.impl;

import com.roubao.common.sensitive.strategy.AbstractSensitiveStrategy;

/**
 * 身份证脱敏（保留前面3位和后面4位）
 *
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/3/15
 **/
public class IdCardSensitiveStrategy extends AbstractSensitiveStrategy {
    @Override
    public String process(String sourceStr) {
        // return sourceStr.replaceAll("(?<=\\w{3})\\w(?=\\w{4})", this.placeholder());
        String prefix = sourceStr.substring(0, 3);
        String suffix = sourceStr.substring(sourceStr.length() - 4);
        String symbol = this.forAssembleSymbol(sourceStr.length() - 7);
        return prefix + symbol + suffix;
    }
}
