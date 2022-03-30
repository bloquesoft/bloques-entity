/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:AbstractNumberFieldDefinition.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.core.definition.basicValue;

import lombok.Getter;
import lombok.Setter;

public abstract class AbstractNumberFieldDefinition<T extends Number> extends AbstractBasicValueFieldDefinition
{
    @Setter
    @Getter
    private T min;

    @Setter
    @Getter
    private T max;

    public AbstractNumberFieldDefinition(String id, String name) {
        super(id, name);
    }
}