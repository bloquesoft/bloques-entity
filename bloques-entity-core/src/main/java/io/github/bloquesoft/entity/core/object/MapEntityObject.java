/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:MapEntityObject.java
 * Author:zhangjian
 * date:2022/3/31 下午10:15
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.core.object;

import io.github.bloquesoft.entity.core.definition.EntityDefinition;
import org.springframework.util.StringUtils;

import java.util.Map;

public class MapEntityObject extends AbstractEntityObject {

    public Map<String, Object> getMap() {
        return (Map) super.getValue();
    }

    public MapEntityObject(EntityDefinition entityDefinition, Map<String, Object> value) {
        super(entityDefinition, value);
    }

    @Override
    public boolean isExistOriginalField(String fieldId) {
        if (StringUtils.hasLength(fieldId))
        {
            return super.getEntityDefinition().getField(fieldId) != null ||
                    fieldId.equals(super.getEntityDefinition().getPrimaryKey().getId());
        }
        return false;
    }

    @Override
    protected Object getOriginalFieldValue(String fieldId) {
        return getMap().get(fieldId);
    }

    @Override
    protected void setOriginalFieldValue(String fieldId, Object value) {
        this.getMap().put(fieldId, value);
    }
}