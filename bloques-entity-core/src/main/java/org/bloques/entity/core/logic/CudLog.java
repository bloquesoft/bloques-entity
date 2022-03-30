/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:CudLog.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.core.logic;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.bloques.entity.core.common.MapUtil;
import org.bloques.entity.core.definition.EntityDefinition;
import org.bloques.entity.core.object.AbstractEntityObject;
import org.springframework.util.Assert;

import java.util.*;

@Slf4j
public class CudLog {

    @Getter
    private final Map<EntityDefinition, Set<AbstractEntityObject>> appendMapSet = new HashMap<>();

    @Getter
    private final Map<EntityDefinition, Set<AbstractEntityObject>> updateMapSet = new HashMap<>();

    @Getter
    private final Map<EntityDefinition, Set<AbstractEntityObject>> deleteMapSet = new HashMap<>();

    private final Map<Object, AbstractEntityObject> appnedMap = new HashMap<>();

    private final Map<Object, AbstractEntityObject> updateMap = new HashMap<>();

    public Collection<AbstractEntityObject> getAppendEntityObjects() {
        return appnedMap.values();
    }

    public Map<Object, AbstractEntityObject> getUpdateMap() {
        return updateMap;
    }

    public void appendEntity(AbstractEntityObject entityObj) {
        Assert.notNull(entityObj);
        Object pk = entityObj.getPkValue();
        Assert.notNull(pk, "Could not append entity with nullable Pk, Entity definition:" + entityObj.getEntityDefinition());
        appnedMap.put(pk, entityObj);
        Set<AbstractEntityObject> set = MapUtil.getSetByKey(appendMapSet, entityObj.getEntityDefinition());
        set.add(entityObj);
        log.debug("CudLog appendEntity:" + entityObj);
    }

    public void updateField(AbstractEntityObject entityObj) {
        Assert.notNull(entityObj);
        Object pk = entityObj.getPkValue();
        Assert.notNull(pk, "Could not append entity with nullable Pk, Entity definition:" + entityObj.getEntityDefinition());
        updateMap.put(pk, entityObj);
        Set<AbstractEntityObject> set = MapUtil.getSetByKey(updateMapSet, entityObj.getEntityDefinition());
        set.add(entityObj);
        log.debug("CudLog updateEntity:" + entityObj);
    }

    public void deleteEntity(AbstractEntityObject entityObj) {
        Assert.notNull(entityObj);
        Set<AbstractEntityObject> set = MapUtil.getSetByKey(deleteMapSet, entityObj.getEntityDefinition());
        set.add(entityObj);
        log.debug("CudLog deleteEntity:" + entityObj);
    }

    public boolean isValidPk(EntityDefinition ed, Object pk) {
        if (appnedMap.containsKey(pk)) {
            AbstractEntityObject entityObj = appnedMap.get(pk);
            return entityObj.getEntityDefinition().equals(ed);
        }
        return false;
    }

    public List<AbstractEntityObject> findEntityList(EntityDefinition ed, Map<String, Object> args) {
        List<AbstractEntityObject> list = new LinkedList<>();
        if (appendMapSet.containsKey(ed)){
            list.addAll(findEntity(appendMapSet.get(ed), args));
        }
        if (updateMapSet.containsKey(ed)){
            list.addAll(findEntity(updateMapSet.get(ed), args));
        }
        return list;
    }

    private List<AbstractEntityObject> findEntity(Set<AbstractEntityObject> set, Map<String, Object> args) {
        List<AbstractEntityObject> list = new LinkedList<>();
        for (AbstractEntityObject entityObject : set) {
            boolean matched = true;
            for (Map.Entry<String, Object> entry : args.entrySet()) {
                String fieldId = entry.getKey();
                Object fieldValue = entry.getValue();
                if (!entityObject.getFieldObject(fieldId).equals(fieldValue)) {
                    matched = false;
                    break;
                }
            }
            if (matched){
                list.add(entityObject);
            }
        }
        return list;
    }
}