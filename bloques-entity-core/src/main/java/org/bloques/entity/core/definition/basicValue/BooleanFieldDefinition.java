/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:BooleanFieldDefinition.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.core.definition.basicValue;

public class BooleanFieldDefinition extends AbstractBasicValueFieldDefinition {

    public BooleanFieldDefinition(String id, String name) {
        super(id, name);
        this.valueType = BasicValueType.Boolean;
        this.setDefaultValue(false);
    }
}