/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-clazz
 * FileName:ClassEntityDefinitionFactory.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.clazz.register;

import org.bloques.entity.clazz.definition.ClassEntityDefinition;
import org.bloques.entity.core.definition.EntityDefinition;

public interface ClassEntityDefinitionFactory
{
    EntityDefinition getEntityDefinition(Class<?> clazz);

    ClassEntityDefinition getClassEntityDefinition(Class<?> clazz);
}