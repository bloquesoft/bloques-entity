/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:AbstractEntityObjectTest.java
 * Author:zhangjian
 * date:2022/3/31 下午10:15
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.core.object;

import io.github.bloquesoft.entity.core.definition.EntityDefinition;
import io.github.bloquesoft.entity.core.definition.PrimaryKeyDefinition;
import io.github.bloquesoft.entity.core.definition.PrimaryKeyGenerationType;
import io.github.bloquesoft.entity.core.definition.basicValue.LongFieldDefinition;
import io.github.bloquesoft.entity.core.definition.basicValue.StringFieldDefinition;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.powermock.api.mockito.PowerMockito;

import java.util.HashMap;
import java.util.Map;

class AbstractEntityObjectTest {

    @Test
    public void testGetPkValue() {
        EntityDefinition ed = PowerMockito.mock(EntityDefinition.class);
        LongFieldDefinition idField = new LongFieldDefinition("id", "id");
        idField.setEntityDefinition(ed);
        PrimaryKeyDefinition pkd = new PrimaryKeyDefinition(idField, PrimaryKeyGenerationType.Snowflake);
        PowerMockito.when(ed.getPrimaryKey()).thenReturn(pkd);
        Map<String, Object> map = new HashMap<>();
        MapEntityObject oeo = new MapEntityObject(ed, map);
        Assert.assertEquals(oeo.getPkValue(), null);
        oeo.setFieldValue("id", 100L);
        Assert.assertEquals(oeo.getPkValue(), 100L);
    }

    @Test
    public void testGeneratePk() {
        EntityDefinition ed = PowerMockito.mock(EntityDefinition.class);
        LongFieldDefinition idField = new LongFieldDefinition("id", "id");
        idField.setEntityDefinition(ed);
        PrimaryKeyDefinition pkd = new PrimaryKeyDefinition(idField, PrimaryKeyGenerationType.Snowflake);
        PowerMockito.when(ed.getPrimaryKey()).thenReturn(pkd);
        Map<String, Object> org3 = new HashMap<>();
        MapEntityObject oeo3 = new MapEntityObject(ed, org3);
        Assert.assertEquals(oeo3.getPkValue(), null);
        oeo3.generatePk();
        Assert.assertTrue((Long)oeo3.getPkValue() > 0);
    }

    @Test
    public void testIsNewEntity() {

        EntityDefinition ed = PowerMockito.mock(EntityDefinition.class);
        PowerMockito.when(ed.getId()).thenReturn("id");
        LongFieldDefinition idField = (new LongFieldDefinition("id", "id"));
        idField.setEntityDefinition(ed);

        PrimaryKeyDefinition pkd = new PrimaryKeyDefinition(idField, PrimaryKeyGenerationType.Snowflake);
        PowerMockito.when(ed.getPrimaryKey()).thenReturn(pkd);
        Map<String, Object> map = new HashMap<>();
        MapEntityObject oeo = new MapEntityObject(ed, map);
        Assert.assertTrue(oeo.isPkEmpty());

        Map<String, Object> map1 = new HashMap<>();
        map1.put("id", 100L);
        MapEntityObject oeo1 = new MapEntityObject(ed, map1);
        Assert.assertEquals(oeo1.isPkEmpty(), false);
    }

    @Test
    public void testMatch()
    {
        EntityDefinition ed = PowerMockito.mock(EntityDefinition.class);

        PowerMockito.when(ed.getId()).thenReturn("id");
        LongFieldDefinition idField = new LongFieldDefinition("id", "id");
        idField.setEntityDefinition(ed);
        PrimaryKeyDefinition pkd = new PrimaryKeyDefinition(idField , PrimaryKeyGenerationType.Snowflake);

        PowerMockito.when(ed.getPrimaryKey()).thenReturn(pkd);
        PowerMockito.when(ed.getId()).thenReturn("Org");

        Map<String, Object> org1 = new HashMap<>();
        MapEntityObject oeo1 = new MapEntityObject(ed, org1);
        MapEntityObject oeo2 = new MapEntityObject(ed, org1);
        Assert.assertTrue(oeo1.match(oeo2));

        Map<String, Object> org3 = new HashMap<>();
        org3.put("id", 100L);
        MapEntityObject oeo3 = new MapEntityObject(ed, org3);
        Assert.assertTrue(!oeo3.match(oeo1));

        org1.put("id", 100L);
        Assert.assertTrue(oeo3.match(oeo1));
        org1.put("id", 200L);
        Assert.assertTrue(!oeo3.match(oeo1));

        EntityDefinition ed1 = PowerMockito.mock(EntityDefinition.class);

        PowerMockito.when(ed1.getId()).thenReturn("id");
        LongFieldDefinition idField1 = new LongFieldDefinition("id", "id");
        idField1.setEntityDefinition(ed1);
        PrimaryKeyDefinition pkd1 = new PrimaryKeyDefinition(idField1 , PrimaryKeyGenerationType.Snowflake);
        ed1.setPrimaryKey(pkd1);
        PowerMockito.when(ed1.getPrimaryKey()).thenReturn(pkd1);
        PowerMockito.when(ed1.getId()).thenReturn("Org1");
        Map<String, Object> org4 = new HashMap<>();
        org4.put("id", 100L);
        MapEntityObject oeo4 = new MapEntityObject(ed, org4);
        Map<String, Object> org5 = new HashMap<>();
        org5.put("id", 100L);
        MapEntityObject oeo5 = new MapEntityObject(ed1, org5);
        Assert.assertTrue(!oeo4.match(oeo5));
    }

    @Test
    public void testSetFieldValue(){

        EntityDefinition ed = PowerMockito.mock(EntityDefinition.class);
        PowerMockito.when(ed.getField("name")).thenReturn(new StringFieldDefinition("name", "名称"));
        PowerMockito.when(ed.getId()).thenReturn("id");
        LongFieldDefinition idField = new LongFieldDefinition("id", "id");
        idField.setEntityDefinition(ed);
        PrimaryKeyDefinition pkd = new PrimaryKeyDefinition(idField , PrimaryKeyGenerationType.Snowflake);

        PowerMockito.when(ed.getPrimaryKey()).thenReturn(pkd);
        Map<String, Object> org = new HashMap<>();
        MapEntityObject oeo = new MapEntityObject(ed, org);
        oeo.setFieldValue("name", "abcd");
        Object nameValue = oeo.getFieldValue("name");
        Assert.assertTrue("abcd".equals(nameValue));
    }
}