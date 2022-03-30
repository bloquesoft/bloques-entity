/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:MapEntityObjectFactory.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.core.object;

import org.bloques.entity.core.definition.EntityDefinition;

import java.util.Map;

public class MapEntityObjectFactory implements EntityObjectFactory {

    @Override
    public AbstractEntityObject create(EntityDefinition entityDefinition, Object value) {

        if (value instanceof Map)
        {
            return new MapEntityObject(entityDefinition, (Map) value);
        }
        return null;
    }
}
