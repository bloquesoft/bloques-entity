/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-clazz
 * FileName:FloatFieldDefinitionReader.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.clazz.reader.fieldReader;

import org.bloques.entity.core.definition.AbstractFieldDefinition;
import org.bloques.entity.core.definition.basicValue.FloatFieldDefinition;

import java.lang.reflect.Field;

public class FloatFieldDefinitionReader extends AbstractFieldDefinitionReader {

    @Override
    public AbstractFieldDefinition read(Field field) {
        if (Float.class.equals(field.getType())) {
            FloatFieldDefinition sfd = new FloatFieldDefinition(field.getName(), field.getName());
            super.read(sfd, field,
                    (annotation) -> {
                        sfd.setMin(annotation.minFloat());
                        sfd.setMax(annotation.maxFloat());
                        sfd.setDecimalDigit(annotation.decimalDigit());
                    });
            return sfd;
        }
        return null;
    }
}