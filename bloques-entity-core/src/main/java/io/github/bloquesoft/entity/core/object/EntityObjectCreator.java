/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:EntityObjectCreator.java
 * Author:zhangjian
 * date:2022/3/31 下午10:15
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.core.object;

import io.github.bloquesoft.entity.core.definition.EntityDefinition;
import org.springframework.util.Assert;

import java.util.LinkedList;
import java.util.List;

public class EntityObjectCreator {

    private final List<EntityObjectFactory> factoryList = new LinkedList<>();

    private static final EntityObjectCreator SINGLETON = new EntityObjectCreator();

    public static EntityObjectCreator getSingleton(){
        return SINGLETON;
    }

    public void register(EntityObjectFactory entityObjectFactory) {
        this.factoryList.add(entityObjectFactory);
    }

    public AbstractEntityObject create(EntityDefinition entityDefinition, Object value) {
        Assert.notNull(entityDefinition);
        if (value == null) {
            return null;
        }
        for (EntityObjectFactory factory : factoryList) {
            AbstractEntityObject entityObject = factory.create(entityDefinition, value);
            if (entityObject != null) {
                return entityObject;
            }
        }
        throw new IllegalArgumentException("BEntity could not create AbstractEntityObject for value:" + value);
    }
}