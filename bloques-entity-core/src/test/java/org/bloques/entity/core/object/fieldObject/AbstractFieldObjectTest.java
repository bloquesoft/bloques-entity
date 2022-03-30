/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:AbstractFieldObjectTest.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.core.object.fieldObject;

import org.bloques.entity.core.definition.EntityDefinition;
import org.bloques.entity.core.definition.PrimaryKeyDefinition;
import org.bloques.entity.core.definition.basicValue.StringFieldDefinition;
import org.bloques.entity.core.object.MapEntityObject;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.powermock.api.mockito.PowerMockito;

import java.util.HashMap;
import java.util.Map;

class AbstractFieldObjectTest {

    @Test
    public void testGetFieldId()
    {
        EntityDefinition ed = PowerMockito.mock(EntityDefinition.class);
        PrimaryKeyDefinition pkd = new PrimaryKeyDefinition("id");
        PowerMockito.when(ed.getPrimaryKey()).thenReturn(pkd);
        Map<String, Object> org = new HashMap<>();
        MapEntityObject oeo = new MapEntityObject(ed, org);
        StringFieldDefinition fd = new StringFieldDefinition("name", "名称");
        PowerMockito.when(ed.getField("name")).thenReturn(fd);
        StringFieldObject sfo = new StringFieldObject(oeo, fd);
        Assert.assertEquals("name", sfo.getFieldId());
    }

    @Test
    public void testSetDefaultValue() {
        EntityDefinition ed = PowerMockito.mock(EntityDefinition.class);
        PrimaryKeyDefinition pkd = new PrimaryKeyDefinition("id");
        PowerMockito.when(ed.getPrimaryKey()).thenReturn(pkd);
        Map<String, Object> org = new HashMap<>();
        MapEntityObject oeo = new MapEntityObject(ed, org);

        StringFieldDefinition fd = new StringFieldDefinition("name", "名称");
        fd.setDefaultValue("默认值");
        StringFieldObject sfo = new StringFieldObject(oeo, fd);
        PowerMockito.when(ed.getField("name")).thenReturn(fd);
        Assert.assertEquals(sfo.getValue(), null);
        sfo.setDefaultValue();
        Assert.assertEquals("默认值", sfo.getValue());
    }
}
