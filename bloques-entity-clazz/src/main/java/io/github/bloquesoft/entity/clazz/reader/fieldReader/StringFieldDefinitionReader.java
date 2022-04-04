/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-clazz
 * FileName:StringFieldDefinitionReader.java
 * Author:zhangjian
 * date:2022/3/31 下午10:16
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.clazz.reader.fieldReader;

import io.github.bloquesoft.entity.core.definition.AbstractFieldDefinition;
import io.github.bloquesoft.entity.core.definition.basicValue.StringFieldDefinition;

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