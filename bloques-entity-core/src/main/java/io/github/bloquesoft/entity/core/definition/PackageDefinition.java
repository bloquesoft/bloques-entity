/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:PackageDefinition.java
 * Author:zhangjian
 * date:2022/3/31 下午10:15
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.core.definition;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Preconditions;
import lombok.Getter;
import org.springframework.util.Assert;

import java.util.*;

public class PackageDefinition
{
    @Getter
    private final String name;

    @Getter
    @JsonIgnore
    private final Set<EntityDefinition> entities;

    private final Map<String, EntityDefinition> entitiesMap;

    public void addEntityDefinition(EntityDefinition ed)
    {
        Assert.notNull(ed, "PackageDefinition could not add a nullable EntityDefinition.");
        entities.add(ed);
        entitiesMap.put(ed.getName(), ed);
    }

    public EntityDefinition getEntityDefinition(String entityName){
        return entitiesMap.get(entityName);
    }

    @JsonIgnore
    public Collection<EntityDefinition> getAllEntities(){
        return entitiesMap.values();
    }

    public PackageDefinition(String name)
    {
        this.name = Preconditions.checkNotNull(name);
        this.entities = new HashSet<>();
        this.entitiesMap = new HashMap<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PackageDefinition that = (PackageDefinition) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "PackageDefinition{" +
                ", name='" + name + '\'' +
                '}';
    }
}