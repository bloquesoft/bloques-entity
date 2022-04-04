/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:StringFieldDefinition.java
 * Author:zhangjian
 * date:2022/3/31 下午10:15
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.core.definition.basicValue;

import lombok.Getter;
import lombok.Setter;

public class StringFieldDefinition extends AbstractBasicValueFieldDefinition
{
    @Getter
    @Setter
    Integer minLength;

    @Getter
    @Setter
    Integer maxLength;

    public StringFieldDefinition(String id, String name)
    {
        super(id, name);
        this.valueType = BasicValueType.String;
        this.minLength = 0;
        this.maxLength = -1;
        this.setDefaultValue("");
    }
}