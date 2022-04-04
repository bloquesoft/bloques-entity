/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:CudLogicTest.java
 * Author:zhangjian
 * date:2022/3/31 下午10:15
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.core.logic;

import io.github.bloquesoft.entity.core.definition.*;
import io.github.bloquesoft.entity.core.definition.basicValue.LongFieldDefinition;
import io.github.bloquesoft.entity.core.definition.basicValue.StringFieldDefinition;
import io.github.bloquesoft.entity.core.error.template.LogicErrorTemplate;
import io.github.bloquesoft.entity.core.logic.error.LogicException;
import io.github.bloquesoft.entity.core.object.AbstractEntityObject;
import io.github.bloquesoft.entity.core.object.EntityObjectCreator;
import io.github.bloquesoft.entity.core.object.MapEntityObject;
import io.github.bloquesoft.entity.core.object.MapEntityObjectFactory;
import io.github.bloquesoft.entity.core.repository.EntityRepository;
import io.github.bloquesoft.entity.core.repository.EntityRepositoryFactory;
import io.github.bloquesoft.entity.core.resource.i18n.ResourceReader;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;

import java.util.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class CudLogicTest {

    @Test
    public void test_1_Input() {

        ResourceReader reader = new ResourceReader();
        Map<String, String> map = reader.readResourceMap("i18n/logicErrorTemplate", new Locale("default", ""));
        LogicErrorTemplate.getSingleton().input(map);
        Assertions.assertEquals(6, LogicErrorTemplate.getSingleton().getKeys().size());
    }

    @Test
    public void test_2_Add() throws Exception {

        LogicErrorTemplate.importDefault();

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

        Map<String, Object> root = new HashMap<>();
        root.put("name", "根节点");
        root.put("parentId", 0L);

        Map<String, Object> child1 = new HashMap<>();
        child1.put("name", "Child1");

        List<Map<String, Object>> children = new ArrayList<>();
        children.add(child1);

        root.put("children", children);

        EntityRepositoryFactory entityRepositoryFactory = PowerMockito.mock(EntityRepositoryFactory.class);
        EntityRepository entityRepository = PowerMockito.mock(EntityRepository.class);
        PowerMockito.when(entityRepository.findList(Mockito.any(), Mockito.any())).thenReturn(new LinkedList<>());
        PowerMockito.when(entityRepositoryFactory.getRepository(orgEd.getId())).thenReturn(entityRepository);

        CudLog log = new CudLog();
        CudLogic logic = new CudLogic(entityRepositoryFactory, log);

        MapEntityObject entityObj = new MapEntityObject(orgEd, root);
        logic.add(entityObj);

        Assertions.assertTrue((Long) root.get("id") > 1000);
        Assertions.assertTrue((Long) child1.get("parentId") == (Long) root.get("id"));
        Assertions.assertTrue(log.getAppendEntityObjects().size() == 2);
        for (AbstractEntityObject aeo : log.getAppendEntityObjects()) {
            Assertions.assertTrue(aeo.getValue().equals(root) || aeo.getValue().equals(child1));
        }
    }

    @Test
    public void test_3_Update() {

        LogicErrorTemplate.importDefault();

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

        Map<String, Object> root = new HashMap<>();
        root.put("name", "根节点");
        root.put("parentId", 0L);

        Map<String, Object> child1 = new HashMap<>();
        child1.put("name", "Child1");

        List<Map<String, Object>> children = new ArrayList<>();
        children.add(child1);

        root.put("children", children);

        EntityRepositoryFactory entityRepositoryFactory = PowerMockito.mock(EntityRepositoryFactory.class);
        EntityRepository entityRepository = PowerMockito.mock(EntityRepository.class);
        PowerMockito.when(entityRepository.findList(Mockito.any(), Mockito.any())).thenReturn(new LinkedList<>());
        PowerMockito.when(entityRepositoryFactory.getRepository(orgEd.getId())).thenReturn(entityRepository);

        CudLog log = new CudLog();
        CudLogic logic = new CudLogic(entityRepositoryFactory, log);
        MapEntityObject entityObj = new MapEntityObject(orgEd, root);
        logic.add(entityObj);

        Map<String, Object> newRoot = new HashMap<>();
        newRoot.put("id", root.get("id"));
        newRoot.put("name", "根节点1");
        newRoot.put("parentId", 0L);

        logic.update(new MapEntityObject(orgEd, newRoot), entityObj);
        Assertions.assertEquals(2, log.getAppendEntityObjects().size());
        Assertions.assertEquals(1, log.getUpdateMap().size());
        Assertions.assertEquals("根节点1", root.get("name"));
    }

    @Test
    public void test_4_Unique() {

        LogicErrorTemplate.importDefault();

        EntityObjectCreator.getSingleton().register(new MapEntityObjectFactory());

        EntityDefinition orgEd = new EntityDefinition("center.Organization", "Organization", "组织");
        LongFieldDefinition idField = new LongFieldDefinition("id", "id");
        idField.setEntityDefinition(orgEd);
        PrimaryKeyDefinition primaryKeyDefinition = new PrimaryKeyDefinition(idField, PrimaryKeyGenerationType.Snowflake);
        orgEd.setPrimaryKey(primaryKeyDefinition);
        orgEd.addField(idField);
        orgEd.addField(new StringFieldDefinition("name", "名称"));
        orgEd.addField(new LongFieldDefinition("parentId", "父节点"));

        List<FieldUniqDefinition> uniques = new ArrayList<>();
        FieldUniqDefinition nameUnique = new FieldUniqDefinition(orgEd);
        nameUnique.addFieldId("name");
        uniques.add(nameUnique);
        orgEd.setFieldUniqs(uniques);


        ListFieldDefinition listField = new ListFieldDefinition("children", "子节点集合", new ArrayList<ListRelationShip>() {{
            add(new ListRelationShip("id", "parentId"));
        }});
        listField.setListItemEntity(orgEd);
        listField.setEntityDefinition(orgEd);
        listField.setListItemEntityId(orgEd.getId());

        orgEd.addField(listField);

        EntityDefinition orgRoleEd = new EntityDefinition("center.OrgRole", "OrgRole", "组织角色");



        Map<String, Object> root = new HashMap<>();
        root.put("id", 1L);
        root.put("name", "根节点");
        root.put("parentId", -1l);


        Map<String, Object> existRoot = new HashMap<>();
        existRoot.put("id", 2L);
        existRoot.put("name", "根节点1");
        existRoot.put("parentId", -1l);


        List<Object> existList = new LinkedList<>();
        existList.add(existRoot);

        LinkedHashMap<String, Object> args = new LinkedHashMap<>();
        args.put("name", "根节点1");

        EntityRepositoryFactory entityRepositoryFactory = PowerMockito.mock(EntityRepositoryFactory.class);
        EntityRepository orgEntityRepository = PowerMockito.mock(EntityRepository.class);
        PowerMockito.when(orgEntityRepository.findList(orgEd, args)).thenReturn(new LinkedList<>());
        PowerMockito.when(entityRepositoryFactory.getRepository(orgEd.getId())).thenReturn(orgEntityRepository);

        EntityRepository orgRoleEntityRepository = PowerMockito.mock(EntityRepository.class);
        PowerMockito.when(orgRoleEntityRepository.findList(Mockito.any(), Mockito.any())).thenReturn(new LinkedList<>());
        PowerMockito.when(entityRepositoryFactory.getRepository(orgRoleEd.getId())).thenReturn(orgRoleEntityRepository);

        CudLog log = new CudLog();
        CudLogic logic = new CudLogic(entityRepositoryFactory, log);
        MapEntityObject entityObj = new MapEntityObject(orgEd, root);
        logic.add(entityObj);

        Map<String, Object> newRoot = new HashMap<>();
        newRoot.put("id", root.get("id"));
        newRoot.put("name","根节点1");

        MapEntityObject newObj = new MapEntityObject(orgEd, newRoot);
        logic.update(newObj, entityObj);

        PowerMockito.when(orgEntityRepository.findList(orgEd, args)).thenReturn(existList);
        Assertions.assertThrows(LogicException.class, ()->logic.update(newObj, entityObj));
    }
}