package com.roubao.common.utils;

import java.util.Locale;

import com.google.common.base.CaseFormat;

import lombok.extern.slf4j.Slf4j;

/**
 * 字符串驼峰转换工具
 * 
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/2/27
 **/
@Slf4j
public class CaseUtil {
    /**
     * 小横线转
     * 
     * @param str （hello-world）
     * @param camelEnum camelEnum
     * @return String
     */
    public static String lowerHyphenTo(String str, CaseType camelEnum) {
        if (camelEnum == CaseType.UPPER_HYPHEN) {
            return str.toUpperCase(Locale.ROOT);
        }
        CaseFormat caseFormat = convertFormat(camelEnum);
        if (camelEnum == CaseType.UPPER_UNDERSCORE) {
            return CaseFormat.LOWER_HYPHEN.to(caseFormat, str).toUpperCase(Locale.ROOT);
        }
        return CaseFormat.LOWER_HYPHEN.to(caseFormat, str);
    }

    /**
     * 大横线转
     * 
     * @param str （HELLO-WORlD）
     * @param camelEnum camelEnum
     * @return String
     */
    public static String upperHyphenTo(String str, CaseType camelEnum) {
        return lowerHyphenTo(str.toLowerCase(Locale.ROOT), camelEnum);
    }

    /**
     * 小驼峰转
     * 
     * @param str （helloWorld）
     * @param camelEnum camelEnum
     * @return String
     */
    public static String lowerCamelTo(String str, CaseType camelEnum) {
        CaseFormat caseFormat = convertFormat(camelEnum);
        if (camelEnum == CaseType.UPPER_HYPHEN || camelEnum == CaseType.UPPER_UNDERSCORE) {
            return CaseFormat.LOWER_CAMEL.to(caseFormat, str).toUpperCase(Locale.ROOT);
        }
        return CaseFormat.LOWER_CAMEL.to(caseFormat, str);
    }

    /**
     * 大驼峰转
     * 
     * @param str （HelloWorld）
     * @param camelEnum camelEnum
     * @return String
     */
    public static String upperCamelTo(String str, CaseType camelEnum) {
        CaseFormat caseFormat = convertFormat(camelEnum);
        if (camelEnum == CaseType.UPPER_HYPHEN || camelEnum == CaseType.UPPER_UNDERSCORE) {
            return CaseFormat.UPPER_CAMEL.to(caseFormat, str).toUpperCase(Locale.ROOT);
        }
        return CaseFormat.UPPER_CAMEL.to(caseFormat, str);
    }

    /**
     * 小下划线转
     * 
     * @param str （hello_world）
     * @param camelEnum camelEnum
     * @return String
     */
    public static String lowerUnderscoreTo(String str, CaseType camelEnum) {
        if (camelEnum == CaseType.UPPER_UNDERSCORE) {
            return str.toUpperCase(Locale.ROOT);
        }
        CaseFormat caseFormat = convertFormat(camelEnum);
        if (camelEnum == CaseType.UPPER_HYPHEN) {
            return CaseFormat.LOWER_UNDERSCORE.to(caseFormat, str).toUpperCase(Locale.ROOT);
        }
        return CaseFormat.LOWER_UNDERSCORE.to(caseFormat, str);
    }

    /**
     * 大下划线转
     * 
     * @param str （HELLO_WORLD）
     * @param camelEnum camelEnum
     * @return String
     */
    public static String upperUnderscoreTo(String str, CaseType camelEnum) {
        return lowerUnderscoreTo(str.toLowerCase(Locale.ROOT), camelEnum);
    }

    private static CaseFormat convertFormat(CaseType camelEnum) {
        CaseFormat caseFormat = null;
        switch (camelEnum) {
            case LOWER_CAMEL:
                caseFormat = CaseFormat.LOWER_CAMEL;
                break;
            case UPPER_CAMEL:
                caseFormat = CaseFormat.UPPER_CAMEL;
                break;
            case LOWER_HYPHEN:
            case UPPER_HYPHEN:
                caseFormat = CaseFormat.LOWER_HYPHEN;
                break;
            case LOWER_UNDERSCORE:
            case UPPER_UNDERSCORE:
                caseFormat = CaseFormat.LOWER_UNDERSCORE;
                break;
            default:
                throw new RuntimeException("Unknown camelEnum[" + camelEnum + "]. Please check.");
        }
        return caseFormat;
    }

    public enum CaseType {
        /**
         * 小驼峰（helloWorld）
         */
        LOWER_CAMEL,
        /**
         * 大驼峰（HelloWorld）
         */
        UPPER_CAMEL,
        /**
         * 小横线格式（hello-world）
         */
        LOWER_HYPHEN,
        /**
         * 大横线格式（HELLO-WORLD）
         */
        UPPER_HYPHEN,
        /**
         * 小下划线格式（hello_world）
         */
        LOWER_UNDERSCORE,
        /**
         * 大下划线格式（HELLO_WORLD）
         */
        UPPER_UNDERSCORE;
    }

    private CaseUtil() {

    }
}
