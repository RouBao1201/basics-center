package com.roubao.common.sensitive.strategy.impl;

import com.roubao.common.sensitive.strategy.AbstractSensitiveStrategy;

/**
 * 前半部分内容脱敏
 *
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/3/15
 **/
public class FirstHalfSensitiveStrategy extends AbstractSensitiveStrategy {
    @Override
    public String process(String sourceStr) {
        int i = sourceStr.length() / 2;
        String symbol = this.forAssembleSymbol(sourceStr.length() - i);
        return symbol + sourceStr.substring(i);
    }
}
