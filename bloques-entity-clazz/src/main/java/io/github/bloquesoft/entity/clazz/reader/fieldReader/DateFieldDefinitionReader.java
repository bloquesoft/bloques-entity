/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-clazz
 * FileName:DateFieldDefinitionReader.java
 * Author:zhangjian
 * date:2022/3/31 下午10:16
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.clazz.reader.fieldReader;

import io.github.bloquesoft.entity.clazz.annotation.DateField;
import io.github.bloquesoft.entity.core.definition.AbstractFieldDefinition;
import io.github.bloquesoft.entity.core.definition.basicValue.DateFieldDefinition;
import io.github.bloquesoft.entity.core.definition.basicValue.DateTimeFieldDefinition;

import java.lang.reflect.Field;
import java.util.Date;

public class DateFieldDefinitionReader extends AbstractFieldDefinitionReader {

    @Override
    public AbstractFieldDefinition read(Field field) {
        if (Date.class.equals(field.getType())) {
            if (field.isAnnotationPresent(DateField.class)) {
                DateField dfAnnotation = field.getAnnotation(DateField.class);
                if (dfAnnotation.isDateTime()) {
                    DateTimeFieldDefinition dtfDef = new DateTimeFieldDefinition(field.getName(), field.getName());
                    super.readDate(dtfDef, field,
                            (annotation) -> dtfDef.setDateFormat(annotation.dateTimeFormat()));
                    return dtfDef;
                } else {
                    DateFieldDefinition dfDef = new DateFieldDefinition(field.getName(), field.getName());
                    super.readDate(dfDef, field,
                            (annotation) -> dfDef.setDateFormat(annotation.dateFormat()));
                    return dfDef;
                }
            } else {
                DateFieldDefinition dfDef = new DateFieldDefinition(field.getName(), field.getName());
                return dfDef;
            }

        }
        return null;
    }
}
