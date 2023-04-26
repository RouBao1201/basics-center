package com.roubao.common.spring.holder;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * spring上下文持有工具类
 *
 * @author SongYanBin
 * @copyright 2022-2099 SongYanBin All Rights Reserved.
 * @since 2022/12/22
 **/
@Component
public class SpringContextHolder implements ApplicationContextAware, DisposableBean {
    private static final Logger log = LoggerFactory.getLogger(SpringContextHolder.class);

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextHolder.applicationContext = applicationContext;
    }

    /**
     * 获取spring上下文对象
     *
     * @return spring上下文对象
     */
    public static ApplicationContext getApplicationContext() {
        checkApplicationContext();
        return applicationContext;
    }

    /**
     * 根据bean名称获取bean
     *
     * @param beanName bean名称
     * @return bean
     */
    public static Object getBean(String beanName) {
        return getBean(beanName, false);
    }

    /**
     * 根据bean名称获取bean
     *
     * @param beanName bean名称
     * @param ignoreNotFound 是否忽略容器中是否含有bean
     * @return bean
     */
    public static Object getBean(String beanName, boolean ignoreNotFound) {
        try {
            return getApplicationContext().getBean(beanName);
        }
        catch (NoSuchBeanDefinitionException e) {
            log.error("SpringContextHolder => The Bean is not found. [beanName:{}, ignoreNotFound:{}]. ErrorMessage:"
                + e.getMessage() + ". ", beanName, ignoreNotFound);
            if (ignoreNotFound) {
                return null;
            }
            throw e;
        }
    }

    /**
     * 根据bean类型获取bean
     *
     * @param beanClass bean类型
     * @param <T> bean类型
     * @return bean
     */
    public static <T> T getBean(Class<T> beanClass) {
        return getBean(beanClass, false);
    }

    /**
     * 根据bean类型获取bean
     *
     * @param beanClass bean类型
     * @param ignoreNotFound 是否忽略容器中是否含有bean
     * @param <T> bean类型
     * @return bean
     */
    public static <T> T getBean(Class<T> beanClass, boolean ignoreNotFound) {
        try {
            return getApplicationContext().getBean(beanClass);
        }
        catch (NoSuchBeanDefinitionException e) {
            log.error(
                "SpringContextHolder => SpringContextHolder => The Bean is not found. [beanClass:{}, ignoreNotFound:{}]. ErrorMessage:"
                    + e.getMessage() + ". ",
                beanClass, ignoreNotFound);
            if (ignoreNotFound) {
                return null;

            }
            throw e;
        }
    }

    /**
     * 根据bean名称和bean类型获取bean
     *
     * @param beanName bean名称
     * @param clazz bean类型
     * @param <T> bean类型
     * @return bean
     */
    public static <T> T getBean(String beanName, Class<T> clazz) {
        return getBean(beanName, clazz, false);
    }

    /**
     * 根据bean名称和bean类型获取bean
     *
     * @param beanName bean名称
     * @param beanClass bean类型
     * @param ignoreNotFound ignoreNotFound
     * @return bean
     * @param <T> bean类型
     */
    public static <T> T getBean(String beanName, Class<T> beanClass, boolean ignoreNotFound) {
        try {
            return getApplicationContext().getBean(beanClass);
        }
        catch (NoSuchBeanDefinitionException e) {
            log.error("SpringContextHolder => The Bean is not found. [beanName:{}, beanClass:{}, ignoreNotFound:"
                + ignoreNotFound + "]. ErrorMessage:" + e.getMessage() + ". ", beanName, beanClass);
            if (ignoreNotFound) {
                return null;
            }
            throw e;
        }
    }

    /**
     * 根据bean类型获取所有bean
     *
     * @param beanClass bean类型
     * @param <T> 枚举
     * @return bean
     */
    public static <T> Map<String, T> getBeanMapOfType(Class<T> beanClass) {
        return getApplicationContext().getBeansOfType(beanClass);
    }

    /**
     * 是否存在bean
     * 
     * @param beanName bean名称
     * @return boolean
     */
    public static boolean containsBean(String beanName) {
        return getApplicationContext().containsBean(beanName);
    }

    /**
     * 根据bean名称获取bean类型
     * 
     * @param beanName bean名称
     * @return bean类型
     */
    public static Class<?> getBeanType(String beanName) {
        return getApplicationContext().getType(beanName);
    }

    /**
     * 根据bean类型获取bean名称
     * 
     * @param beanClass bean类型
     * @return bean名称集合
     * @param <T> bean类型泛型
     */
    public static <T> String[] getBeanNamesForType(Class<T> beanClass) {
        return getApplicationContext().getBeanNamesForType(beanClass);
    }

    /**
     * 校验spring上下对象是否为空
     */
    private static void checkApplicationContext() {
        if (applicationContext == null) {
            throw new IllegalStateException("SpringContextHolder => ApplicationContext is not injected.");
        }
    }

    @Override
    public void destroy() throws Exception {
        SpringContextHolder.clearHolder();
    }

    private static void clearHolder() {
        log.info("SpringContextHolder ==> Clean spring context.");
        SpringContextHolder.applicationContext = null;
    }
}
