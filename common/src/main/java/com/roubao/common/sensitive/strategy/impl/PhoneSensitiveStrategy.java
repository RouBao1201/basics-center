package com.roubao.common.sensitive.strategy.impl;

import com.roubao.common.constants.NumberConst;
import com.roubao.common.sensitive.strategy.AbstractSensitiveStrategy;

/**
 * 电话号码脱敏（保留前面3位和后面4位）
 *
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/3/15
 **/
public class PhoneSensitiveStrategy extends AbstractSensitiveStrategy {

    @Override
    public String process(String sourceStr) {
        if (sourceStr.length() == NumberConst.ELEVEN) {
            return sourceStr.substring(0, 3) + this.forAssembleSymbol(4) + sourceStr.substring(7);
            // return sourceStr.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        }
        return sourceStr;
    }
}
