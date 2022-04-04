/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-clazz
 * FileName:IntegerFieldDefinitionReader.java
 * Author:zhangjian
 * date:2022/3/31 下午10:16
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.clazz.reader.fieldReader;

import io.github.bloquesoft.entity.core.definition.AbstractFieldDefinition;
import io.github.bloquesoft.entity.core.definition.basicValue.IntegerFieldDefinition;

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