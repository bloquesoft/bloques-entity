/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:DateTimeFieldDefinition.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.core.definition.basicValue;

import org.bloques.entity.core.common.DateUtil;

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
