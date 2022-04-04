/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:AbstractFieldDefinition.java
 * Author:zhangjian
 * date:2022/3/31 下午10:15
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.core.definition;

import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

public abstract class AbstractFieldDefinition
{
    @Getter
    private final String id;

    @Getter
    @Setter
    private String name;

    @Getter
    private final FieldType type;

    @Getter
    @Setter
    private Boolean allowModify;

    @Getter
    @Setter
    private Boolean allowEmpty;

    @Setter
    @Getter
    private Object defaultValue;

    @Getter
    @Setter
    private EntityDefinition entityDefinition;

    public AbstractFieldDefinition(String id, String name, FieldType type) {
        this.id = Preconditions.checkNotNull(id, "init argument id could not be null");
        this.name = Preconditions.checkNotNull(name, "init argument name could not be null");
        this.type = Preconditions.checkNotNull(type, "int argument type could not be null");
        this.allowEmpty = true;
        this.allowModify = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractFieldDefinition)) return false;
        AbstractFieldDefinition that = (AbstractFieldDefinition) o;
        return id.equals(that.id) && entityDefinition.equals(that.entityDefinition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, entityDefinition);
    }
}