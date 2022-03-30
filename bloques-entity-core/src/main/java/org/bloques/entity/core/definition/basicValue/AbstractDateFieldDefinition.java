/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:AbstractDateFieldDefinition.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.core.definition.basicValue;

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
