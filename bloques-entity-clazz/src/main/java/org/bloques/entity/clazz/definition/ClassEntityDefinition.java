/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-clazz
 * FileName:ClassEntityDefinition.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.clazz.definition;

import com.google.common.base.Preconditions;
import lombok.Getter;
import org.bloques.entity.core.definition.EntityDefinition;

public class ClassEntityDefinition extends EntityDefinition
{
    @Getter
    private final Class<?> clazz;

    public ClassEntityDefinition(Class<?> clazz) {
        super(clazz.getName(), clazz.getSimpleName());
        this.clazz = Preconditions.checkNotNull(clazz);
    }
}