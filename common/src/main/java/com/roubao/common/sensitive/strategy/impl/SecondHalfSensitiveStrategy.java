package com.roubao.common.sensitive.strategy.impl;

import com.roubao.common.sensitive.strategy.AbstractSensitiveStrategy;

/**
 * 后半部分脱敏
 *
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/3/15
 **/
public class SecondHalfSensitiveStrategy extends AbstractSensitiveStrategy {
    @Override
    public String process(String sourceStr) {
        int i = sourceStr.length() / 2;
        String symbol = this.forAssembleSymbol(sourceStr.length() - i);
        return sourceStr.substring(0, i) + symbol;
    }
}
