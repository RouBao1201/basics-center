package com.roubao.common.sensitive.strategy.impl;

import com.roubao.common.sensitive.strategy.AbstractSensitiveStrategy;

/**
 * 地址脱敏（保留前四位）
 *
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/3/15
 **/
public class AddressSensitiveStrategy extends AbstractSensitiveStrategy {
    @Override
    public String process(String sourceStr) {
        return sourceStr.substring(0, 4) + this.forAssembleSymbol(8);
    }
}
