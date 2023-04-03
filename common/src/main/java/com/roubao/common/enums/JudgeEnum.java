package com.roubao.common.enums;

/**
 * 判断枚举
 *
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/3/16
 **/
public enum JudgeEnum {
    /**
     * 是-Y
     */
    YES_Y("Y", "是"),
    /**
     * 否-N
     */
    NO_N("N", "否"),
    /**
     * 是-0
     */
    YES_0("0", "是"),
    /**
     * 否-1
     */
    NO_1("1", "否");

    private final String code;

    private final String name;

    JudgeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static boolean validYes(String code) {
        if (JudgeEnum.YES_Y.getCode().equals(code)) {
            return true;
        } else if (JudgeEnum.YES_0.getCode().equals(code)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean validNo(String code) {
        if (JudgeEnum.NO_N.getCode().equals(code)) {
            return true;
        } else if (JudgeEnum.NO_1.getCode().equals(code)) {
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
