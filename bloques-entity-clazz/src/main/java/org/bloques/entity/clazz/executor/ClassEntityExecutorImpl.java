/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-clazz
 * FileName:ClassEntityExecutorImpl.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.clazz.executor;

import org.bloques.entity.core.executor.EntityExecutor;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Supplier;

public class ClassEntityExecutorImpl implements ClassEntityExecutor {

    private final EntityExecutor entityExecutor;

    public ClassEntityExecutorImpl(EntityExecutor entityExecutor) {
        this.entityExecutor = entityExecutor;
    }

    @Override
    public <T> T add(Class<T> clazz, Object values) {
        return this.convertTo(()->entityExecutor.add(clazz.getName(), values));
    }

    @Override
    public <T> void update(Class<T> clazz, Object values) {
        this.entityExecutor.update(clazz.getName(), values);
    }

    @Override
    public <T> T findById(Class<T> clazz, Object id) {
        return this.convertTo(()->this.entityExecutor.findById(clazz.getName(), id, null));
    }

    @Override
    public <T> T findById(Class<T> clazz, Object id, String graph) {
        return this.convertTo(()->this.entityExecutor.findById(clazz.getName(), id, graph));
    }

    @Override
    public <T> List<Object> findList(Class<T> clazz, LinkedHashMap<String, Object> paramters) {
        return this.entityExecutor.findList(clazz.getName(), paramters, null);
    }

    @Override
    public <T> void delete(Class<T> clazz, Object id) {
        this.entityExecutor.delete(clazz.getName(), id);
    }

    private <T> T convertTo(Supplier<Object> supplier) {
        Object object = supplier.get();
        if (object != null) {
            return (T) object;
        }
        return null;
    }
}