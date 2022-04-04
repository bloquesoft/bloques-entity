/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:BooleanFieldDefinition.java
 * Author:zhangjian
 * date:2022/3/31 下午10:15
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.core.definition.basicValue;

public class BooleanFieldDefinition extends AbstractBasicValueFieldDefinition {

    public BooleanFieldDefinition(String id, String name) {
        super(id, name);
        this.valueType = BasicValueType.Boolean;
        this.setDefaultValue(false);
    }
}
