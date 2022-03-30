/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:FloatFieldObject.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.core.object.fieldObject;

import lombok.Getter;
import org.bloques.entity.core.definition.basicValue.FloatFieldDefinition;
import org.bloques.entity.core.object.AbstractEntityObject;

public class FloatFieldObject extends AbstractBasicFieldObject {

    @Getter
    private final FloatFieldDefinition floatFieldDefinition;

    public FloatFieldObject(AbstractEntityObject entityObject, FloatFieldDefinition floatFieldDefinition) {
        super(entityObject, floatFieldDefinition);
        this.floatFieldDefinition = floatFieldDefinition;
    }

    @Override
    protected void format(FieldObjectFormatVisitor formatVisitor) {
        formatVisitor.visit(this);
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
