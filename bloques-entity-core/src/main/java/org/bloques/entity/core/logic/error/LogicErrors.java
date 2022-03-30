/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:LogicErrors.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.core.logic.error;

public class LogicErrors
{
    public final static FieldEmptyError FIELD_EMPTY_ERROR = new FieldEmptyError();

    public static final FieldValueNotValidError FIELD_VALUE_NOT_VALID_ERROR = new FieldValueNotValidError();

    public final static NumberFieldRangeError LONG_FIELD_RANGE_ERROR = new NumberFieldRangeError();

    public final static IntegerFieldRangeError INTEGER_FIELD_RANGE_ERROR = new IntegerFieldRangeError();

    public final static StringFieldRangeError STRING_FIELD_RANGE_ERROR = new StringFieldRangeError();

    public final static FieldUniqueError FIELD_UNIQUE_ERROR = new FieldUniqueError();
}