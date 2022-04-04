/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:BooleanFieldObject.java
 * Author:zhangjian
 * date:2022/3/31 下午10:15
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.core.object.fieldObject;

import io.github.bloquesoft.entity.core.object.AbstractEntityObject;
import lombok.Getter;
import io.github.bloquesoft.entity.core.definition.basicValue.BooleanFieldDefinition;

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
