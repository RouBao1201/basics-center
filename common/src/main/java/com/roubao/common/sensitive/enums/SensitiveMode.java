package com.roubao.common.sensitive.enums;

import com.roubao.common.sensitive.strategy.AbstractSensitiveStrategy;
import com.roubao.common.sensitive.strategy.impl.AddressSensitiveStrategy;
import com.roubao.common.sensitive.strategy.impl.EmailSensitiveStrategy;
import com.roubao.common.sensitive.strategy.impl.FirstHalfSensitiveStrategy;
import com.roubao.common.sensitive.strategy.impl.FullSensitiveStrategy;
import com.roubao.common.sensitive.strategy.impl.IdCardSensitiveStrategy;
import com.roubao.common.sensitive.strategy.impl.NameSensitiveStrategy;
import com.roubao.common.sensitive.strategy.impl.PhoneSensitiveStrategy;
import com.roubao.common.sensitive.strategy.impl.SecondHalfSensitiveStrategy;

/**
 * 脱敏模式枚举
 *
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/3/15
 **/
public enum SensitiveMode {
    /**
     * 默认脱敏公式（全脱敏）
     */
    DEFAULT(new FullSensitiveStrategy()),

    /**
     * 电话号码脱敏公式（保留前3位和后4位）
     */
    PHONE(new PhoneSensitiveStrategy()),

    /**
     * 邮箱脱敏策略（保留前1位和@符号后）
     */
    EMAIL(new EmailSensitiveStrategy()),

    /**
     * 身份证脱敏策略（保留前3位和后4位）
     */
    ID_CARD(new IdCardSensitiveStrategy()),

    /**
     * 前半部分脱敏
     */
    FIRST_HALF(new FirstHalfSensitiveStrategy()),

    /**
     * 后半部分脱敏
     */
    SECOND_HALF(new SecondHalfSensitiveStrategy()),

    /**
     * 全脱敏
     */
    FULL(new FullSensitiveStrategy()),

    /**
     * 名字脱敏（保留第一位）
     */
    NAME(new NameSensitiveStrategy()),

    /**
     * 地址脱敏（保留前四位）
     */
    ADDRESS(new AddressSensitiveStrategy());

    private final AbstractSensitiveStrategy strategy;

    SensitiveMode(AbstractSensitiveStrategy strategy) {
        this.strategy = strategy;
    }

    public AbstractSensitiveStrategy getStrategy() {
        return strategy;
    }
}
