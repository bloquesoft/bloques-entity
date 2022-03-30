/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:LogicErrorTemplate.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.core.error.template;

import java.util.HashSet;
import java.util.Set;

public class LogicErrorTemplate extends AbstractErrorTemplate {

    private final static LogicErrorTemplate SINGLETON = new LogicErrorTemplate();

    public final static String FIELD_EMPTY_ERROR = "fieldEmptyError";

    public final static String FIELD_FORMAT_ERROR = "fieldFormatError";

    public final static String NUMBER_FIELD_RANGE_ERROR = "numberFieldRangeError";

    public final static String STRING_FIELD_RANGE_ERROR = "stringFieldRangeError";

    public final static String FIELD_VALUE_NOT_VALID_ERROR = "fieldValueNotValidError";

    public final static String FIELDS_UNIQUE_ERROR = "fieldsUniqueError";

    private final static Set<String> ALL_KEYS = new HashSet<String>() {{
        add(FIELD_EMPTY_ERROR);
        add(FIELD_FORMAT_ERROR);
        add(NUMBER_FIELD_RANGE_ERROR);
        add(STRING_FIELD_RANGE_ERROR);
        add(FIELD_VALUE_NOT_VALID_ERROR);
        add(FIELDS_UNIQUE_ERROR);
    }};

    public static final ErrorTemplateConfiguration DEFAULT = new ErrorTemplateConfiguration("i18n/logicErrorTemplate", "default", "");

    public static void importDefault() {
        SINGLETON.input(DEFAULT);
    }

    public static LogicErrorTemplate getSingleton() {
        return SINGLETON;
    }

    @Override
    public Set<String> getKeys() {
        return ALL_KEYS;
    }
}