/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-clazz
 * FileName:ClassEntityDefinitionRegistrarImpl.java
 * Author:zhangjian
 * date:2022/3/31 下午10:16
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.clazz.register;

import lombok.extern.slf4j.Slf4j;
import io.github.bloquesoft.entity.clazz.definition.ClassEntityDefinition;
import io.github.bloquesoft.entity.clazz.reader.ClassEntityDefinitionReader;
import io.github.bloquesoft.entity.core.definition.EntityDefinition;
import io.github.bloquesoft.entity.core.definition.PackageDefinition;
import io.github.bloquesoft.entity.core.register.EntityDefinitionRegister;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class ClassEntityDefinitionRegistrarImpl implements ClassEntityDefinitionRegister {

    private final static ClassEntityDefinitionReader CLASS_READER = new ClassEntityDefinitionReader();

    private final EntityDefinitionRegister entityDefinitionRegistrar;

    private final Map<Class<?>, ClassEntityDefinition> clazzContainer = new ConcurrentHashMap<>();

    public ClassEntityDefinitionRegistrarImpl(EntityDefinitionRegister entityDefinitionRegistrar) {
        this.entityDefinitionRegistrar = entityDefinitionRegistrar;
    }

    @Override
    public void register(Class<?> clazz, ClassEntityDefinition ed) {
        if (!clazzContainer.containsKey(clazz)) {
            synchronized (clazzContainer) {
                if (!clazzContainer.containsKey(clazz)) {
                    PackageDefinition pd = packageDefinition(clazz);
                    pd.addEntityDefinition(ed);
                    ed.setPackageDefinition(pd);
                    clazzContainer.put(clazz, ed);
                    log.info("Registed Class Entity " + clazz.getName());
                    this.entityDefinitionRegistrar.register(ed);
                }
            }
        }
    }

    private PackageDefinition packageDefinition(Class<?> clazz) {
        PackageDefinition pd = CLASS_READER.readPackageDefinition(clazz);
        String packageName = pd.getName();
        PackageDefinition existPd = this.entityDefinitionRegistrar.getPackageDefinition(packageName);
        if (existPd != null) {
            return existPd;
        }
        return pd;
    }

    @Override
    public boolean contain(Class<?> clazz) {
        return clazzContainer.containsKey(clazz);
    }

    @Override
    public EntityDefinition getEntityDefinition(Class clazz) {
        return clazzContainer.get(clazz);
    }

    @Override
    public ClassEntityDefinition getClassEntityDefinition(Class clazz) {

        return clazzContainer.get(clazz);
    }

    @Override
    public EntityDefinition getEntityDefinition(String id) {
        return this.entityDefinitionRegistrar.getEntityDefinition(id);
    }

    @Override
    public EntityDefinition getEntityDefinition(String packageName, String entityName) {
        return this.entityDefinitionRegistrar.getEntityDefinition(packageName, entityName);
    }

    @Override
    public PackageDefinition getPackageDefinition(String name) {
        return this.entityDefinitionRegistrar.getPackageDefinition(name);
    }

    @Override
    public void register(EntityDefinition ed) {
        this.entityDefinitionRegistrar.register(ed);
    }

    @Override
    public boolean contain(String entityDefinitionId) {
        return this.entityDefinitionRegistrar.contain(entityDefinitionId);
    }

    @Override
    public Collection<PackageDefinition> getAllPackages() {
        return this.entityDefinitionRegistrar.getAllPackages();
    }

    @Override
    public Collection<EntityDefinition> getEntities(String packageName) {
        return this.entityDefinitionRegistrar.getEntities(packageName);
    }
}