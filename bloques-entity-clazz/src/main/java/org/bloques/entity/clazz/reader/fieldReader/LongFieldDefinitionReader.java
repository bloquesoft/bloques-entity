/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-clazz
 * FileName:LongFieldDefinitionReader.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.clazz.reader.fieldReader;

import org.bloques.entity.core.definition.AbstractFieldDefinition;
import org.bloques.entity.core.definition.basicValue.LongFieldDefinition;

import java.lang.reflect.Field;

public class LongFieldDefinitionReader extends AbstractFieldDefinitionReader {

    @Override
    public AbstractFieldDefinition read(Field field) {
        if (Long.class.equals(field.getType())) {
            LongFieldDefinition sfd = new LongFieldDefinition(field.getName(), field.getName());
            super.read(sfd, field,
                    (annotation) -> {
                        sfd.setMin(annotation.minLong());
                        sfd.setMax(annotation.maxLong());
                    });
            return sfd;
        }
        return null;
    }
}