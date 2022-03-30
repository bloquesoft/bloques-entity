/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:EntityDefinitionFactory.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.core.register;

import org.bloques.entity.core.definition.PackageDefinition;
import org.bloques.entity.core.definition.EntityDefinition;

public interface EntityDefinitionFactory
{
    EntityDefinition getEntityDefinition(String entityDefinitionId);

    EntityDefinition getEntityDefinition(String packageName, String entityName);

    PackageDefinition getPackageDefinition(String name);
}