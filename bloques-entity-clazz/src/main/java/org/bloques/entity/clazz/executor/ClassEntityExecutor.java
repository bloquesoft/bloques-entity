/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-clazz
 * FileName:ClassEntityExecutor.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.clazz.executor;

import java.util.LinkedHashMap;
import java.util.List;

public interface ClassEntityExecutor
{
    <T> T  add(Class<T> clazz, Object values);

    <T> void update(Class<T> clazz, Object values);

    <T> T findById(Class<T> clazz, Object id);

    <T> T findById(Class<T> clazz, Object id, String graph);

    <T> List<Object> findList(Class<T> clazz, LinkedHashMap<String, Object> parameters);

    <T> void delete(Class<T> clazz, Object id);
}