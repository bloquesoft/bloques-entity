/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-jpa-support
 * FileName:JpaEntityManagerImpl.java
 * Author:zhangjian
 * date:2022/3/31 下午10:17
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.jpa.impl;

import io.github.bloquesoft.entity.core.definition.EntityDefinition;
import io.github.bloquesoft.entity.clazz.definition.ClassEntityDefinition;
import io.github.bloquesoft.entity.clazz.common.ObjectHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class JpaEntityManagerImpl {

    private final ApplicationContext appContext;

    private EntityManager entityManager;

    public JpaEntityManagerImpl(ApplicationContext appContext) {
        this.appContext = appContext;
    }

    private EntityManager getEntityManager() {
        if (entityManager == null) {
            entityManager = appContext.getBean(EntityManager.class);
        }
        return entityManager;
    }

    public Object findById(EntityDefinition ed, Object entityId) {
        Class<?> clazz = ((ClassEntityDefinition) ed).getClazz();
        return getEntityManager().find(clazz, entityId);
    }

    public Object findOne(EntityDefinition ed, LinkedHashMap<String, Object> uniques) {

        List list = findList(ed, uniques);
        if (list.size() == 1) {
            return list.get(0);
        } else {
            if (list.size() == 0) {
                return null;
            }
        }
        return null;
    }


    public List findList(EntityDefinition ed, LinkedHashMap<String, Object> parameters) {

        Class<?> clazz = ((ClassEntityDefinition) ed).getClazz();
        CriteriaBuilder cb = this.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<?> cq = cb.createQuery(clazz);
        Root root = cq.from(clazz);
        cq.select(root);
        Predicate[] predicates = new Predicate[parameters.size()];
        int i = 0;
        for (Map.Entry<String, Object> item : parameters.entrySet()) {
            String fieldId = item.getKey();
            Object fieldValue = item.getValue();
            Predicate predicate = cb.equal(root.get(fieldId), fieldValue);
            predicates[i] = predicate;
            i++;
        }
        cq.where(predicates);
        List<?> list = getEntityManager().createQuery(cq).getResultList();
        return list;
    }

    public List findList(EntityDefinition ed, LinkedHashMap<String, Object> criteria, LinkedHashMap<String, String> orderBy, int startRow, int rows) {

        Class<?> clazz = ((ClassEntityDefinition) ed).getClazz();
        CriteriaBuilder cb = this.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<?> cq = cb.createQuery(clazz);
        Root root = cq.from(clazz);
        cq.select(root);
        if (criteria.size() > 0) {
            Predicate[] predicates = new Predicate[criteria.size()];
            int i = 0;
            for (Map.Entry<String, Object> item : criteria.entrySet()) {
                String fieldId = item.getKey();
                Object fieldValue = item.getValue();
                Predicate predicate = cb.equal(root.get(fieldId), fieldValue);
                predicates[i] = predicate;
                i++;
            }
            cq.where(predicates);
        }
        if (orderBy.size() > 0) {
            List<Order> orderList = new ArrayList<>();
            for (Map.Entry<String, String> item : orderBy.entrySet()) {

                String fieldName = item.getKey();
                Assert.hasLength(fieldName, "");

                if (StringUtils.hasLength(item.getValue())) {
                    switch (item.getValue().toUpperCase()) {
                        case "asc":
                            orderList.add(cb.asc(root.get(item.getKey())));
                            break;
                        case "desc":
                            orderList.add(cb.desc(root.get(item.getKey())));
                            break;
                        default:
                            throw new IllegalArgumentException("BEntity orderBy args must be asc or desc, now is " + item.getValue());
                    }
                } else {
                    orderList.add(cb.asc(root.get(item.getKey())));
                }

            }
            cq.orderBy(orderList);
        }
        Query query = getEntityManager().createQuery(cq);
        if (startRow >= 0 && rows > 0) {
            query.setFirstResult(startRow);
            query.setMaxResults(rows);
        }
        return query.getResultList();
    }

    @Transactional
    public Object add(EntityDefinition ed, Object values) {
        this.getEntityManager().persist(values);
        return values;
    }

    @Transactional
    public Object add(EntityDefinition ed, Map<String, Object> values) {
        Class<?> clazz = ((ClassEntityDefinition) ed).getClazz();
        Object entityObj = ObjectHandler.mapToObject(clazz, values);
        this.getEntityManager().persist(entityObj);
        return entityObj;
    }

    @Transactional
    public Object update(EntityDefinition ed, Object values) {
        this.getEntityManager().merge(values);
        return values;
    }

    @Transactional
    public Object update(EntityDefinition ed, Map<String, Object> values) {
        Class<?> clazz = ((ClassEntityDefinition) ed).getClazz();
        Object entityObj = ObjectHandler.mapToObject(clazz, values);
        this.getEntityManager().merge(entityObj);
        return entityObj;
    }

    @Transactional
    public int deleteById(EntityDefinition ed, Object id) {
        Object obj = findById(ed, id);
        if (obj != null) {
            delete(ed, obj);
            return 1;
        }
        return 0;
    }

    @Transactional
    public int delete(EntityDefinition ed, Object entity) {
        this.getEntityManager().remove(entity);
        return 1;
    }
}