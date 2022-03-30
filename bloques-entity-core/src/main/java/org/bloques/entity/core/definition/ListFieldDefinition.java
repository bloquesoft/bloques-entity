/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:ListFieldDefinition.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.core.definition;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
public class ListFieldDefinition extends AbstractFieldDefinition
{
    @Setter
    @Getter
    private String listItemEntityId;

    @Getter
    private EntityDefinition listItemEntity;

    public void setListItemEntity(EntityDefinition listItemEntity)
    {
        Assert.notNull(listItemEntity);
        this.listItemEntity = listItemEntity;
        this.listItemEntityId = listItemEntity.getId();
    }

    @Getter
    private final List<ListRelationShip> relationShips;

    public ListFieldDefinition(String id, String name, List<ListRelationShip> relationShips) {
        super(id, name, FieldType.List);
        this.relationShips = relationShips;
    }
}