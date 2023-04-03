package com.roubao.common.sensitive.strategy.impl;

import com.roubao.common.sensitive.strategy.AbstractSensitiveStrategy;

/**
 * 名字脱敏（保留第一位）
 *
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/3/15
 **/
public class NameSensitiveStrategy extends AbstractSensitiveStrategy {
    @Override
    public String process(String sourceStr) {
        return sourceStr.charAt(0) + this.forAssembleSymbol(sourceStr.length() - 1);
    }
}
