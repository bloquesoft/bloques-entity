/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:IntegerFieldRangeError.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.core.logic.error;

import org.bloques.entity.core.definition.basicValue.IntegerFieldDefinition;
import org.bloques.entity.core.error.template.LogicErrorTemplate;

public class IntegerFieldRangeError extends AbstractLogicError {

    @Override
    public String getErrorCode() {
        return LogicErrorTemplate.NUMBER_FIELD_RANGE_ERROR;
    }

    @Override
    protected String getErrorTemplateKey() {
        return LogicErrorTemplate.NUMBER_FIELD_RANGE_ERROR;
    }

    public void throwException(IntegerFieldDefinition fieldDefinition) {
        throw new LogicException(
                this.getErrorCode(),
                String.format(this.getErrorTemplate(), fieldDefinition.getName(), fieldDefinition.getMin(), fieldDefinition.getMax()));
    }
}