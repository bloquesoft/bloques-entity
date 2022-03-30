/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:AbstractBasicFieldObject.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.core.object.fieldObject;

import org.bloques.entity.core.definition.basicValue.AbstractBasicValueFieldDefinition;
import org.bloques.entity.core.object.AbstractEntityObject;

public abstract class AbstractBasicFieldObject extends AbstractFieldObject {

    private final static FieldObjectFormatVisitor FORMAT_VISITOR = new FieldObjectFormatVisitor();

    public AbstractBasicValueFieldDefinition getFieldDefinition()
    {
        return (AbstractBasicValueFieldDefinition)super.getFieldDefinition();
    }

    AbstractBasicFieldObject(AbstractEntityObject entityObject, AbstractBasicValueFieldDefinition fieldDefinition) {
        super(entityObject, fieldDefinition);
        this.format(FORMAT_VISITOR);
    }
}