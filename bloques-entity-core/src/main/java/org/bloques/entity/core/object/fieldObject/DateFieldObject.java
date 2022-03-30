/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:DateFieldObject.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.core.object.fieldObject;

import org.bloques.entity.core.definition.basicValue.DateFieldDefinition;
import org.bloques.entity.core.object.AbstractEntityObject;

public class DateFieldObject extends AbstractDateFieldObject {

    public final DateFieldDefinition getDateFieldDefinition(){
        return (DateFieldDefinition)super.getDateFieldDefinition();
    }

    public DateFieldObject(AbstractEntityObject entityObject, DateFieldDefinition dateFieldDefinition) {
        super(entityObject, dateFieldDefinition);
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