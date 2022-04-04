/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:AbstractDateFieldObject.java
 * Author:zhangjian
 * date:2022/3/31 下午10:15
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.core.object.fieldObject;

import io.github.bloquesoft.entity.core.definition.basicValue.AbstractDateFieldDefinition;
import io.github.bloquesoft.entity.core.object.AbstractEntityObject;

public abstract class AbstractDateFieldObject extends AbstractBasicFieldObject {

    public AbstractDateFieldDefinition getDateFieldDefinition(){
        return (AbstractDateFieldDefinition)this.getFieldDefinition();
    }

    public AbstractDateFieldObject(AbstractEntityObject entityObject, AbstractDateFieldDefinition dateFieldDefinition) {
        super(entityObject, dateFieldDefinition);
    }

    @Override
    protected boolean isEmptyValue(Object value) {
        return value == null;
    }
}
