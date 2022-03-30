/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:EntityRepository.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.core.repository;

import org.bloques.entity.core.definition.EntityDefinition;

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
