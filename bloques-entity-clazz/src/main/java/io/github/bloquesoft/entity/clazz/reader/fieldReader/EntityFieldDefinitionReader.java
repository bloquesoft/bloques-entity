/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-clazz
 * FileName:EntityFieldDefinitionReader.java
 * Author:zhangjian
 * date:2022/3/31 下午10:16
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.clazz.reader.fieldReader;

import io.github.bloquesoft.entity.clazz.reader.DefinitionReader;
import lombok.extern.slf4j.Slf4j;
import io.github.bloquesoft.entity.clazz.annotation.EntityField;
import io.github.bloquesoft.entity.core.common.AbstractResponsibleNode;
import io.github.bloquesoft.entity.core.common.ResponsibleChainResult;
import io.github.bloquesoft.entity.core.definition.AbstractFieldDefinition;
import io.github.bloquesoft.entity.core.definition.EntityFieldDefinition;
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
