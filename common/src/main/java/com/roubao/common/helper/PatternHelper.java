package com.roubao.common.helper;

import com.roubao.common.constants.RegexConstant;

import java.util.regex.Pattern;

/**
 * 正则助手
 *
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/5/23
 **/
public class PatternHelper {
    /**
     * 是否包含中文
     */
    public static final Pattern PATTERN_CONTAIN_CHINESE = Pattern.compile(RegexConstant.REGEX_CONTAIN_CHINESE);

    private PatternHelper() {

    }
}
