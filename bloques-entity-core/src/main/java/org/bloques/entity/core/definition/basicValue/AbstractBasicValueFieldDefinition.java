/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:AbstractBasicValueFieldDefinition.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.core.definition.basicValue;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.bloques.entity.core.definition.AbstractFieldDefinition;
import org.bloques.entity.core.definition.FieldType;

@EqualsAndHashCode(callSuper = true)
public abstract class AbstractBasicValueFieldDefinition extends AbstractFieldDefinition {

    @Getter
    protected BasicValueType valueType;

    public AbstractBasicValueFieldDefinition(String id, String name) {
        super(id, name, FieldType.BasicValue);
    }
}