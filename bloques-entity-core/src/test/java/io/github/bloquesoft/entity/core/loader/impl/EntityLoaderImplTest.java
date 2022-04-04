/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:EntityLoaderImplTest.java
 * Author:zhangjian
 * date:2022/3/31 下午10:15
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.core.loader.impl;

import io.github.bloquesoft.entity.core.definition.*;
import io.github.bloquesoft.entity.core.definition.basicValue.LongFieldDefinition;
import io.github.bloquesoft.entity.core.definition.basicValue.StringFieldDefinition;
import io.github.bloquesoft.entity.core.loader.EntityLoader;
import io.github.bloquesoft.entity.core.object.AbstractEntityObject;
import io.github.bloquesoft.entity.core.object.EntityObjectCreator;
import io.github.bloquesoft.entity.core.object.MapEntityObject;
import io.github.bloquesoft.entity.core.object.MapEntityObjectFactory;
import io.github.bloquesoft.entity.core.object.fieldObject.ListFieldObject;
import io.github.bloquesoft.entity.core.repository.EntityRepository;
import io.github.bloquesoft.entity.core.repository.EntityRepositoryFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.powermock.api.mockito.PowerMockito;

import java.util.*;

class EntityLoaderImplTest {

    @Test
    public void testLoadEntityField() {

        EntityDefinition orgEd = new EntityDefinition("center.Organization", "Organization", "组织");

        LongFieldDefinition idField = new LongFieldDefinition("id", "id");
        idField.setEntityDefinition(orgEd);
        PrimaryKeyDefinition primaryKeyDefinition = new PrimaryKeyDefinition(idField, PrimaryKeyGenerationType.Snowflake);
        orgEd.setPrimaryKey(primaryKeyDefinition);
        orgEd.addField(idField);

        orgEd.addField(new StringFieldDefinition("name", "名称"));
        orgEd.addField(new LongFieldDefinition("parentId", "父节点"));
        EntityFieldDefinition efd = new EntityFieldDefinition("parent", "父亲", "parentId");
        efd.setAssociatedEntity(orgEd);
        orgEd.addField(efd);

        ListFieldDefinition listField = new ListFieldDefinition("children", "子节点集合", new ArrayList<ListRelationShip>() {{
            add(new ListRelationShip("id", "parentId"));
        }});
        listField.setListItemEntity(orgEd);
        listField.setEntityDefinition(orgEd);
        listField.setListItemEntityId(orgEd.getId());

        orgEd.addField(listField);

        EntityRepositoryFactory entityRepositoryFactory = PowerMockito.mock(EntityRepositoryFactory.class);

        EntityRepository orgEntityRepository = PowerMockito.mock(EntityRepository.class);

        PowerMockito.when(entityRepositoryFactory.getRepository(orgEd.getId())).thenReturn(orgEntityRepository);

        Map<String, Object> parent = new HashMap<>();
        parent.put("id", 1L);
        parent.put("name", "Parent");

        Map<String, Object> child = new HashMap<>();
        child.put("id", 2L);
        child.put("parentId", 1L);
        child.put("name", "Child1");

        AbstractEntityObject entityObject = new MapEntityObject(orgEd, child);

        PowerMockito.when(orgEntityRepository.findById(orgEd, 1L)).thenReturn(parent);

        EntityLoader loader = new EntityLoaderImpl();
        loader.loadEntityField(entityObject.getEntityFieldObject("parent"), entityRepositoryFactory);
        Assertions.assertEquals(child.get("parent"), parent);
    }


    @Test
    public void testLoadListField() {

        EntityObjectCreator.getSingleton().register(new MapEntityObjectFactory());

        EntityDefinition orgEd = new EntityDefinition("center.Organization", "Organization", "组织");
        LongFieldDefinition idField = new LongFieldDefinition("id", "id");
        idField.setEntityDefinition(orgEd);
        PrimaryKeyDefinition primaryKeyDefinition = new PrimaryKeyDefinition(idField, PrimaryKeyGenerationType.Snowflake);
        orgEd.setPrimaryKey(primaryKeyDefinition);
        orgEd.addField(idField);
        orgEd.addField(new StringFieldDefinition("name", "名称"));
        orgEd.addField(new LongFieldDefinition("parentId", "父节点"));

        ListFieldDefinition listField = new ListFieldDefinition("children", "子节点集合", new ArrayList<ListRelationShip>() {{
            add(new ListRelationShip("id", "parentId"));
        }});
        listField.setListItemEntity(orgEd);
        listField.setEntityDefinition(orgEd);
        listField.setListItemEntityId(orgEd.getId());

        orgEd.addField(listField);

        EntityRepositoryFactory entityRepositoryFactory = PowerMockito.mock(EntityRepositoryFactory.class);

        EntityRepository orgEntityRepository = PowerMockito.mock(EntityRepository.class);
        PowerMockito.when(entityRepositoryFactory.getRepository(orgEd.getId())).thenReturn(orgEntityRepository);

        Map<String, Object> parent = new HashMap<>();
        parent.put("id", 1L);
        parent.put("name", "Parent");


        Map<String, Object> child1 = new HashMap<>();
        child1.put("id", 2L);
        child1.put("parentId", 1L);
        child1.put("name", "Child1");

        Map<String, Object> child2 = new HashMap<>();
        child2.put("id", 3L);
        child2.put("parentId", 1L);
        child2.put("name", "Child2");

        List<Map<String, Object>> children = new ArrayList<>();
        children.add(child1);
        children.add(child2);

        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("parentId", 1L);
        PowerMockito.when(orgEntityRepository.findList(orgEd, parameters)).thenReturn((List)children);

        AbstractEntityObject entityObject = new MapEntityObject(orgEd, parent);
        EntityLoader loader = new EntityLoaderImpl();
        ListFieldObject lfo = entityObject.getListFieldObject("children");
        loader.loadListField(lfo, entityRepositoryFactory);
        Assertions.assertEquals(2, lfo.getListValue().size());
    }

}