/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:FieldUniqDefinition.java
 * Author:zhangjian
 * date:2022/3/31 下午10:15
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.core.definition;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import io.github.bloquesoft.entity.core.definition.basicValue.AbstractBasicValueFieldDefinition;
import org.springframework.util.Assert;

import java.util.HashSet;
import java.util.Set;

@Slf4j
public class FieldUniqDefinition
{
    @Getter
    private final EntityDefinition entityDefinition;

    @Getter
    private final Set<AbstractBasicValueFieldDefinition> uniqFields;

    public FieldUniqDefinition(EntityDefinition entityDefinition){
        this.entityDefinition = entityDefinition;
        this.uniqFields = new HashSet<>();
    }

    public void addFieldId(String fieldId)
    {
        Assert.notNull(fieldId);
        AbstractFieldDefinition fd = entityDefinition.getField(fieldId);
        Assert.isTrue((fd instanceof AbstractBasicValueFieldDefinition), "Could not find uniq basic value Field, class:" + entityDefinition.getId() + ", fieldId:" + fieldId);
        this.uniqFields.add((AbstractBasicValueFieldDefinition) fd);
    }
}