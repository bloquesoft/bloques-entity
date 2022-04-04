/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:StringFieldObject.java
 * Author:zhangjian
 * date:2022/3/31 下午10:15
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.core.object.fieldObject;

import io.github.bloquesoft.entity.core.object.AbstractEntityObject;
import lombok.Getter;
import io.github.bloquesoft.entity.core.definition.basicValue.StringFieldDefinition;

public class StringFieldObject extends AbstractBasicFieldObject
{
    @Getter
    private final StringFieldDefinition stringFieldDefinition;

    public StringFieldObject(AbstractEntityObject entityObject, StringFieldDefinition stringFieldDefinition) {
        super(entityObject, stringFieldDefinition);
        this.stringFieldDefinition = stringFieldDefinition;
    }

    protected boolean isEmptyValue(Object value) {
        return (value == null) || ((String)value).length() == 0;
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