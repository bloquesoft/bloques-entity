/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-jpa-support
 * FileName:JpaEntityRepositoryImpl.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.jpa.impl;

import lombok.extern.slf4j.Slf4j;
import org.bloques.entity.clazz.common.ObjectHandler;
import org.bloques.entity.clazz.definition.ClassEntityDefinition;
import org.bloques.entity.core.definition.EntityDefinition;
import org.bloques.entity.core.repository.EntityRepository;
import org.springframework.context.ApplicationContext;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class JpaEntityRepositoryImpl implements EntityRepository {

    /*@Setter
    @Getter
    private JpaRepositoryBeanImpl repoBeanImpl;*/

    private final ApplicationContext applicationContext;

    private static volatile JpaEntityManagerImpl EntityManagerImpl;

    public JpaEntityManagerImpl getEntityManagerImpl() {
        if (EntityManagerImpl == null) {
            synchronized (JpaEntityManagerImpl.class) {
                if (EntityManagerImpl == null) {
                    EntityManagerImpl = applicationContext.getBean(JpaEntityManagerImpl.class);
                }
            }
        }
        return EntityManagerImpl;
    }

    public JpaEntityRepositoryImpl(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object findById(EntityDefinition ed, Object entityId) {
        return this.getEntityManagerImpl().findById(ed, entityId);
    }

    @Override
    public Object findOne(EntityDefinition ed, LinkedHashMap<String, Object> uniqs) {
        return this.getEntityManagerImpl().findOne(ed, uniqs);
    }

    @Override
    public List findList(EntityDefinition ed, LinkedHashMap<String, Object> parameters) {
        return this.getEntityManagerImpl().findList(ed, parameters);
    }

    @Override
    public Object add(EntityDefinition entityDefinition, Object entityObject) {
        if (entityObject instanceof Map) {
            return add(entityDefinition, (Map) entityObject);
        }
        return addEntityBean(entityDefinition, entityObject);
    }

    @Override
    public Object add(EntityDefinition entityDefinition, Map<String, Object> entityMap) {
        Object entityBean = ObjectHandler.mapToObject(((ClassEntityDefinition) entityDefinition).getClazz(), entityMap);
        return addEntityBean(entityDefinition, entityBean);
    }

    private Object addEntityBean(EntityDefinition entityDefinition, Object entityBean) {
        return this.getEntityManagerImpl().add(entityDefinition, entityBean);
    }

    @Override
    public Object update(EntityDefinition ed, Object values) {
        return this.getEntityManagerImpl().update(ed, values);
    }

    @Override
    public Object update(EntityDefinition ed, Map<String, Object> values) {
        return this.getEntityManagerImpl().update(ed, values);
    }

    @Override
    public int deleteById(EntityDefinition ed, Object id) {
        return this.getEntityManagerImpl().deleteById(ed, id);
    }

    @Override
    public int delete(EntityDefinition ed, Object value) {
        return this.getEntityManagerImpl().delete(ed, value);
    }
}
