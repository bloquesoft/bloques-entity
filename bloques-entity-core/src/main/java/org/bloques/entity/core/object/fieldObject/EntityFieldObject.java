/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:EntityFieldObject.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.core.object.fieldObject;

import lombok.Getter;
import org.bloques.entity.core.definition.EntityFieldDefinition;
import org.bloques.entity.core.object.AbstractEntityObject;

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