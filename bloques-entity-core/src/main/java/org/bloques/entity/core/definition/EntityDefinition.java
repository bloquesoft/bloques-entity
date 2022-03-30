/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:EntityDefinition.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.core.definition;

import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class EntityDefinition extends AbstractObjectDefinition
{
    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private PrimaryKeyDefinition primaryKey;

    @Getter
    @Setter
    private List<FieldUniqDefinition> fieldUniqs;

    public EntityDefinition(String id, String name)
    {
        super(id, name);
        this.title = name;
    }

    public EntityDefinition(String id, String name, String title)
    {
        super(id, name);
        this.title = Preconditions.checkNotNull(title);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}