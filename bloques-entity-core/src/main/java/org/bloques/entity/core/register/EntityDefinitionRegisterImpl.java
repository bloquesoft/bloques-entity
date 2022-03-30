/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:EntityDefinitionRegisterImpl.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.core.register;

import org.bloques.entity.core.definition.EntityDefinition;
import org.bloques.entity.core.definition.PackageDefinition;
import org.springframework.util.Assert;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EntityDefinitionRegisterImpl implements EntityDefinitionRegister {

    private final Map<String, EntityDefinition> entityContainer = new ConcurrentHashMap<>();

    private final Map<String, PackageDefinition> packageContainer = new ConcurrentHashMap<>();

    @Override
    public void register(EntityDefinition ed)
    {
        if (!entityContainer.containsKey(ed.getId())){
            synchronized (entityContainer) {
                if (!entityContainer.containsKey(ed.getId())) {
                    this.entityContainer.put(ed.getId(), ed);
                }
                if (!packageContainer.containsKey(ed.getPackageDefinition().getName())){
                    packageContainer.put(ed.getPackageDefinition().getName(), ed.getPackageDefinition());
                }
            }
        }
    }

    @Override
    public boolean contain(String id) {
        return entityContainer.containsKey(id);
    }

    @Override
    public EntityDefinition getEntityDefinition(String id) {
        Assert.hasLength(id, "EntityDefinitionId could not be empty.");
        Assert.isTrue(entityContainer.containsKey(id), "No such EntityDefinition id:" + id);
        return entityContainer.get(id);
    }

    @Override
    public EntityDefinition getEntityDefinition(String packageName, String entityName) {
        Assert.hasLength(packageName);
        Assert.hasLength(entityName);
        Assert.isTrue(packageContainer.containsKey(packageName), "Not such packageName:" + packageName);
        PackageDefinition pack = packageContainer.get(packageName);
        EntityDefinition entityDefinition = pack.getEntityDefinition(entityName);
        Assert.notNull(entityDefinition, "Not such EntityDefinition, packageName:" + packageName + ",entityName:" + entityName);
        return entityDefinition;
    }

    @Override
    public PackageDefinition getPackageDefinition(String name) {
        return packageContainer.get(name);
    }
}