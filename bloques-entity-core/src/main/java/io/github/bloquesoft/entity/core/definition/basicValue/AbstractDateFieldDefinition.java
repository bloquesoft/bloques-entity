/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:AbstractDateFieldDefinition.java
 * Author:zhangjian
 * date:2022/3/31 下午10:15
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.core.definition.basicValue;

import lombok.Getter;
import lombok.Setter;

public abstract class AbstractDateFieldDefinition extends AbstractBasicValueFieldDefinition{

    @Getter
    @Setter
    private String dateFormat;

    public AbstractDateFieldDefinition(String id, String name) {
        super(id, name);
    }
}
