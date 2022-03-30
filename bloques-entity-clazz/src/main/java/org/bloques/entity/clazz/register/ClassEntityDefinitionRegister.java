/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-clazz
 * FileName:ClassEntityDefinitionRegister.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.clazz.register;

import org.bloques.entity.clazz.definition.ClassEntityDefinition;
import org.bloques.entity.core.register.EntityDefinitionRegister;

public interface ClassEntityDefinitionRegister extends ClassEntityDefinitionFactory, EntityDefinitionRegister {

    void register(Class<?> clazz, ClassEntityDefinition ed);

    boolean contain(Class<?> clazz);
}