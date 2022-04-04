/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:EntityDefinitionRegister.java
 * Author:zhangjian
 * date:2022/3/31 下午10:15
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.core.register;

import io.github.bloquesoft.entity.core.definition.EntityDefinition;
import io.github.bloquesoft.entity.core.definition.PackageDefinition;

import java.util.Collection;

public interface EntityDefinitionRegister extends EntityDefinitionFactory
{
    void register(EntityDefinition ed);

    boolean contain(String id);

    Collection<PackageDefinition> getAllPackages();

    Collection<EntityDefinition> getEntities(String packageName);
}