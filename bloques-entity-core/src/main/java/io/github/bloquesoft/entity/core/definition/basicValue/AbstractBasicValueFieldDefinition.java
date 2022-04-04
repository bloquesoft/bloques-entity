/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:AbstractBasicValueFieldDefinition.java
 * Author:zhangjian
 * date:2022/3/31 下午10:15
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.core.definition.basicValue;

import io.github.bloquesoft.entity.core.definition.AbstractFieldDefinition;
import io.github.bloquesoft.entity.core.definition.FieldType;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(callSuper = true)
public abstract class AbstractBasicValueFieldDefinition extends AbstractFieldDefinition {

    @Getter
    protected BasicValueType valueType;

    public AbstractBasicValueFieldDefinition(String id, String name) {
        super(id, name, FieldType.BasicValue);
    }
}