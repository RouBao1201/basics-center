package com.roubao.common.locker;

import java.lang.annotation.*;

import org.springframework.context.annotation.Import;

/**
 * 分布式锁启动装配注解
 * 
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/6/27
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(LockerImportSelector.class)
public @interface EnableCustomLocker {
    /**
     * 锁类型
     * 
     * @return Class
     */
    Class<? extends ILocker>[] value();
}
