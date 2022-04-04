/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:EntityExecutor.java
 * Author:zhangjian
 * date:2022/3/31 下午10:15
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.core.executor;

import io.github.bloquesoft.entity.core.register.EntityDefinitionFactory;
import io.github.bloquesoft.entity.core.repository.EntityRepositoryFactory;
import io.github.bloquesoft.entity.core.definition.EntityDefinition;

import java.util.LinkedHashMap;
import java.util.List;

public interface EntityExecutor
{
    void setEntityDefinitionFactory(EntityDefinitionFactory entityDefinitionFactory);

    void setEntityRepositoryFactory(EntityRepositoryFactory entityRepositoryFactory);

    Object add(EntityDefinition entityDefinition, Object entityValue);

    Object add(String entityDefinitionId, Object entityValue);

    Object add(String packageName, String entityName, Object entityValue);

    Object findById(EntityDefinition entityDefinition, Object id, String graph);

    Object findById(String entityDefinitionId, Object id, String graph);

    Object findById(String packageName, String entityName, Object id, String graph);

    void update(EntityDefinition entityDefinition, Object entityValue);

    void update(String entityDefinitionId, Object entityValue);

    void update(String packageName, String entityName, Object entityValue);

    List<Object> findList(EntityDefinition entityDefinition, LinkedHashMap<String, Object> parameters, String graph);

    List<Object> findList(String entityDefinitionId, LinkedHashMap<String, Object> parameters, String graph);

    List<Object> findList(String packageName, String entityName, LinkedHashMap<String, Object> parameters, String graph);

    Object findOne(String entityDefinitionId, LinkedHashMap<String, Object> uniqs);

    void delete(EntityDefinition entityDefinition, Object id);

    void delete(String entityDefinitionId, Object id);

    void delete(String packageName, String entityName, Object id);
}