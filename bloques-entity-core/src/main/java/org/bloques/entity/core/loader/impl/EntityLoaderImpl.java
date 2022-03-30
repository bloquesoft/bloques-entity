/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:EntityLoaderImpl.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.core.loader.impl;

import org.bloques.entity.core.definition.ListFieldDefinition;
import org.bloques.entity.core.definition.ListRelationShip;
import org.bloques.entity.core.loader.EntityLoader;
import org.bloques.entity.core.object.fieldObject.EntityFieldObject;
import org.bloques.entity.core.object.fieldObject.ListFieldObject;
import org.bloques.entity.core.repository.EntityRepository;
import org.bloques.entity.core.repository.EntityRepositoryFactory;

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