/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:NumberFieldRangeError.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.core.logic.error;

import org.bloques.entity.core.definition.basicValue.AbstractNumberFieldDefinition;
import org.bloques.entity.core.error.template.LogicErrorTemplate;

public class NumberFieldRangeError extends AbstractLogicError {

    @Override
    public String getErrorCode() {
        return LogicErrorTemplate.NUMBER_FIELD_RANGE_ERROR;
    }

    @Override
    protected String getErrorTemplateKey() {
        return LogicErrorTemplate.NUMBER_FIELD_RANGE_ERROR;
    }

    public void throwException(AbstractNumberFieldDefinition<?> numberFieldDefinition) {
        throw new LogicException(this.getErrorCode(),
                String.format(this.getErrorTemplate(), numberFieldDefinition.getName(), numberFieldDefinition.getMin(), numberFieldDefinition.getMax()));
    }
}