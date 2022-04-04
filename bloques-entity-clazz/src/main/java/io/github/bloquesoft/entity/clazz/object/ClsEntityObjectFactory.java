/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-clazz
 * FileName:ClsEntityObjectFactory.java
 * Author:zhangjian
 * date:2022/3/31 下午10:16
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.clazz.object;

import io.github.bloquesoft.entity.core.definition.EntityDefinition;
import io.github.bloquesoft.entity.core.object.AbstractEntityObject;
import io.github.bloquesoft.entity.core.object.EntityObjectFactory;

public class ClsEntityObjectFactory implements EntityObjectFactory {

    @Override
    public AbstractEntityObject create(EntityDefinition entityDefinition, Object value) {

        if (value != null)
        {
            return new ClsEntityObject(entityDefinition, value);
        }
        return null;
    }
}