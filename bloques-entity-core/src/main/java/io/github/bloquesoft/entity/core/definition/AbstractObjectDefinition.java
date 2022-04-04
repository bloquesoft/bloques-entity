/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:AbstractObjectDefinition.java
 * Author:zhangjian
 * date:2022/3/31 下午10:15
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.core.definition;

import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.Setter;
import io.github.bloquesoft.entity.core.definition.basicValue.AbstractBasicValueFieldDefinition;

import java.util.*;

public abstract class AbstractObjectDefinition
{
    @Getter
    private final String id;

    @Getter
    private final String name;

    @Getter
    @Setter
    private PackageDefinition packageDefinition;

    @Getter
    private final List<AbstractFieldDefinition> fields = new LinkedList<>();

    @Getter
    private final List<ListFieldDefinition> listFields = new LinkedList<>();

    @Getter
    private final List<AbstractBasicValueFieldDefinition> baseFields = new LinkedList<>();

    @Getter
    private final List<EntityFieldDefinition> entityFields = new LinkedList<>();

    @Getter
    private final Map<String, AbstractFieldDefinition> fieldsMap = new HashMap<>();

    public AbstractObjectDefinition(String id, String name){
        this.id = Preconditions.checkNotNull(id, "init argument id could not be null");
        this.name = Preconditions.checkNotNull(name, "init argument name could not be null");
    }

    public void addField(AbstractFieldDefinition field)
    {
        this.fields.add(field);
        this.fieldsMap.put(field.getId(), field);

        if (field instanceof AbstractBasicValueFieldDefinition)
        {
            this.baseFields.add((AbstractBasicValueFieldDefinition) field);
        }
        else
        {
            if (field instanceof EntityFieldDefinition)
            {
                this.entityFields.add((EntityFieldDefinition) field);
            }
            else
            {
                if (field instanceof ListFieldDefinition)
                {
                    this.listFields.add((ListFieldDefinition) field);
                }
            }
        }
    }

    public AbstractFieldDefinition getField(String fieldId)
    {
        return this.fieldsMap.get(fieldId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractObjectDefinition that = (AbstractObjectDefinition) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}