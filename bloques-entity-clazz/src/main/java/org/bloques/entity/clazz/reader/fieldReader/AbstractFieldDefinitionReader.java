/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-clazz
 * FileName:AbstractFieldDefinitionReader.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.clazz.reader.fieldReader;

import org.bloques.entity.clazz.annotation.BasicField;
import org.bloques.entity.clazz.annotation.DateField;
import org.bloques.entity.clazz.reader.DefinitionReader;
import org.bloques.entity.core.common.AbstractResponsibleNode;
import org.bloques.entity.core.common.ResponsibleChainResult;
import org.bloques.entity.core.definition.AbstractFieldDefinition;
import org.bloques.entity.core.definition.basicValue.AbstractBasicValueFieldDefinition;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.function.Consumer;

public abstract class AbstractFieldDefinitionReader extends AbstractResponsibleNode implements DefinitionReader<Field, AbstractFieldDefinition> {

    @Override
    public ResponsibleChainResult exec(Object parameter) {
        if (parameter instanceof Field) {
            Field field = (Field) parameter;
            AbstractFieldDefinition ed = this.read(field);
            Boolean success = ed != null;
            return new ResponsibleChainResult(success, ed);
        }
        return new ResponsibleChainResult(false, null);
    }

    protected <T extends AbstractBasicValueFieldDefinition> void read(T fieldDefinition, Field field, Consumer<BasicField> readAnnotation) {
        if (field.isAnnotationPresent(BasicField.class)) {
            BasicField annotation = field.getAnnotation(BasicField.class);
            if (StringUtils.hasLength(annotation.name())) {
                fieldDefinition.setName(annotation.name());
            }
            fieldDefinition.setAllowEmpty(annotation.allowEmpty());
            fieldDefinition.setAllowModify(annotation.allowModify());
            fieldDefinition.setDefaultValue(annotation.defaultValue());
            if (readAnnotation != null) {
                readAnnotation.accept(annotation);
            }
        }
    }

    protected <T extends AbstractBasicValueFieldDefinition> void readDate(T fieldDefinition, Field field, Consumer<DateField> readAnnotation) {
        if (field.isAnnotationPresent(DateField.class)) {
            DateField annotation = field.getAnnotation(DateField.class);
            if (StringUtils.hasLength(annotation.name())) {
                fieldDefinition.setName(annotation.name());
            }
            fieldDefinition.setAllowEmpty(annotation.allowEmpty());
            fieldDefinition.setAllowModify(annotation.allowModify());
            fieldDefinition.setDefaultValue(annotation.defaultValue());
            if (readAnnotation != null) {
                readAnnotation.accept(annotation);
            }
        }
    }
}
