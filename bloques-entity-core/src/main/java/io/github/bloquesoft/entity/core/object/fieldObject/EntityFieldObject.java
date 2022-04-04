/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:EntityFieldObject.java
 * Author:zhangjian
 * date:2022/3/31 下午10:15
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.core.object.fieldObject;

import io.github.bloquesoft.entity.core.object.AbstractEntityObject;
import lombok.Getter;
import io.github.bloquesoft.entity.core.definition.EntityFieldDefinition;

public class EntityFieldObject extends AbstractFieldObject
{
    @Getter
    private final EntityFieldDefinition entityFieldDefinition;

    @Getter
    private Boolean isLoaded;

    public EntityFieldObject(AbstractEntityObject entityObject, EntityFieldDefinition entityFieldDefinition) {
        super(entityObject, entityFieldDefinition);
        this.entityFieldDefinition = entityFieldDefinition;
        this.isLoaded = super.getValue() != null;
    }

    @Override
    protected void format(FieldObjectFormatVisitor formatVisitor) {
        formatVisitor.visit(this);
    }

    @Override
    public void setValue(Object value) {
        super.setValue(value);
        this.isLoaded = true;
    }

    public Object getAssociatedFieldValue(){
        String fieldId = this.entityFieldDefinition.getAssociatedFieldId();
        return getEntityObject().getFieldValue(fieldId);
    }

    @Override
    protected boolean isEmptyValue(Object value) {
        return value == null;
    }

    @Override
    public void accept(FieldObjectVisitor visitor) {
        visitor.visit(this);
    }
}