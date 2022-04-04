/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-clazz
 * FileName:ObjectHandler.java
 * Author:zhangjian
 * date:2022/3/31 下午10:16
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.clazz.common;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ObjectHandler {

    public static boolean containField(Object obj, String fieldName) {
        Field field = ReflectionUtils.findField(obj.getClass(), fieldName);
        return field != null;
    }

    public static Object getFieldValue(Object obj, String fieldName) {
        Field field = ReflectionUtils.findField(obj.getClass(), fieldName);
        Assert.hasLength(fieldName, "can not find field " + fieldName);
        if (!field.isAccessible()) {
            field.setAccessible(true);
        }
        return ReflectionUtils.getField(field, obj);
    }

    public static void setFieldValue(Object entity, String fieldName, Object value) {
        Field field = ReflectionUtils.findField(entity.getClass(), fieldName);
        if (field != null) {
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            ReflectionUtils.setField(field, entity, value);
        }
    }

    public static <T> List<T> listMapToListObject(Class<T> clazz, List<Map<String, Object>> listMap) {
        List<T> list = new LinkedList<>();
        for (Map<String, Object> map : listMap) {
            T item = mapToObject(clazz, map);
            list.add(item);
        }
        return list;
    }

    public static <T> T mapToObject(Class<T> clazz, Map<String, Object> map) {
        T root = null;
        try {
            root = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if (root != null) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String name = entry.getKey();
                Object value = entry.getValue();
                try {
                    Field field = clazz.getDeclaredField(name);
                    if (value instanceof List) {
                        Type type = ((ParameterizedTypeImpl) field.getGenericType()).getActualTypeArguments()[0];
                        Class tc = (Class) type;
                        List list = listMapToListObject(tc, (List<Map<String, Object>>) value);
                        BeanUtils.setProperty(root, name, list);
                    } else {
                        BeanUtils.setProperty(root, name, value);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
            }
        }
        return root;
    }
}
