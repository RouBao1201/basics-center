package com.roubao.nosql.elasticsearch.enums;

/**
 * 条件连接类型枚举
 *
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/5/25
 **/
public enum AOEnum {
    MUST {
        @Override
        public String toString() {
            return "MUST";
        }
    }, SHOULD {
        @Override
        public String toString() {
            return "SHOULD";
        }
    }
}
