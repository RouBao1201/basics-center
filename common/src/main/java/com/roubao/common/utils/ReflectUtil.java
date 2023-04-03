package com.roubao.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;

/**
 * 反射工具类
 * 
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/3/15
 **/
@Slf4j
public class ReflectUtil {

    /**
     * 获取对象属性值
     * 
     * @param obj object对象
     * @param fieldName 属性名称
     * @param clazz 对象类型
     * @return 属性值
     * @param <T> 属性值类型
     */
    public static <T> T getFieldValue(Object obj, String fieldName, Class<T> clazz) {
        Optional<Field> fieldOptional = getDeclaredField(obj, fieldName);
        if (!fieldOptional.isPresent()) {
            throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + obj + "].");
        }
        Field field = fieldOptional.get();
        makeAccessible(field);
        T result = null;
        try {
            result = (T) field.get(obj);
        }
        catch (IllegalAccessException e) {
            log.error("ReflectHelper ==> GetFieldValue error:{}", e.getMessage());
        }
        return result;
    }

    /**
     * 获取属性值
     * 
     * @param obj object对象
     * @param fieldName 属性名
     * @return 属性值
     */
    public static Object getFieldValue(Object obj, String fieldName) {
        return getFieldValue(obj, fieldName, Object.class);
    }

    /**
     * 直接设置对象属性值, 忽略 private/protected 修饰符, 也不经过 setter
     * 
     * @param obj object对象
     * @param fieldName 属性名
     * @param value 设置属性的新值
     */
    public static void setFieldValue(Object obj, String fieldName, Object value) {
        Optional<Field> fieldOptional = getDeclaredField(obj, fieldName);
        if (!fieldOptional.isPresent()) {
            throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + obj + "]");
        }
        Field field = fieldOptional.get();
        makeAccessible(field);
        try {
            field.set(obj, value);
        }
        catch (IllegalAccessException e) {
            log.error("ReflectHelper ==> SetFieldValue error:{}", e.getMessage());
        }
    }

    public static <T> T invokeMethod(Object obj, String methodName, Class<T> callbackClass) {
        return invokeMethod(obj, methodName, new Class[] {}, new Object[] {}, callbackClass);
    }

    public static Object invokeMethod(Object obj, String methodName) {
        return invokeMethod(obj, methodName, new Class[] {}, new Object[] {});
    }

    /**
     * 直接调用对象方法, 而忽略修饰符(private, protected)
     * 
     * @param obj object对象
     * @param methodName 方法名
     * @param parameterTypes 参数类型
     * @param parameters 参数值
     * @return 方法执行结果
     */
    public static Object invokeMethod(Object obj, String methodName, Class<?>[] parameterTypes, Object[] parameters) {
        return invokeMethod(obj, methodName, parameterTypes, parameters, Object.class);
    }

    public static <T> T invokeMethod(Object obj, String methodName, Class<?>[] parameterTypes, Object[] parameters,
        Class<T> callbackClass) {
        Optional<Method> methodOptional = getDeclaredMethod(obj, methodName, parameterTypes);
        if (!methodOptional.isPresent()) {
            throw new IllegalArgumentException("Could not find method [" + methodName + "] on target [" + obj + "]");
        }
        Method method = methodOptional.get();
        method.setAccessible(true);
        try {
            return (T) method.invoke(obj, parameters);
        }
        catch (IllegalAccessException | InvocationTargetException e) {
            log.error("ReflectHelper ==> InvokeMethod error:{}", e.getMessage());
            // throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * 通过反射, 获得 Class 定义中声明的父类的泛型参数类型 如: public EmployeeDao extends BaseDao<Employee, String>
     * 
     * @param clazz class类型
     * @return 参数类型
     */
    public static Class getSuperClassGenericType(Class clazz) {
        return getSuperClassGenericType(clazz, 0);
    }

    /**
     * 通过反射, 获得定义 Class 时声明的父类的泛型参数的类型 如: public EmployeeDao extends BaseDao<Employee, String>
     * 
     * @param clazz class类型
     * @param index 父类第几个泛型
     * @return 类型
     */
    public static Class getSuperClassGenericType(Class clazz, int index) {
        Type genType = clazz.getGenericSuperclass();
        if (!(genType instanceof ParameterizedType)) {
            return Object.class;
        }
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        if (index >= params.length || index < 0) {
            return Object.class;
        }
        if (!(params[index] instanceof Class)) {
            return Object.class;
        }
        return (Class) params[index];
    }

    /**
     * 循环向上转型,获取对象属性
     * 
     * @param obj object对象
     * @param filedName 属性名
     * @return 属性对象
     */
    public static Optional<Field> getDeclaredField(Object obj, String filedName) {
        for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass
            .getSuperclass()) {
            try {
                return Optional.of(superClass.getDeclaredField(filedName));
            }
            catch (NoSuchFieldException e) {
                // Field 不在当前类定义, 继续向上转型
            }
        }
        return Optional.empty();
    }

    /**
     * 循环向上转型, 获取对象的 DeclaredMethod
     * 
     * @param obj object对象
     * @param methodName 方法名
     * @param parameterTypes 方法参数类型
     * @return 方法对象DeclaredMethod
     */
    public static Optional<Method> getDeclaredMethod(Object obj, String methodName, Class<?>[] parameterTypes) {
        for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass
            .getSuperclass()) {
            try {
                return Optional.of(superClass.getDeclaredMethod(methodName, parameterTypes));
            }
            catch (NoSuchMethodException e) {
                // Method 不在当前类定义, 继续向上转型
            }
        }
        return Optional.empty();
    }

    /**
     * 暴力反射
     * 
     * @param field 属性对象
     */
    public static void makeAccessible(Field field) {
        if (!Modifier.isPublic(field.getModifiers())) {
            field.setAccessible(true);
        }
    }

    /**
     * 私有构造方法
     */
    private ReflectUtil() {

    }
}
