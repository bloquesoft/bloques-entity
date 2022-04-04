/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:ListFieldObjectTest.java
 * Author:zhangjian
 * date:2022/3/31 下午10:15
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.core.object.fieldObject;

import io.github.bloquesoft.entity.core.definition.PrimaryKeyGenerationType;
import io.github.bloquesoft.entity.core.definition.basicValue.LongFieldDefinition;
import io.github.bloquesoft.entity.core.object.AbstractEntityObject;
import io.github.bloquesoft.entity.core.object.EntityObjectCreator;
import io.github.bloquesoft.entity.core.object.MapEntityObject;
import io.github.bloquesoft.entity.core.definition.EntityDefinition;
import io.github.bloquesoft.entity.core.definition.ListFieldDefinition;
import io.github.bloquesoft.entity.core.definition.PrimaryKeyDefinition;
import io.github.bloquesoft.entity.core.definition.basicValue.StringFieldDefinition;
import io.github.bloquesoft.entity.core.object.MapEntityObjectFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

class ListFieldObjectTest {

    @Test
    public void testIsEmptyValue() {

        EntityDefinition ed = new EntityDefinition("Org", "Org", "组织");
        LongFieldDefinition idField = new LongFieldDefinition("id", "id");
        idField.setEntityDefinition(ed);
        PrimaryKeyDefinition pkd = new PrimaryKeyDefinition(idField, PrimaryKeyGenerationType.Snowflake);
        ed.setPrimaryKey(pkd);
        ed.addField(new StringFieldDefinition("name", "名称"));
        ListFieldDefinition lfd = new ListFieldDefinition("children", "children", null);
        lfd.setEntityDefinition(ed);
        lfd.setListItemEntity(ed);
        ed.addField(lfd);

        Map<String,Object> root = new HashMap<>();
        root.put("name", "根");

        MapEntityObject rootObj = new MapEntityObject(ed, root);
        Assertions.assertTrue(rootObj.getListFieldObject("children").isEmptyValue(null));
        Assertions.assertTrue(rootObj.getListFieldObject("children").isEmptyValue(new LinkedList<>()));

        Map<String, Object> son1 = new HashMap<>();
        son1.put("name","子1");
        Map<String, Object> son2 = new HashMap<>();
        son2.put("name","子2");

        List<Map<String, Object>> children = new ArrayList<>();
        children.add(son1);
        children.add(son2);
        root.put("children", children);

        Assertions.assertFalse(rootObj.getListFieldObject("children").isEmptyValue(root.get("children")));
    }

    @Test
    public void testInit() {

        EntityObjectCreator.getSingleton().register(new MapEntityObjectFactory());

        EntityDefinition ed = new EntityDefinition("Org", "Org", "组织");
        LongFieldDefinition idField = new LongFieldDefinition("id", "id");
        idField.setEntityDefinition(ed);
        PrimaryKeyDefinition pkd = new PrimaryKeyDefinition(idField, PrimaryKeyGenerationType.Snowflake);
        ed.setPrimaryKey(pkd);

        ed.addField(new StringFieldDefinition("name", "名称"));
        ListFieldDefinition lfd = new ListFieldDefinition("children", "children", null);
        lfd.setEntityDefinition(ed);
        lfd.setListItemEntity(ed);
        ed.addField(lfd);

        Map<String,Object> root = new HashMap<>();
        root.put("name", "根");

        Map<String,Object> son1 = new HashMap<>();
        son1.put("name", "子1");

        Map<String,Object> son2 = new HashMap<>();
        son2.put("name", "子2");
        son2.put("id", 4L);

        List<Map<String, Object>> children = new ArrayList<>();
        children.add(son1);
        children.add(son2);
        root.put("children", children);

        Map<String,Object> grantSon1 = new HashMap<>();
        grantSon1.put("name", "孙1");

        Map<String,Object> grantSon2 = new HashMap<>();
        grantSon2.put("name", "孙2");

        List<Map<String, Object>> children1 = new ArrayList<>();
        children1.add(grantSon1);
        children1.add(grantSon2);
        son1.put("children", children1);

        Map<String,Object> grantSon3 = new HashMap<>();
        grantSon3.put("name", "孙3");

        Map<String,Object> grantSon4 = new HashMap<>();
        grantSon4.put("name", "孙4");

        List<Map<String, Object>> children2 = new ArrayList<>();
        children2.add(grantSon3);
        children2.add(grantSon4);
        son2.put("children", children2);

        MapEntityObject rootObj = new MapEntityObject(ed, root);
        Assertions.assertEquals("根", rootObj.getFieldValue("name"));
        Assertions.assertEquals(rootObj.getValue(), root);
        ListFieldObject lfo1 =  rootObj.getListFieldObject("children");
        Assertions.assertEquals(lfo1.getListFieldDefinition(), lfd);
        Assertions.assertEquals(lfo1.getListFieldDefinition().getListItemEntity(), ed);
        Assertions.assertEquals(2, lfo1.getListItemObjects().size());
        Assertions.assertEquals(lfo1.getValue(), root.get("children"));

        ListFieldObject rootChildren =  rootObj.getListFieldObject("children");
        Assertions.assertEquals(rootChildren.getListFieldDefinition(), lfd);
        Assertions.assertEquals(rootChildren.getListFieldDefinition().getListItemEntity(), ed);
        Assertions.assertEquals(2, rootChildren.getListItemObjects().size());
        Assertions.assertEquals(rootChildren.getValue(), root.get("children"));

        ListFieldObject sonList1 =  rootChildren.getListItemObjects().get(0).getListFieldObject("children");
        Assertions.assertEquals(sonList1.getListFieldDefinition().getListItemEntity(), ed);
        Assertions.assertEquals(2, sonList1.getListItemObjects().size());
        Assertions.assertEquals(sonList1.getValue(), (((Map) ((List) root.get("children")).get(0)).get("children")));
        Assertions.assertEquals(sonList1.getListValue().get(0), grantSon1);
    }


    @Test
    public void testFindFirstMatchedItem()
    {
        EntityObjectCreator.getSingleton().register(new MapEntityObjectFactory());

        EntityDefinition ed = new EntityDefinition("Org", "Org", "组织");
        LongFieldDefinition idField = new LongFieldDefinition("id", "id");
        idField.setEntityDefinition(ed);
        PrimaryKeyDefinition pkd = new PrimaryKeyDefinition(idField, PrimaryKeyGenerationType.Snowflake);
        ed.setPrimaryKey(pkd);

        ed.addField(new StringFieldDefinition("name", "名称"));
        ListFieldDefinition lfd = new ListFieldDefinition("children", "children", null);
        lfd.setEntityDefinition(ed);
        lfd.setListItemEntity(ed);
        ed.addField(lfd);

        Map<String,Object> root = new HashMap<>();
        root.put("name", "根");

        Map<String,Object> son1 = new HashMap<>();
        son1.put("name", "子1");

        Map<String,Object> son2 = new HashMap<>();
        son2.put("name", "子2");
        son2.put("id", 4L);

        List<Map<String, Object>> children = new ArrayList<>();
        children.add(son1);
        children.add(son2);
        root.put("children", children);

        MapEntityObject rootObj = new MapEntityObject(ed, root);

        AbstractEntityObject matchedObj = rootObj.getListFieldObject("children").findFirstMatchedItem(new MapEntityObject(ed, son1));
        Assertions.assertTrue(matchedObj != null && matchedObj.getValue().equals(son1));

        Map<String, Object> son3 = new HashMap<>();
        son3.put("name", "子3");
        children.add(son3);

        matchedObj = rootObj.getListFieldObject("children").findFirstMatchedItem(new MapEntityObject(ed, son3));
        Assertions.assertNull(matchedObj);

        Map<String, Object> son4 = new HashMap<>();
        son4.put("name", "子4");
        children.add(son4);

        matchedObj = rootObj.getListFieldObject("children").findFirstMatchedItem(new MapEntityObject(ed, son4));
        Assertions.assertNull(matchedObj);
        son4.put("id", 4L);

        matchedObj = rootObj.getListFieldObject("children").findFirstMatchedItem(new MapEntityObject(ed, son4));
        Assertions.assertNotNull(matchedObj);
        Assertions.assertEquals(matchedObj.getValue(), son2);
    }

    @Test
    public void testAppendItem() {

        EntityObjectCreator.getSingleton().register(new MapEntityObjectFactory());

        EntityDefinition ed = new EntityDefinition("Org", "Org", "组织");
        LongFieldDefinition idField = new LongFieldDefinition("id", "id");
        idField.setEntityDefinition(ed);
        PrimaryKeyDefinition pkd = new PrimaryKeyDefinition(idField, PrimaryKeyGenerationType.Snowflake);
        ed.setPrimaryKey(pkd);

        ed.addField(new StringFieldDefinition("name", "名称"));
        ListFieldDefinition lfd = new ListFieldDefinition("children", "children", null);
        lfd.setEntityDefinition(ed);
        lfd.setListItemEntity(ed);
        ed.addField(lfd);

        Map<String,Object> root = new HashMap<>();
        root.put("name", "根");

        Map<String,Object> son1 = new HashMap<>();
        son1.put("name", "子1");

        Map<String,Object> son2 = new HashMap<>();
        son2.put("name", "子2");

        son2.put("id", 1L);

        MapEntityObject rootObj = new MapEntityObject(ed, root);
        rootObj.getListFieldObject("children").appendItem(son1);
        Assertions.assertEquals(rootObj.getListFieldObject("children").getListItemObjects().get(0).getValue(), son1);
        rootObj.getListFieldObject("children").appendItem(son2);
        rootObj.getListFieldObject("children").appendItem(son1);

        AbstractEntityObject son1Obj = rootObj.getListFieldObject("children").findFirstMatchedItem(new MapEntityObject(ed, son1));
        son1Obj.setFieldValue("id", 1L);
        rootObj.getListFieldObject("children").appendItem(son1);

        Map<String, Object> son3 = new HashMap<>();
        son3.put("name", "子2");
        son3.put("id", 1L);
        rootObj.getListFieldObject("children").appendItem(son3);
    }

    @Test
    public void testRemoveListItemByPk() {

        EntityDefinition ed = new EntityDefinition("Org", "Org", "组织");
        LongFieldDefinition idField = new LongFieldDefinition("id", "id");
        idField.setEntityDefinition(ed);
        PrimaryKeyDefinition pkd = new PrimaryKeyDefinition(idField, PrimaryKeyGenerationType.Snowflake);
        ed.setPrimaryKey(pkd);

        ed.addField(new StringFieldDefinition("name", "名称"));
        ListFieldDefinition lfd = new ListFieldDefinition("children", "children", null);
        lfd.setEntityDefinition(ed);
        lfd.setListItemEntity(ed);
        ed.addField(lfd);

        Map<String,Object> root = new HashMap<>();
        root.put("name", "根");

        Map<String,Object> son1 = new HashMap<>();
        son1.put("name", "子1");

        Map<String,Object> son2 = new HashMap<>();
        son2.put("name", "子2");

        son2.put("id", 1L);

        List<Map<String, Object>> children = new ArrayList<>();
        children.add(son1);
        children.add(son2);
        root.put("children", children);

        MapEntityObject rootObj = new MapEntityObject(ed, root);
        Assertions.assertEquals(2, rootObj.getListFieldObject("children").getListItemObjects().size());
        Assertions.assertEquals(2, rootObj.getListFieldObject("children").getListValue().size());
        rootObj.getListFieldObject("children").removeItemByPk(1L);
        Assertions.assertEquals(1, rootObj.getListFieldObject("children").getListItemObjects().size());
        Assertions.assertEquals(1, rootObj.getListFieldObject("children").getListValue().size());
    }
}



