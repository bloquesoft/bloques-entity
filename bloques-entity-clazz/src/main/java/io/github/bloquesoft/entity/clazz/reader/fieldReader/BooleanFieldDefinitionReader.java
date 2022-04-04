/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-clazz
 * FileName:BooleanFieldDefinitionReader.java
 * Author:zhangjian
 * date:2022/3/31 下午10:16
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.clazz.reader.fieldReader;

import io.github.bloquesoft.entity.core.definition.AbstractFieldDefinition;
import io.github.bloquesoft.entity.core.definition.basicValue.BooleanFieldDefinition;

import java.lang.reflect.Field;

public class BooleanFieldDefinitionReader  extends AbstractFieldDefinitionReader {

    @Override
    public AbstractFieldDefinition read(Field field) {
        if (Boolean.class.equals(field.getType())) {
            BooleanFieldDefinition sfd = new BooleanFieldDefinition(field.getName(), field.getName());
            super.read(sfd, field, null);
            return sfd;
        }
        return null;
    }
}