/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:LongFieldDefinition.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.core.definition.basicValue;

public class LongFieldDefinition extends AbstractNumberFieldDefinition<Long> {

    public LongFieldDefinition(String id, String name) {
        super(id, name);
        this.valueType = BasicValueType.Long;
        this.setMin(Long.MIN_VALUE);
        this.setMax(Long.MAX_VALUE);
        this.setDefaultValue(0);
    }
}