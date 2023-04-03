package com.roubao.common.utils;

import java.lang.reflect.Array;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

/**
 * 判断工具类（用于替代if...else..,纯纯的学习函数式编程）
 * 
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/3/3
 **/
@Slf4j
public class JudgeUtil {

    /**
     * 对象判空分支执行
     * 
     * @param obj obj
     * @return EitherHandleFunction
     */
    public static EitherHandleFunction isNotEmpty(Object obj) {
        return (trueHandle, falseHandle) -> {
            if (obj == null) {
                falseHandle.run();
                return;
            }
            if (obj instanceof Boolean) {
                if ((Boolean) obj) {
                    trueHandle.run();
                }
                else {
                    falseHandle.run();
                }
            }
            else if (obj instanceof CharSequence) {
                if (((CharSequence) obj).length() == 0) {
                    falseHandle.run();
                }
                else {
                    trueHandle.run();
                }
            }
            else if (obj instanceof Map) {
                if (((Map<?, ?>) obj).isEmpty()) {
                    falseHandle.run();
                }
                else {
                    trueHandle.run();
                }
            }
            else if (obj instanceof Iterable) {
                if (((Iterable<?>) obj).iterator() == null || !((Iterable<?>) obj).iterator().hasNext()) {
                    falseHandle.run();
                }
                else {
                    trueHandle.run();
                }
            }
            else {
                if (obj != null && obj.getClass().isArray() && Array.getLength(obj) > 0) {
                    trueHandle.run();
                }
                else {
                    falseHandle.run();
                }
            }
        };
    }

    @FunctionalInterface
    public interface EitherHandleFunction {
        /**
         * 分支处理逻辑
         *
         * @param trueHandle true分支处理逻辑
         * @param falseHandle false分支处理逻辑
         */
        void either(Runnable trueHandle, Runnable falseHandle);
    }

    private JudgeUtil() {

    }
}
