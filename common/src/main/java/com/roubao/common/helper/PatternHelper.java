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

    /**
     * 负浮点数
     */
    public static final Pattern PATTERN_NEGATIVE_FLOAT = Pattern.compile(RegexConstant.REGEX_NEGATIVE_FLOAT);

    /**
     * 正浮点数
     */
    public static final Pattern PATTERN_POSITIVE_FLOAT = Pattern.compile(RegexConstant.REGEX_POSITIVE_FLOAT);

    /**
     * 非正整数
     */
    public static final Pattern PATTERN_NOT_POSITIVE_INTEGER = Pattern.compile(RegexConstant.REGEX_NOT_POSITIVE_INTEGER);

    /**
     * 非负整数
     */
    public static final Pattern PATTERN_NOT_NEGATIVE_INTEGER = Pattern.compile(RegexConstant.REGEX_NOT_NEGATIVE_INTEGER);

    /**
     * 整数
     */
    public static final Pattern PATTERN_INTEGER = Pattern.compile(RegexConstant.REGEX_INTEGER);

    /**
     * 负整数
     */
    public static final Pattern PATTERN_NEGATIVE_INTEGER = Pattern.compile(RegexConstant.REGEX_NEGATIVE_INTEGER);

    /**
     * 正整数
     */
    public static final Pattern PATTERN_POSITIVE_INTEGER = Pattern.compile(RegexConstant.REGEX_POSITIVE_INTEGER);

    /**
     * 中国邮政编码
     */
    public static final Pattern PATTERN_ZIP_CODE = Pattern.compile(RegexConstant.REGEX_ZIP_CODE);

    /**
     * 空白行
     */
    public static final Pattern PATTERN_BLANK_LINE = Pattern.compile(RegexConstant.REGEX_BLANK_LINE);

    private PatternHelper() {

    }
}
