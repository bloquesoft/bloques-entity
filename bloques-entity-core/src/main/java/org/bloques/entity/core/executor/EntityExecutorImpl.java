/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:EntityExecutorImpl.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.core.executor;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.bloques.entity.core.definition.EntityDefinition;
import org.bloques.entity.core.loader.EntityGraphLoader;
import org.bloques.entity.core.logic.CudLog;
import org.bloques.entity.core.logic.CudLogic;
import org.bloques.entity.core.object.AbstractEntityObject;
import org.bloques.entity.core.object.EntityObjectCreator;
import org.bloques.entity.core.register.EntityDefinitionFactory;
import org.bloques.entity.core.repository.EntityRepository;
import org.bloques.entity.core.repository.EntityRepositoryFactory;
import org.bloques.entity.core.repository.EntityRepositoryInBatchImpl;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;
import java.util.List;

@Slf4j
public class EntityExecutorImpl implements EntityExecutor {

    @Getter
    @Setter
    private EntityDefinitionFactory entityDefinitionFactory;

    @Getter
    @Setter
    private EntityRepositoryFactory entityRepositoryFactory;

    @Getter
    @Setter
    private EntityGraphLoader graphLoader;

    public EntityExecutorImpl() {}

    @Override
    public Object add(EntityDefinition entityDefinition, Object entityValue) {
        CudLog cudLog = new CudLog();
        CudLogic logic = new CudLogic(entityRepositoryFactory, cudLog);
        AbstractEntityObject entityObject = EntityObjectCreator.getSingleton().create(entityDefinition, entityValue);
        logic.add(entityObject);
        repoLogForAdd(cudLog);
        return entityObject.getValue();
    }

    @Override
    public Object add(String entityDefinitionId, Object entityValue) {
        EntityDefinition entityDefinition = getEntityDefinition(entityDefinitionId);
        return add(entityDefinition, entityValue);
    }

    @Override
    public Object add(String packageName, String entityName, Object entityValue) {
        EntityDefinition entityDefinition = getEntityDefinition(packageName, entityName);
        return add(entityDefinition, entityValue);
    }

    @Override
    public Object findById(EntityDefinition entityDefinition, Object id, String graph) {
        EntityRepository er = getEntityRepository(entityDefinition);
        long pkValue;
        try {
            pkValue = Long.parseLong(id.toString());
        } catch (NumberFormatException ept) {
            throw new IllegalArgumentException("JEntity PrimaryKey must be long type,entityDefinitionId:" + entityDefinition.getId());
        }
        Object entity = er.findById(entityDefinition, pkValue);
        if (entity != null && StringUtils.hasLength(graph)) {
            Assert.notNull(graphLoader, "GraphLoader could not be null.");
            graphLoader.load(entityDefinition, entity, graph);
        }
        return entity;
    }

    @Override
    public Object findById(String packageName, String entityName, Object id, String graph) {
        EntityDefinition entityDefinition = getEntityDefinition(packageName, entityName);
        return this.findById(entityDefinition, id, graph);
    }

    @Override
    public Object findById(String entityDefinitionId, Object id, String graph) {
        EntityDefinition entityDefinition = getEntityDefinition(entityDefinitionId);
        return this.findById(entityDefinition, id, graph);
    }

    @Override
    public void update(EntityDefinition entityDefinition, Object newEntityValues) {

        AbstractEntityObject newEntityObjectValues = EntityObjectCreator.getSingleton().create(entityDefinition, newEntityValues);
        Object entityPkValue = newEntityObjectValues.getPkValue();
        Assert.notNull(entityPkValue, "缺少entity pk 参数");
        Object targetEntityObject = findById(entityDefinition.getId(), entityPkValue, null);
        Assert.notNull(targetEntityObject, "找不到pk 对应的实体");
        CudLog cudLog = new CudLog();
        CudLogic logic = new CudLogic(entityRepositoryFactory, cudLog);
        logic.update(newEntityObjectValues, EntityObjectCreator.getSingleton().create(entityDefinition, targetEntityObject));
        repoLogForUpdate(logic.getCudLog());
    }

    @Override
    public void update(String entityDefinitionId, Object newEntityValues) {
        EntityDefinition ed = getEntityDefinition(entityDefinitionId);
        this.update(ed, newEntityValues);
    }

    @Override
    public void update(String packageName, String entityName, Object newEntityValues) {
        EntityDefinition ed = getEntityDefinition(packageName, entityName);
        this.update(ed, newEntityValues);
    }

    @Override
    public List<Object> findList(EntityDefinition entityDefinition, LinkedHashMap<String, Object> parameters, String graph) {
        EntityRepository er = getEntityRepository(entityDefinition);
        List<Object> list = er.findList(entityDefinition, parameters);
        if (list != null && list.size() > 0 && StringUtils.hasLength(graph)) {
            Assert.notNull(graphLoader, "JEntity GraphLoader in EntityExecutor is null");
            for (Object item : list) {
                graphLoader.load(entityDefinition, item, graph);
            }
        }
        return list;
    }

    @Override
    public List<Object> findList(String entityDefinitionId, LinkedHashMap<String, Object> parameters, String graph) {
        EntityDefinition entityDefinition = getEntityDefinition(entityDefinitionId);
        return this.findList(entityDefinition, parameters, graph);
    }

    @Override
    public List<Object> findList(String packageName, String entityName, LinkedHashMap<String, Object> parameters, String graph) {
        EntityDefinition entityDefinition = getEntityDefinition(packageName, entityName);
        return this.findList(entityDefinition, parameters, graph);
    }

    @Override
    public Object findOne(String entityDefinitionId, LinkedHashMap<String, Object> uniqs) {
        EntityDefinition ed = getEntityDefinition(entityDefinitionId);
        EntityRepository er = getEntityRepository(ed);
        return er.findOne(ed, uniqs);
    }

    @Override
    public void delete(EntityDefinition entityDefinition, Object id) {
        Object oldEntityObject = findById(entityDefinition, id, null);
        if (oldEntityObject != null) {
            CudLog cudLog = new CudLog();
            CudLogic logic = new CudLogic(entityRepositoryFactory, cudLog);
            AbstractEntityObject delObject = EntityObjectCreator.getSingleton().create(entityDefinition, oldEntityObject);
            logic.delete(delObject);
            repoLogForDelete(cudLog);
        }
    }

    @Override
    public void delete(String entityDefinitionId, Object id) {
        EntityDefinition entityDefinition = getEntityDefinition(entityDefinitionId);
        delete(entityDefinition, id);
    }

    @Override
    public void delete(String packageName, String entityName, Object id) {
        EntityDefinition entityDefinition = getEntityDefinition(packageName, entityName);
        delete(entityDefinition, id);
    }

    private EntityDefinition getEntityDefinition(String entityDefinitionId) {
        return entityDefinitionFactory.getEntityDefinition(entityDefinitionId);
    }

    private EntityDefinition getEntityDefinition(String packageName, String entityName) {
        return entityDefinitionFactory.getEntityDefinition(packageName, entityName);
    }

    private EntityRepository getEntityRepository(EntityDefinition entityDefinition) {
        return entityRepositoryFactory.getRepository(entityDefinition.getId());
    }

    public void repoLogForAdd(CudLog log) {
        EntityRepositoryInBatchImpl batch = new EntityRepositoryInBatchImpl(this.entityRepositoryFactory);
        log.getAppendMapSet().forEach((EntityDefinition, Set) -> Set.forEach(item -> batch.appendForAdd(item)));
        batch.commit(false);
    }

    public void repoLogForDelete(CudLog log) {
        EntityRepositoryInBatchImpl batch = new EntityRepositoryInBatchImpl(this.entityRepositoryFactory);
        log.getDeleteMapSet().forEach((EntityDefinition, Set) -> Set.forEach(item -> batch.appendForDelete(item)));
        batch.commit(false);
    }

    public void repoLogForUpdate(CudLog log) {
        EntityRepositoryInBatchImpl batch = new EntityRepositoryInBatchImpl(this.entityRepositoryFactory);
        log.getAppendMapSet().forEach((EntityDefinition, Set) -> Set.forEach(item -> batch.appendForAdd(item)));
        log.getUpdateMapSet().forEach((EntityDefinition, Set) -> Set.forEach(item -> batch.appendForUpdate(item)));
        log.getDeleteMapSet().forEach((EntityDefinition, Set) -> Set.forEach(item -> batch.appendForDelete(item)));
        batch.commit(false);
    }
}