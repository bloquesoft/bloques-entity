/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:IntegerFieldDefinition.java
 * Author:zhangjian
 * date:2022/3/31 下午10:15
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.core.definition.basicValue;

public class IntegerFieldDefinition extends AbstractNumberFieldDefinition<Integer>{

    public IntegerFieldDefinition(String id, String name) {
        super(id, name);
        this.valueType = BasicValueType.Integer;
        this.setMin(Integer.MIN_VALUE);
        this.setMax(Integer.MAX_VALUE);
        this.setDefaultValue(0);
    }
}