/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:EntityLoaderImpl.java
 * Author:zhangjian
 * date:2022/3/31 下午10:15
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.core.loader.impl;

import io.github.bloquesoft.entity.core.loader.EntityLoader;
import io.github.bloquesoft.entity.core.object.fieldObject.EntityFieldObject;
import io.github.bloquesoft.entity.core.object.fieldObject.ListFieldObject;
import io.github.bloquesoft.entity.core.repository.EntityRepository;
import io.github.bloquesoft.entity.core.repository.EntityRepositoryFactory;
import io.github.bloquesoft.entity.core.definition.ListFieldDefinition;
import io.github.bloquesoft.entity.core.definition.ListRelationShip;

import java.util.LinkedHashMap;
import java.util.List;

public class EntityLoaderImpl implements EntityLoader {

    @Override
    public void loadListField(ListFieldObject lfo, EntityRepositoryFactory entityRepositoryFactory) {

        ListFieldDefinition lfd = lfo.getListFieldDefinition();
        EntityRepository repo = entityRepositoryFactory.getRepository(lfd.getListItemEntityId());
        LinkedHashMap<String, Object> params = new LinkedHashMap<>();
        for (ListRelationShip ship : lfo.getListFieldDefinition().getRelationShips()) {
            String key = ship.getAssociatedListItemFieldId();
            Object value = lfo.getEntityObject().getFieldValue(ship.getAssociatedFieldId());
            params.put(key, value);
        }
        List<Object> list = repo.findList(lfd.getListItemEntity(), params);
        lfo.setValue(list);
    }

    @Override
    public void loadEntityField(EntityFieldObject entityFieldObject, EntityRepositoryFactory entityRepositoryFactory) {
        Object associatedId = entityFieldObject.getAssociatedFieldValue();
        EntityRepository repo = entityRepositoryFactory.getRepository(entityFieldObject.getEntityFieldDefinition().getAssociatedEntityId());
        Object fieldValue = repo.findById(entityFieldObject.getEntityFieldDefinition().getAssociatedEntity(), associatedId);
        entityFieldObject.setValue(fieldValue);
    }
}