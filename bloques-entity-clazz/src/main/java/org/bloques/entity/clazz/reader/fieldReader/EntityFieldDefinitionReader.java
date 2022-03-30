/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-clazz
 * FileName:EntityFieldDefinitionReader.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.clazz.reader.fieldReader;

import lombok.extern.slf4j.Slf4j;
import org.bloques.entity.clazz.annotation.EntityField;
import org.bloques.entity.clazz.reader.DefinitionReader;
import org.bloques.entity.core.common.AbstractResponsibleNode;
import org.bloques.entity.core.common.ResponsibleChainResult;
import org.bloques.entity.core.definition.AbstractFieldDefinition;
import org.bloques.entity.core.definition.EntityFieldDefinition;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;

@Slf4j
public class EntityFieldDefinitionReader extends AbstractResponsibleNode implements DefinitionReader<Field, AbstractFieldDefinition> {

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

    @Override
    public AbstractFieldDefinition read(Field field) {
        if (field.isAnnotationPresent(EntityField.class)) {
            EntityField annotation = field.getAnnotation(EntityField.class);
            String fieldId = field.getName();
            String fieldName = field.getName();
            String associatedFieldId = annotation.associatedFieldId();
            if (StringUtils.hasLength(annotation.name())) {
                fieldName = annotation.name();
            }
            Assert.hasLength(associatedFieldId, "Class " + field.getDeclaringClass() + " must set associatedFieldId for FieldDefinition");
            return new EntityFieldDefinition(fieldId, fieldName, associatedFieldId);
        }
        return null;
    }
}
