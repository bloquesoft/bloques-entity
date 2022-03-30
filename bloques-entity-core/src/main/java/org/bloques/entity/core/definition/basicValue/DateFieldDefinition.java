
/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:DateFieldDefinition.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.core.definition.basicValue;

import org.bloques.entity.core.common.DateUtil;

import java.util.Date;

public class DateFieldDefinition extends  AbstractDateFieldDefinition{

    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

    public DateFieldDefinition(String id, String name) {
        super(id, name);
        this.valueType = BasicValueType.Date;
        this.setDateFormat(DEFAULT_DATE_FORMAT);
        this.setDefaultValue(DateUtil.formatToDate(new Date()));
    }
}
