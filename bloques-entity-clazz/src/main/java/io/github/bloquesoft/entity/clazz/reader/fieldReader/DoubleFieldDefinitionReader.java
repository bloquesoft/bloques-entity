/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-clazz
 * FileName:DoubleFieldDefinitionReader.java
 * Author:zhangjian
 * date:2022/3/31 下午10:16
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.clazz.reader.fieldReader;

import io.github.bloquesoft.entity.core.definition.AbstractFieldDefinition;
import io.github.bloquesoft.entity.core.definition.basicValue.DoubleFieldDefinition;

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