/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:PrimaryKeyDefinition.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.core.definition;

import lombok.Data;
import org.springframework.util.Assert;

@Data
public class PrimaryKeyDefinition
{
    private final String id;

    public PrimaryKeyDefinition(String id)
    {
        Assert.hasLength(id);
        this.id = id;
    }
}