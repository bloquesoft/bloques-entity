/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:DoubleFieldObject.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.core.object.fieldObject;

import lombok.Getter;
import org.bloques.entity.core.definition.basicValue.DoubleFieldDefinition;
import org.bloques.entity.core.object.AbstractEntityObject;

public class DoubleFieldObject extends AbstractBasicFieldObject {

    @Getter
    private final DoubleFieldDefinition doubleFieldDefinition;

    public DoubleFieldObject(AbstractEntityObject entityObject, DoubleFieldDefinition doubleFieldDefinition) {
        super(entityObject, doubleFieldDefinition);
        this.doubleFieldDefinition = doubleFieldDefinition;
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

