/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:StringFieldObject.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.core.object.fieldObject;

import lombok.Getter;
import org.bloques.entity.core.definition.basicValue.StringFieldDefinition;
import org.bloques.entity.core.object.AbstractEntityObject;

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