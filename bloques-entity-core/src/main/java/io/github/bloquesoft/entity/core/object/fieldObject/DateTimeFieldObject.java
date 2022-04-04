/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:DateTimeFieldObject.java
 * Author:zhangjian
 * date:2022/3/31 下午10:15
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.core.object.fieldObject;

import io.github.bloquesoft.entity.core.object.AbstractEntityObject;
import io.github.bloquesoft.entity.core.definition.basicValue.DateTimeFieldDefinition;

public class DateTimeFieldObject extends AbstractDateFieldObject {

    public DateTimeFieldDefinition getDateTimeFieldDefinition(){
        return (DateTimeFieldDefinition)super.getDateFieldDefinition();
    }

    public DateTimeFieldObject(AbstractEntityObject entityObject, DateTimeFieldDefinition dateTimeFieldDefinition) {
        super(entityObject, dateTimeFieldDefinition);
    }

    @Override
    protected void format(FieldObjectFormatVisitor formatVisitor) {
        formatVisitor.visit(this);
    }

    @Override
    public void accept(FieldObjectVisitor visitor) {
        visitor.visit(this);
    }
}