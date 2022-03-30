/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:DateTimeFieldObject.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.core.object.fieldObject;

import org.bloques.entity.core.definition.basicValue.DateTimeFieldDefinition;
import org.bloques.entity.core.object.AbstractEntityObject;

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