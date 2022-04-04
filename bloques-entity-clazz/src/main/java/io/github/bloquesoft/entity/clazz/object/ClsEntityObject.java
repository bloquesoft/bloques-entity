/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-clazz
 * FileName:ClsEntityObject.java
 * Author:zhangjian
 * date:2022/3/31 下午10:16
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.clazz.object;

import io.github.bloquesoft.entity.clazz.common.ObjectHandler;
import io.github.bloquesoft.entity.core.definition.EntityDefinition;
import io.github.bloquesoft.entity.core.object.AbstractEntityObject;

public class ClsEntityObject extends AbstractEntityObject
{
    public ClsEntityObject(EntityDefinition entityDefinition, Object value) {
        super(entityDefinition, value);
    }

    @Override
    public boolean isExistOriginalField(String fieldId) {
        return ObjectHandler.containField(super.getValue(), fieldId);
    }

    @Override
    protected Object getOriginalFieldValue(String fieldId)
    {
        return ObjectHandler.getFieldValue(super.getValue(), fieldId);
    }

    @Override
    protected void setOriginalFieldValue(String fieldId, Object value)
    {
        ObjectHandler.setFieldValue(super.getValue(), fieldId, value);
    }

    @Override
    public String toString() {
        return "entityDefinition" + this.getEntityDefinition().toString() + "," + "value:" + this.getValue().toString();
    }
}