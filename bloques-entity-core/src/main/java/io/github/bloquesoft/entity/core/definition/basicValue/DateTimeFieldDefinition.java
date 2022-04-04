/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:DateTimeFieldDefinition.java
 * Author:zhangjian
 * date:2022/3/31 下午10:15
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.core.definition.basicValue;

import io.github.bloquesoft.entity.core.common.DateUtil;

import java.util.Date;

public class DateTimeFieldDefinition extends AbstractDateFieldDefinition {

    private static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public DateTimeFieldDefinition(String id, String name) {
        super(id, name);
        this.valueType = BasicValueType.DateTime;
        this.setDateFormat(DEFAULT_DATETIME_FORMAT);
        this.setDefaultValue(DateUtil.formatToDate(new Date()));
    }
}
