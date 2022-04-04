/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:FieldUniqueError.java
 * Author:zhangjian
 * date:2022/3/31 下午10:15
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.core.logic.error;

import io.github.bloquesoft.entity.core.definition.AbstractFieldDefinition;
import io.github.bloquesoft.entity.core.definition.FieldUniqDefinition;
import io.github.bloquesoft.entity.core.error.template.LogicErrorTemplate;
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Collectors;

public class FieldUniqueError extends AbstractLogicError
{
    @Override
    public String getErrorCode(){
        return LogicErrorTemplate.FIELDS_UNIQUE_ERROR;
    }

    @Override
    protected String getErrorTemplateKey() {
        return LogicErrorTemplate.FIELDS_UNIQUE_ERROR;
    }

    public void throwException(FieldUniqDefinition fieldUniqDefinition)
    {
        List<String> fieldIdList =  fieldUniqDefinition.getUniqFields().stream().map(AbstractFieldDefinition::getId).collect(Collectors.toList());
        String[] fieldNames = new String[fieldIdList.size()];
        for(int i=0;i< fieldIdList.size();i++)
        {
            AbstractFieldDefinition fieldDefinition = fieldUniqDefinition.getEntityDefinition().getField(fieldIdList.get(i));
            Assert.notNull(fieldDefinition, "");
            fieldNames[i] = fieldDefinition.getName();
        }
        throw new LogicException(this.getErrorCode(), String.format(this.getErrorTemplate(), String.join(",", fieldNames)));
    }
}
