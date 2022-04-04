/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:AbstractFieldObjectTest.java
 * Author:zhangjian
 * date:2022/3/31 下午10:15
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.core.object.fieldObject;

import io.github.bloquesoft.entity.core.definition.EntityDefinition;
import io.github.bloquesoft.entity.core.definition.PrimaryKeyDefinition;
import io.github.bloquesoft.entity.core.definition.PrimaryKeyGenerationType;
import io.github.bloquesoft.entity.core.definition.basicValue.LongFieldDefinition;
import io.github.bloquesoft.entity.core.definition.basicValue.StringFieldDefinition;
import io.github.bloquesoft.entity.core.object.MapEntityObject;
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
        LongFieldDefinition idField = new LongFieldDefinition("id", "id");
        idField.setEntityDefinition(ed);
        PrimaryKeyDefinition pkd = new PrimaryKeyDefinition(idField, PrimaryKeyGenerationType.Snowflake);
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

        LongFieldDefinition idField = new LongFieldDefinition("id", "id");
        idField.setEntityDefinition(ed);
        PrimaryKeyDefinition pkd = new PrimaryKeyDefinition(idField, PrimaryKeyGenerationType.Snowflake);
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
