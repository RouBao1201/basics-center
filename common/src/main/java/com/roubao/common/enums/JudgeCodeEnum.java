package com.roubao.common.enums;

/**
 * 判断枚举
 *
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/3/16
 **/
public enum JudgeCodeEnum {
    /**
     * 是-Y
     */
    YES_Y("Y", "是"),
    /**
     * 否-N
     */
    NO_N("N", "否"),
    /**
     * 是-1
     */
    YES_1("1", "是"),
    /**
     * 否-0
     */
    NO_0("0", "否");

    private final String code;

    private final String name;

    JudgeCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static boolean validYes(String code) {
        if (JudgeCodeEnum.YES_Y.getCode().equals(code)) {
            return true;
        } else if (JudgeCodeEnum.YES_1.getCode().equals(code)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean validNo(String code) {
        if (JudgeCodeEnum.NO_N.getCode().equals(code)) {
            return true;
        } else if (JudgeCodeEnum.NO_0.getCode().equals(code)) {
            return true;
        } else {
            return false;
        }
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
