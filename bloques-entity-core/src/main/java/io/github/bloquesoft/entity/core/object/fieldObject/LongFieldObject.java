/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:LongFieldObject.java
 * Author:zhangjian
 * date:2022/3/31 下午10:15
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.core.object.fieldObject;

import io.github.bloquesoft.entity.core.object.AbstractEntityObject;
import lombok.Getter;
import io.github.bloquesoft.entity.core.definition.basicValue.LongFieldDefinition;

public class LongFieldObject extends AbstractBasicFieldObject{

    @Getter
    private final LongFieldDefinition longFieldDefinition;

    public LongFieldObject(AbstractEntityObject entityObject, LongFieldDefinition longFieldDefinition) {
        super(entityObject, longFieldDefinition);
        this.longFieldDefinition = longFieldDefinition;
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
