/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:EntityRepository.java
 * Author:zhangjian
 * date:2022/3/31 下午10:15
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.core.repository;

import io.github.bloquesoft.entity.core.definition.EntityDefinition;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface EntityRepository
{
    Object findById(EntityDefinition ed, Object entityId);

    Object findOne(EntityDefinition ed, LinkedHashMap<String, Object> uniqs);

    List<Object> findList(EntityDefinition ed, LinkedHashMap<String, Object> paramters);

    Object add(EntityDefinition ed, Object values);

    Object add(EntityDefinition ed, Map<String, Object> values);

    Object update(EntityDefinition ed, Object values);

    Object update(EntityDefinition ed, Map<String, Object> values);

    int deleteById(EntityDefinition ed, Object id);

    int delete(EntityDefinition ed, Object entity);
}
