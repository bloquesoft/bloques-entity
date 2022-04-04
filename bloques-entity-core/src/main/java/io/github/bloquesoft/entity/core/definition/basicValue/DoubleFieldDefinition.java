/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:DoubleFieldDefinition.java
 * Author:zhangjian
 * date:2022/3/31 下午10:15
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.core.definition.basicValue;

import lombok.Getter;
import lombok.Setter;

public class DoubleFieldDefinition extends AbstractNumberFieldDefinition<Double> {

    @Getter
    @Setter
    Integer decimalDigit;

    public DoubleFieldDefinition(String id, String name) {
        super(id, name);
        this.valueType = BasicValueType.Double;
        this.setMin(Double.MIN_VALUE);
        this.setMax(Double.MAX_VALUE);
        this.setDecimalDigit(2);
        this.setDefaultValue(0);
    }
}
