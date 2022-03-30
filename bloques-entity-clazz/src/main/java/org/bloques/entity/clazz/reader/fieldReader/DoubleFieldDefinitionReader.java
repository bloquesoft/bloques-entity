/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-clazz
 * FileName:DoubleFieldDefinitionReader.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.clazz.reader.fieldReader;

import org.bloques.entity.core.definition.AbstractFieldDefinition;
import org.bloques.entity.core.definition.basicValue.DoubleFieldDefinition;

import java.lang.reflect.Field;

public class DoubleFieldDefinitionReader extends AbstractFieldDefinitionReader {

    @Override
    public AbstractFieldDefinition read(Field field) {
        if (Double.class.equals(field.getType())) {
            DoubleFieldDefinition sfd = new DoubleFieldDefinition(field.getName(), field.getName());
            super.read(sfd, field,
                    (annotation) -> {
                        sfd.setMin(annotation.minDouble());
                        sfd.setMax(annotation.maxDouble());
                        sfd.setDecimalDigit(annotation.decimalDigit());
                    });
            return sfd;
        }
        return null;
    }
}