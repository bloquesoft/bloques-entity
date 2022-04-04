/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:FloatFieldDefinition.java
 * Author:zhangjian
 * date:2022/3/31 下午10:15
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.core.definition.basicValue;

import lombok.Getter;
import lombok.Setter;

public class FloatFieldDefinition extends AbstractNumberFieldDefinition<Float> {

    @Getter
    @Setter
    Integer decimalDigit;

    public FloatFieldDefinition(String id, String name) {
        super(id, name);
        this.valueType = BasicValueType.Float;
        this.setMin(Float.MIN_VALUE);
        this.setMax(Float.MAX_VALUE);
        this.setDecimalDigit(2);
        this.setDefaultValue(0);
    }
}
