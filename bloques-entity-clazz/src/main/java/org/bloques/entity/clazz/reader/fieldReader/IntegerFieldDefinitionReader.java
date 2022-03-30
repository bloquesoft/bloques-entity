/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-clazz
 * FileName:IntegerFieldDefinitionReader.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.clazz.reader.fieldReader;

import org.bloques.entity.core.definition.AbstractFieldDefinition;
import org.bloques.entity.core.definition.basicValue.IntegerFieldDefinition;

import java.lang.reflect.Field;

public class IntegerFieldDefinitionReader extends AbstractFieldDefinitionReader {

    @Override
    public AbstractFieldDefinition read(Field field) {
        if (Integer.class.equals(field.getType())) {
            IntegerFieldDefinition sfd = new IntegerFieldDefinition(field.getName(), field.getName());
            super.read(sfd, field,
                    (annotation) -> {
                        sfd.setMin(annotation.minInteger());
                        sfd.setMax(annotation.maxInteger());
                    });
            return sfd;
        }
        return null;
    }
}