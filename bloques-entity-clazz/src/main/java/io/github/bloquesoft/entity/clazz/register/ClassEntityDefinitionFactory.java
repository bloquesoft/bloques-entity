/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-clazz
 * FileName:ClassEntityDefinitionFactory.java
 * Author:zhangjian
 * date:2022/3/31 下午10:16
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.clazz.register;

import io.github.bloquesoft.entity.clazz.definition.ClassEntityDefinition;
import io.github.bloquesoft.entity.core.definition.EntityDefinition;

public interface ClassEntityDefinitionFactory
{
    EntityDefinition getEntityDefinition(Class<?> clazz);

    ClassEntityDefinition getClassEntityDefinition(Class<?> clazz);
}