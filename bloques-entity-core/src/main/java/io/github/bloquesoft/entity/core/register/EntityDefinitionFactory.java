/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:EntityDefinitionFactory.java
 * Author:zhangjian
 * date:2022/3/31 下午10:15
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.core.register;

import io.github.bloquesoft.entity.core.definition.PackageDefinition;
import io.github.bloquesoft.entity.core.definition.EntityDefinition;

public interface EntityDefinitionFactory
{
    EntityDefinition getEntityDefinition(String entityDefinitionId);

    EntityDefinition getEntityDefinition(String packageName, String entityName);

    PackageDefinition getPackageDefinition(String name);
}