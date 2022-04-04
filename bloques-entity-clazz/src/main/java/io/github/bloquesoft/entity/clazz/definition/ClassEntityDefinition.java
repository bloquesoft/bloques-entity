/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-clazz
 * FileName:ClassEntityDefinition.java
 * Author:zhangjian
 * date:2022/3/31 下午10:16
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.clazz.definition;

import com.google.common.base.Preconditions;
import lombok.Getter;
import io.github.bloquesoft.entity.core.definition.EntityDefinition;

public class ClassEntityDefinition extends EntityDefinition
{
    @Getter
    private final Class<?> clazz;

    public ClassEntityDefinition(Class<?> clazz) {
        super(clazz.getName(), clazz.getSimpleName());
        this.clazz = Preconditions.checkNotNull(clazz);
    }
}