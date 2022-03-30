/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-clazz
 * FileName:StringFieldDefinitionReader.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.clazz.reader.fieldReader;

import org.bloques.entity.core.definition.AbstractFieldDefinition;
import org.bloques.entity.core.definition.basicValue.StringFieldDefinition;

import java.lang.reflect.Field;

public class StringFieldDefinitionReader extends AbstractFieldDefinitionReader {

    @Override
    public AbstractFieldDefinition read(Field field) {
        if (String.class.equals(field.getType())) {
            StringFieldDefinition sfd = new StringFieldDefinition(field.getName(), field.getName());
            super.read(sfd, field,
                    (annotation) -> {
                        sfd.setMinLength(annotation.minLength());
                        sfd.setMaxLength(annotation.maxLength());
                    });
            return sfd;
        }
        return null;
    }
}