/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:EntityRepositoryRegisterImpl.java
 * Author:zhangjian
 * date:2022/3/31 下午10:15
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.core.repository;

import org.springframework.util.Assert;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EntityRepositoryRegisterImpl implements EntityRepositoryRegister {

    private final Map<String, EntityRepository> container = new ConcurrentHashMap<>();

    @Override
    public EntityRepository getRepository(String entityDefinitionId) {
        Assert.hasLength(entityDefinitionId);
        Assert.isTrue(container.containsKey(entityDefinitionId), "No such EntityRepository id:" + entityDefinitionId);
        return container.get(entityDefinitionId);
    }

    @Override
    public void register(String entityDefinitionId, EntityRepository entityRepository) {
        Assert.hasLength(entityDefinitionId);
        Assert.notNull(entityRepository);
        if (!container.containsKey(entityDefinitionId)) {
            synchronized (container) {
                if (!container.containsKey(entityDefinitionId)) {
                    container.put(entityDefinitionId, entityRepository);
                }
            }
        }
    }
}