package com.zengyanyu.system.util;

import com.zengyanyu.system.dto.Person;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zengyanyu
 */
public class BeanUtil {

    private BeanUtil() {
    }

    /**
     * javaBaen对象转换为map
     *
     * @param obj javaBean类型的对象
     * @return
     * @throws Exception
     */
    public static Map<String, Object> bean2map(Object obj) throws Exception {
        Map<String, Object> map = new HashMap<>();
        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass(), Object.class);
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor pd : pds) {
            // 获取属性名称
            String name = pd.getName();
            // 获取属性值
            Method getMethod = pd.getReadMethod();
            Object value = getMethod.invoke(obj);
            map.put(name, value);
        }
        return map;
    }

    /**
     * map转换为javaBean对象
     *
     * @param map   map对象
     * @param clazz 转换的javaBean类型
     * @return
     * @throws Exception
     */
    public static <T> T map2bean(Map<String, Object> map, Class<T> clazz) throws Exception {
        BeanInfo beanInfo = Introspector.getBeanInfo(clazz, Object.class);
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
        T obj = clazz.newInstance();
        for (PropertyDescriptor pd : pds) {
            // 获取属性名称
            String name = pd.getName();
            // 获取属性值
            Method setMethod = pd.getWriteMethod();
            setMethod.invoke(obj, map.get(name));
        }
        return obj;
    }

    public static void main(String[] args) throws Exception {
        Person person = new Person("曾", 18, "男");
        Map<String, Object> stringObjectMap = bean2map(person);
        System.out.println(stringObjectMap);
        Person person1 = map2bean(stringObjectMap, Person.class);
        System.out.println(person1);
    }
}
