/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:ListRelationShip.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.core.definition;

import lombok.Getter;
import org.springframework.util.Assert;

public class ListRelationShip {

    @Getter
    private final String associatedFieldId;

    @Getter
    private final String associatedListItemFieldId;

    public ListRelationShip(String associatedFieldId, String associatedListItemFieldId){
        Assert.hasLength(associatedFieldId);
        Assert.hasLength(associatedListItemFieldId);
        this.associatedFieldId = associatedFieldId;
        this.associatedListItemFieldId = associatedListItemFieldId;
    }
}