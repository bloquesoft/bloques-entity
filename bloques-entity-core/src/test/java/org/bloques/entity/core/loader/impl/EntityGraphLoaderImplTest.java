/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:EntityGraphLoaderImplTest.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.core.loader.impl;

import org.bloques.entity.core.definition.*;
import org.bloques.entity.core.definition.basicValue.LongFieldDefinition;
import org.bloques.entity.core.definition.basicValue.StringFieldDefinition;
import org.bloques.entity.core.graph.GraphParser;
import org.bloques.entity.core.graph.graphql.GraphqlJavaParserImpl;
import org.bloques.entity.core.loader.EntityGraphLoader;
import org.bloques.entity.core.object.AbstractEntityObject;
import org.bloques.entity.core.object.EntityObjectCreator;
import org.bloques.entity.core.object.MapEntityObjectFactory;
import org.bloques.entity.core.repository.EntityRepository;
import org.bloques.entity.core.repository.EntityRepositoryFactory;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.powermock.api.mockito.PowerMockito;

import java.util.*;

class EntityGraphLoaderImplTest {

    @Test
    void load() {

        EntityObjectCreator.getSingleton().register(new MapEntityObjectFactory());

        EntityDefinition orgEd = new EntityDefinition("center.Organization", "Organization", "组织");
        orgEd.setPrimaryKey(new PrimaryKeyDefinition("id"));
        orgEd.addField(new StringFieldDefinition("name", "名称"));
        orgEd.addField(new LongFieldDefinition("parentId", "父节点"));

        ListFieldDefinition listField = new ListFieldDefinition("children", "子节点集合", new ArrayList<ListRelationShip>(){{
            add(new ListRelationShip("id", "parentId"));
        }});
        listField.setListItemEntity(orgEd);
        listField.setEntityDefinition(orgEd);
        listField.setListItemEntityId(orgEd.getId());

        orgEd.addField(listField);

        EntityFieldDefinition parentField = new EntityFieldDefinition("parent", "父节点", "parentId");
        parentField.setAssociatedEntity(orgEd);
        parentField.setEntityDefinition(orgEd);
        orgEd.addField(parentField);

        GraphParser parser = new GraphqlJavaParserImpl();

        EntityRepositoryFactory entityRepositoryFactory = PowerMockito.mock(EntityRepositoryFactory.class);
        EntityGraphLoader loader = new EntityGraphLoaderImpl(entityRepositoryFactory, parser);

        Map<String,Object> root = new HashMap<>();
        root.put("id",1L);
        root.put("name", "Root");
        root.put("parentId", -1L);

        Map<String,Object> child1 = new HashMap<>();
        child1.put("id", 2L);
        child1.put("name", "Child1");
        child1.put("parentId", 1L);

        Map<String,Object> child2 = new HashMap<>();
        child2.put("id", 3L);
        child2.put("name", "Child2");
        child2.put("parentId", 1L);

        Map<String,Object> child11 = new HashMap<>();
        child11.put("id", 4L);
        child11.put("name", "Child11");
        child11.put("parentId", 2L);


        EntityRepository orgEntityRepository = PowerMockito.mock(EntityRepository.class);
        PowerMockito.when(entityRepositoryFactory.getRepository(orgEd.getId())).thenReturn(orgEntityRepository);
        PowerMockito.when(orgEntityRepository.findById(orgEd, 1L)).thenReturn(root);
        PowerMockito.when(orgEntityRepository.findById(orgEd, 2L)).thenReturn(child1);

        AbstractEntityObject entityObject = EntityObjectCreator.getSingleton().create(orgEd, child11);
        loader.load(entityObject, "{:parent}");

        Map<String, Object> org = (Map<String, Object>)entityObject.getValue();

        Map map1 = (Map)org.get("parent");
        Map map2 = (Map)map1.get("parent");
        Assert.assertEquals(map2.get("id"), 1L);

        LinkedHashMap parameters = new LinkedHashMap();
        parameters.put("parentId", 1L);
        List<Map<String, Object>> children = new ArrayList<>();
        children.add(child1);
        children.add(child2);
        PowerMockito.when(orgEntityRepository.findList(orgEd, parameters)).thenReturn(children);

        List<Map<String,Object>> grandSon = new ArrayList<>();
        grandSon.add(child11);

        LinkedHashMap parameters2 = new LinkedHashMap();
        parameters2.put("parentId", 2L);

        PowerMockito.when(orgEntityRepository.findList(orgEd, parameters2)).thenReturn(grandSon);

        AbstractEntityObject rootObj = EntityObjectCreator.getSingleton().create(orgEd, root);
        loader.load(rootObj, "{:children}");
        Assert.assertEquals(rootObj.getListFieldObjects().get(0).getListItemObjects().size(), 2);

        List list1 = (List)root.get("children");
        Map map3 = (Map)list1.get(0);
        List list2 = (List)map3.get("children");
        Map map4 = (Map)list2.get(0);

        Assert.assertEquals(map4.get("id") , 4L);
    }

    @Test
    void testLoad() {
    }

    @Test
    void loadList() {
    }

    @Test
    void testLoadList() {
    }
}