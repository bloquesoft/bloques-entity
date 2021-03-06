/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:AbstractFieldObject.java
 * Author:zhangjian
 * date:2022/3/31 下午10:15
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.core.object.fieldObject;

import com.google.common.base.Preconditions;
import io.github.bloquesoft.entity.core.object.AbstractEntityObject;
import lombok.Getter;
import io.github.bloquesoft.entity.core.definition.AbstractFieldDefinition;
import io.github.bloquesoft.entity.core.definition.EntityDefinition;

public abstract class AbstractFieldObject {

    @Getter
    private final AbstractEntityObject entityObject;

    @Getter
    private final AbstractFieldDefinition fieldDefinition;

    private final static FieldObjectFormatVisitor formatVisitor = new FieldObjectFormatVisitor();

    protected abstract void format(FieldObjectFormatVisitor formatVisitor);

    AbstractFieldObject(AbstractEntityObject entityObject, AbstractFieldDefinition fieldDefinition) {
        this.entityObject = Preconditions.checkNotNull(entityObject, "could new FieldObject with nullable entityObject");
        this.fieldDefinition = Preconditions.checkNotNull(fieldDefinition);
    }

    public String getFieldId() {
        return this.getFieldDefinition().getId();
    }

    public EntityDefinition getEntityDefinition() {
        return fieldDefinition.getEntityDefinition();
    }

    public void setDefaultValue() {
        Object value = this.getValue();
        if (isEmptyValue(value)) {
            Object defaultValue = this.getFieldDefinition().getDefaultValue();
            if (defaultValue != null) {
                setValue(defaultValue, true);
            }
        }
    }

    public Object getValue() {
        return this.getEntityObject().getFieldValue(this.getFieldId());
    }

    public void setValue(Object value) {
        this.getEntityObject().setFieldValue(this.getFieldId(), value);
    }

    public void setValue(Object value, Boolean format) {
        this.setValue(value);
        if (format) {
            this.format(formatVisitor);
        }
    }

    protected abstract boolean isEmptyValue(Object value);

    public boolean isEmptyValue() {
        return this.isEmptyValue(this.getValue());
    }

    public abstract void accept(FieldObjectVisitor visitor);
}