/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:NumberFieldRangeError.java
 * Author:zhangjian
 * date:2022/3/31 下午10:15
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.core.logic.error;

import io.github.bloquesoft.entity.core.definition.basicValue.AbstractNumberFieldDefinition;
import io.github.bloquesoft.entity.core.error.template.LogicErrorTemplate;

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