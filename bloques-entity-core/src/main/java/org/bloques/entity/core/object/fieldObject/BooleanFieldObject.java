/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:BooleanFieldObject.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.core.object.fieldObject;

import lombok.Getter;
import org.bloques.entity.core.definition.basicValue.BooleanFieldDefinition;
import org.bloques.entity.core.object.AbstractEntityObject;

public class BooleanFieldObject extends AbstractBasicFieldObject {

    @Getter
    private final BooleanFieldDefinition booleanFieldDefinition;

    public BooleanFieldObject(AbstractEntityObject entityObject, BooleanFieldDefinition booleanFieldDefinition) {
        super(entityObject, booleanFieldDefinition);
        this.booleanFieldDefinition = booleanFieldDefinition;
    }

    @Override
    protected boolean isEmptyValue(Object value) {
        return value == null;
    }

    @Override
    public void accept(FieldObjectVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    protected void format(FieldObjectFormatVisitor formatVisitor) {
        formatVisitor.visit(this);
    }
}
