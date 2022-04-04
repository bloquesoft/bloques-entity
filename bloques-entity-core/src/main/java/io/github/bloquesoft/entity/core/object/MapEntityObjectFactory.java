/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:MapEntityObjectFactory.java
 * Author:zhangjian
 * date:2022/3/31 下午10:15
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.core.object;

import io.github.bloquesoft.entity.core.definition.EntityDefinition;

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
