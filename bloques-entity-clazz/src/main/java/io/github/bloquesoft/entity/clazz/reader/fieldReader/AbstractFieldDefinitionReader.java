/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-clazz
 * FileName:AbstractFieldDefinitionReader.java
 * Author:zhangjian
 * date:2022/3/31 下午10:16
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.clazz.reader.fieldReader;

import io.github.bloquesoft.entity.clazz.reader.DefinitionReader;
import io.github.bloquesoft.entity.clazz.annotation.BasicField;
import io.github.bloquesoft.entity.clazz.annotation.DateField;
import io.github.bloquesoft.entity.core.common.AbstractResponsibleNode;
import io.github.bloquesoft.entity.core.common.ResponsibleChainResult;
import io.github.bloquesoft.entity.core.definition.AbstractFieldDefinition;
import io.github.bloquesoft.entity.core.definition.basicValue.AbstractBasicValueFieldDefinition;
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
