/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:EntityRepositoryInBatchImpl.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.core.repository;

import org.bloques.entity.core.definition.EntityDefinition;
import org.bloques.entity.core.object.AbstractEntityObject;
import org.bloques.entity.core.common.MapUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class EntityRepositoryInBatchImpl implements EntityRepositoryInBatch {

    private final EntityRepositoryFactory repositoryFactory;

    private final Map<EntityDefinition, Set<AbstractEntityObject>> appnedMapSet = new HashMap<>();

    private final Map<EntityDefinition, Set<AbstractEntityObject>> updateMapSet = new HashMap<>();

    private final Map<EntityDefinition, Set<AbstractEntityObject>> deleteMapSet = new HashMap<>();

    public EntityRepositoryInBatchImpl(EntityRepositoryFactory repositoryFactory) {
        this.repositoryFactory = repositoryFactory;
    }

    @Override
    public void appendForAdd(AbstractEntityObject entityObject) {
        MapUtil.getSetByKey(appnedMapSet, entityObject.getEntityDefinition()).add(entityObject);
    }

    @Override
    public void appendForUpdate(AbstractEntityObject entityObject) {
        MapUtil.getSetByKey(updateMapSet, entityObject.getEntityDefinition()).add(entityObject);
    }

    @Override
    public void appendForDelete(AbstractEntityObject entityObject) {
        MapUtil.getSetByKey(deleteMapSet, entityObject.getEntityDefinition()).add(entityObject);
    }

    @Override
    public void commit(Boolean transaction) {
        if (transaction) {
            throw new RuntimeException("Now EntityRepositoryInBatchImpl could not support transaction in .");
        } else {
            doForMapSet(appnedMapSet, (entityRepository, entityObject) -> entityRepository.add(entityObject.getEntityDefinition(), entityObject.getValue()));
            doForMapSet(updateMapSet, (entityRepository, entityObject) -> entityRepository.update(entityObject.getEntityDefinition(), entityObject.getValue()));
            doForMapSet(deleteMapSet, (entityRepository, entityObject) -> entityRepository.delete(entityObject.getEntityDefinition(), entityObject.getValue()));
        }
    }

    private void doForMapSet(Map<EntityDefinition, Set<AbstractEntityObject>> mapSet, Consumer consumer) {
        for (Map.Entry<EntityDefinition, Set<AbstractEntityObject>> entry : mapSet.entrySet()) {
            EntityDefinition entityDefinition = entry.getKey();
            EntityRepository entityRepository = repositoryFactory.getRepository(entityDefinition.getId());
            for (AbstractEntityObject item : entry.getValue()) {
                consumer.apply(entityRepository, item);
            }
        }
    }

    @FunctionalInterface
    private interface Consumer {
        void apply(EntityRepository entityRepository, AbstractEntityObject entityObject);
    }
}
