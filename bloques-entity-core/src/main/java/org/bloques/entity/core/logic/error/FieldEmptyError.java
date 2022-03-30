/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:FieldEmptyError.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.core.logic.error;

import org.bloques.entity.core.definition.AbstractFieldDefinition;
import org.bloques.entity.core.error.template.LogicErrorTemplate;

public class FieldEmptyError extends AbstractLogicError
{
    @Override
    public String getErrorCode(){
        return LogicErrorTemplate.FIELD_EMPTY_ERROR;
    }

    @Override
    protected String getErrorTemplateKey() {
        return LogicErrorTemplate.FIELD_EMPTY_ERROR;
    }

    public void throwException(AbstractFieldDefinition fieldDefinition) {
        throw new LogicException(
                this.getErrorCode(),
                String.format(this.getErrorTemplate(), fieldDefinition.getName()));
    }
}