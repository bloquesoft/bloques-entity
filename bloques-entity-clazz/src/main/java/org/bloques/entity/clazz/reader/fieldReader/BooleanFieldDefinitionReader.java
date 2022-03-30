/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-clazz
 * FileName:BooleanFieldDefinitionReader.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.clazz.reader.fieldReader;

import org.bloques.entity.core.definition.AbstractFieldDefinition;
import org.bloques.entity.core.definition.basicValue.BooleanFieldDefinition;

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