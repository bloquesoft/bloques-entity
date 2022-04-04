/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:StringFieldRangeError.java
 * Author:zhangjian
 * date:2022/3/31 下午10:15
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.core.logic.error;

import io.github.bloquesoft.entity.core.definition.basicValue.StringFieldDefinition;
import io.github.bloquesoft.entity.core.error.template.LogicErrorTemplate;

public class StringFieldRangeError extends AbstractLogicError {

    @Override
    public String getErrorCode() {
        return LogicErrorTemplate.STRING_FIELD_RANGE_ERROR;
    }

    @Override
    protected String getErrorTemplateKey() {
        return LogicErrorTemplate.STRING_FIELD_RANGE_ERROR;
    }

    public void throwException(StringFieldDefinition fieldDefinition) {
        throw new LogicException(this.getErrorCode(),
                String.format(this.getErrorTemplate(), fieldDefinition.getName(), fieldDefinition.getMinLength(), fieldDefinition.getMaxLength()));
    }
}
