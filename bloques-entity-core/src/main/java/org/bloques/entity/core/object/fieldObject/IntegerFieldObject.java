/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:IntegerFieldObject.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.core.object.fieldObject;

import lombok.Getter;
import org.bloques.entity.core.definition.basicValue.IntegerFieldDefinition;
import org.bloques.entity.core.object.AbstractEntityObject;

public class IntegerFieldObject extends AbstractBasicFieldObject {

    @Getter
    private final IntegerFieldDefinition integerFieldDefinition;

    public IntegerFieldObject(AbstractEntityObject entityObject, IntegerFieldDefinition integerFieldDefinition) {
        super(entityObject, integerFieldDefinition);
        this.integerFieldDefinition = integerFieldDefinition;
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
