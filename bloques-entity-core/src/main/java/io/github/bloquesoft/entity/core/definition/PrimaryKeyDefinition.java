/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:PrimaryKeyDefinition.java
 * Author:zhangjian
 * date:2022/3/31 下午10:15
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.core.definition;

import com.sun.istack.internal.Nullable;
import io.github.bloquesoft.entity.core.definition.basicValue.AbstractBasicValueFieldDefinition;
import io.github.bloquesoft.entity.core.definition.basicValue.IntegerFieldDefinition;
import io.github.bloquesoft.entity.core.definition.basicValue.LongFieldDefinition;
import io.github.bloquesoft.entity.core.definition.basicValue.StringFieldDefinition;
import lombok.Getter;
import org.springframework.util.Assert;

public class PrimaryKeyDefinition {

    @Getter
    private final String id;

    @Getter
    private PrimaryKeyGenerationType generationType;

    @Getter
    private AbstractBasicValueFieldDefinition basicField;

    public PrimaryKeyDefinition(@Nullable AbstractBasicValueFieldDefinition basicField, @Nullable PrimaryKeyGenerationType generationType) {
        this.id = basicField.getId();
        this.basicField = basicField;
        this.generationType = generationType;
        Assert.isTrue((basicField instanceof IntegerFieldDefinition || basicField instanceof LongFieldDefinition || basicField instanceof StringFieldDefinition)
                , "BEntity field:" + basicField.getEntityDefinition().getId() + "."  + basicField.getId() + " could not be a Primary Key field, must be Long, Integer or String Type.");

        if (generationType == PrimaryKeyGenerationType.Snowflake) {
            Assert.isTrue((basicField instanceof LongFieldDefinition), "BEntity Only Long field Primary key can be generated by Snowflake Generation.");
        }
    }
}