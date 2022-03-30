/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:EntityFieldDefinition.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.core.definition;

import lombok.Getter;
import org.springframework.util.Assert;

public class EntityFieldDefinition extends AbstractFieldDefinition
{
    @Getter
    private final String associatedFieldId;

    @Getter
    private String associatedEntityId;

    @Getter
    private EntityDefinition associatedEntity;

    public EntityFieldDefinition(String id, String name, String associatedFieldId) {
        super(id, name, FieldType.Entity);
        Assert.hasLength(associatedFieldId);
        this.associatedFieldId = associatedFieldId;
    }

    public void setAssociatedEntity(EntityDefinition associatedEntity)
    {
        Assert.notNull(associatedEntity);
        this.associatedEntity = associatedEntity;
        this.associatedEntityId = associatedEntity.getId();
    }

    public AbstractFieldDefinition getAssociatedField()
    {
        return this.getEntityDefinition().getField(this.getAssociatedFieldId());
    }
}