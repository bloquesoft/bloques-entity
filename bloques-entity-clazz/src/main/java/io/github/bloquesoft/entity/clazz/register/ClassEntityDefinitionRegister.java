/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-clazz
 * FileName:ClassEntityDefinitionRegister.java
 * Author:zhangjian
 * date:2022/3/31 下午10:16
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.clazz.register;

import io.github.bloquesoft.entity.clazz.definition.ClassEntityDefinition;
import io.github.bloquesoft.entity.core.register.EntityDefinitionRegister;

public interface ClassEntityDefinitionRegister extends ClassEntityDefinitionFactory, EntityDefinitionRegister {

    void register(Class<?> clazz, ClassEntityDefinition ed);

    boolean contain(Class<?> clazz);
}