/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-clazz
 * FileName:ClsEntityObjectFactory.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.clazz.object;

import org.bloques.entity.core.definition.EntityDefinition;
import org.bloques.entity.core.object.AbstractEntityObject;
import org.bloques.entity.core.object.EntityObjectFactory;

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