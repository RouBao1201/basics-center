package com.roubao.common.locker;

import java.util.LinkedList;
import java.util.Map;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import com.roubao.common.locker.redis.autoconfiguration.RedisLockerAutoConfiguration;
import com.roubao.common.locker.redis.bean.RedisLocker;
import com.roubao.common.locker.redisson.autoconfiguration.RedissonLockerAutoConfiguration;
import com.roubao.common.locker.redisson.bean.RedissonLocker;
import com.roubao.common.locker.zookeeper.autoconfiguration.ZookeeperLockerAutoConfiguration;
import com.roubao.common.locker.zookeeper.bean.ZookeeperLocker;

import lombok.extern.slf4j.Slf4j;

/**
 * locker自动装配类
 * 
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/6/27
 **/
@Slf4j
public class LockerImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        Map<String, Object> annotationAttributes = annotationMetadata
            .getAnnotationAttributes(EnableCustomLocker.class.getName());
        Class<? extends ILocker>[] lockerClass = (Class<? extends ILocker>[]) annotationAttributes.get("value");
        LinkedList<String> beanList = new LinkedList<>();
        for (Class<? extends ILocker> aClass : lockerClass) {
            String lockerClassName = aClass.getName();
            if (lockerClassName.equals(RedisLocker.class.getName())) {
                beanList.add(RedisLockerAutoConfiguration.class.getName());
            }
            else if (lockerClassName.equals(RedissonLocker.class.getName())) {
                beanList.add(RedissonLockerAutoConfiguration.class.getName());
            }
            else if (lockerClassName.equals(ZookeeperLocker.class.getName())) {
                beanList.add(ZookeeperLockerAutoConfiguration.class.getName());
            }
        }
        return beanList.toArray(new String[0]);
    }
}
