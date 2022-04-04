/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:FieldObjectFormatVisitorTest.java
 * Author:zhangjian
 * date:2022/3/31 下午10:15
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.core.object.fieldObject;

import io.github.bloquesoft.entity.core.definition.EntityDefinition;
import io.github.bloquesoft.entity.core.definition.basicValue.DateFieldDefinition;
import io.github.bloquesoft.entity.core.definition.basicValue.DateTimeFieldDefinition;
import io.github.bloquesoft.entity.core.definition.basicValue.DoubleFieldDefinition;
import io.github.bloquesoft.entity.core.definition.basicValue.FloatFieldDefinition;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;

import java.util.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class FieldObjectFormatVisitorTest {

    @Test
    public void test_1_String_Visit() {
        FieldObjectVisitor visitor = new FieldObjectFormatVisitor();
        StringFieldObject fieldObj = Mockito.mock(StringFieldObject.class);
        Mockito.when(fieldObj.getValue()).thenReturn(null);
        Object result = visitor.visit(fieldObj);
        Assertions.assertNull(result);
        Mockito.verify(fieldObj, Mockito.never()).setValue(null);

        Mockito.when(fieldObj.getValue()).thenReturn("abcd");
        result = visitor.visit(fieldObj);
        Assertions.assertEquals("abcd", result);
        Mockito.verify(fieldObj, Mockito.never()).setValue("abcd");

        Mockito.when(fieldObj.getValue()).thenReturn(50);
        result = visitor.visit(fieldObj);
        Assertions.assertEquals("50", result);
        Mockito.verify(fieldObj).setValue("50");

        Mockito.when(fieldObj.getValue()).thenReturn(false);
        result = visitor.visit(fieldObj);
        Assertions.assertEquals("false", result);
        Mockito.verify(fieldObj).setValue("false");
    }

    @Test
    public void test_2_Integer_Visit() {

        FieldObjectVisitor visitor = new FieldObjectFormatVisitor();
        IntegerFieldObject fieldObj = Mockito.mock(IntegerFieldObject.class);
        Mockito.when(fieldObj.getValue()).thenReturn(null);
        Object result = visitor.visit(fieldObj);
        Assertions.assertNull(result);
        Mockito.verify(fieldObj, Mockito.never()).setValue(null);

        Mockito.when(fieldObj.getValue()).thenReturn(50);
        result = visitor.visit(fieldObj);
        Assertions.assertTrue(result instanceof Integer);
        Assertions.assertEquals(0, ((Integer) result).compareTo(50));
        Mockito.verify(fieldObj, Mockito.never()).setValue(50);

        Mockito.when(fieldObj.getValue()).thenReturn("100");
        result = visitor.visit(fieldObj);
        Assertions.assertTrue(result instanceof Integer);
        Assertions.assertEquals(0, ((Integer) result).compareTo(100));
        Mockito.verify(fieldObj).setValue(100);

        Mockito.when(fieldObj.getValue()).thenReturn(1000L);
        result = visitor.visit(fieldObj);
        Assertions.assertTrue(result instanceof Integer);
        Assertions.assertEquals(0, ((Integer) result).compareTo(1000));
        Mockito.verify(fieldObj).setValue(1000);

        Mockito.when(fieldObj.getValue()).thenReturn(false);
        result = visitor.visit(fieldObj);
        Assertions.assertTrue(result instanceof Integer);
        Assertions.assertEquals(0, ((Integer) result).compareTo(0));
        Mockito.verify(fieldObj).setValue(0);

        Mockito.when(fieldObj.getValue()).thenReturn(true);
        result = visitor.visit(fieldObj);
        Assertions.assertTrue(result instanceof Integer);
        Assertions.assertEquals(0, ((Integer) result).compareTo(1));
        Mockito.verify(fieldObj).setValue(1);

        Mockito.when(fieldObj.getValue()).thenReturn("abc");
        Mockito.when(fieldObj.getEntityDefinition()).thenReturn(new EntityDefinition("user","用户"));
        Mockito.when(fieldObj.getFieldId()).thenReturn("age");
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, ()->visitor.visit(fieldObj));
        Assertions.assertTrue(exception.getMessage().contains("BEntity could not format field to integer"));

        Mockito.when(fieldObj.getValue()).thenReturn(new Date());
        Mockito.when(fieldObj.getEntityDefinition()).thenReturn(new EntityDefinition("user","用户"));
        Mockito.when(fieldObj.getFieldId()).thenReturn("createTime");
        exception = Assertions.assertThrows(IllegalArgumentException.class, ()->visitor.visit(fieldObj));
        Assertions.assertTrue(exception.getMessage().contains("BEntity could not support format field to integer"));
    }

    @Test
    public void test_3_Long_Visit() {

        FieldObjectVisitor visitor = new FieldObjectFormatVisitor();
        LongFieldObject fieldObj = Mockito.mock(LongFieldObject.class);
        Mockito.when(fieldObj.getValue()).thenReturn(null);
        Object result = visitor.visit(fieldObj);
        Assertions.assertNull(result);
        Mockito.verify(fieldObj, Mockito.never()).setValue(null);

        Mockito.when(fieldObj.getValue()).thenReturn(50L);
        result = visitor.visit(fieldObj);
        Assertions.assertTrue(result instanceof Long);
        Assertions.assertEquals(0, ((Long) result).compareTo(50L));
        Mockito.verify(fieldObj, Mockito.never()).setValue(50L);

        Mockito.when(fieldObj.getValue()).thenReturn("100");
        result = visitor.visit(fieldObj);
        Assertions.assertTrue(result instanceof Long);
        Assertions.assertEquals(0, ((Long) result).compareTo(100L));
        Mockito.verify(fieldObj).setValue(100L);

        Mockito.when(fieldObj.getValue()).thenReturn(1000);
        result = visitor.visit(fieldObj);
        Assertions.assertTrue(result instanceof Long);
        Assertions.assertEquals(0, ((Long) result).compareTo(1000L));
        Mockito.verify(fieldObj).setValue(1000L);

        Mockito.when(fieldObj.getValue()).thenReturn(false);
        result = visitor.visit(fieldObj);
        Assertions.assertTrue(result instanceof Long);
        Assertions.assertEquals(0, ((Long) result).compareTo(0L));
        Mockito.verify(fieldObj).setValue(0L);

        Mockito.when(fieldObj.getValue()).thenReturn(true);
        result = visitor.visit(fieldObj);
        Assertions.assertTrue(result instanceof Long);
        Assertions.assertEquals(0, ((Long) result).compareTo(1L));
        Mockito.verify(fieldObj).setValue(1L);

        Mockito.when(fieldObj.getValue()).thenReturn("abc");
        Mockito.when(fieldObj.getEntityDefinition()).thenReturn(new EntityDefinition("user","用户"));
        Mockito.when(fieldObj.getFieldId()).thenReturn("age");
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, ()->visitor.visit(fieldObj));
        Assertions.assertTrue(exception.getMessage().contains("BEntity could not format field to long"));

        Mockito.when(fieldObj.getValue()).thenReturn(new Date());
        Mockito.when(fieldObj.getEntityDefinition()).thenReturn(new EntityDefinition("user","用户"));
        Mockito.when(fieldObj.getFieldId()).thenReturn("createTime");
        exception = Assertions.assertThrows(IllegalArgumentException.class, ()->visitor.visit(fieldObj));
        Assertions.assertTrue(exception.getMessage().contains("BEntity could not support format field to long"));
    }

    @Test
    public void test_4_Float_Visit() {
        FieldObjectVisitor visitor = new FieldObjectFormatVisitor();
        FloatFieldObject fieldObj = Mockito.mock(FloatFieldObject.class);
        Mockito.when(fieldObj.getValue()).thenReturn(null);
        FloatFieldDefinition fd = new FloatFieldDefinition("num", "num");
        fd.setDecimalDigit(2);
        Mockito.when(fieldObj.getFloatFieldDefinition()).thenReturn(fd);

        Object result = visitor.visit(fieldObj);
        Assertions.assertNull(result);
        Mockito.verify(fieldObj, Mockito.never()).setValue(null);

        Mockito.when(fieldObj.getValue()).thenReturn(1.0);
        result = visitor.visit(fieldObj);
        Assertions.assertTrue(result instanceof Float);
        Assertions.assertEquals(0, ((Float) result).compareTo((float) 1.0));
        Mockito.verify(fieldObj, Mockito.never()).setValue(1.0);

        Mockito.when(fieldObj.getValue()).thenReturn(1234.5678d);
        result = visitor.visit(fieldObj);
        Assertions.assertTrue(result instanceof Float);
        Assertions.assertEquals(0, ((Float) result).compareTo(1234.57f));

        Mockito.when(fieldObj.getValue()).thenReturn("2.0");
        result = visitor.visit(fieldObj);
        Assertions.assertTrue(result instanceof Float);
        Assertions.assertEquals(0, ((Float) result).compareTo((float) 2.0));
        Mockito.verify(fieldObj).setValue(2.0f);

        Mockito.when(fieldObj.getValue()).thenReturn(2);
        result = visitor.visit(fieldObj);
        Assertions.assertTrue(result instanceof Float);
        Assertions.assertEquals(0, ((Float) result).compareTo((float) 2));

        Mockito.when(fieldObj.getValue()).thenReturn(true);
        result = visitor.visit(fieldObj);
        Assertions.assertTrue(result instanceof Float);
        Assertions.assertEquals(0, ((Float) result).compareTo((float) 1));

        Mockito.when(fieldObj.getValue()).thenReturn("abc");
        Mockito.when(fieldObj.getEntityDefinition()).thenReturn(new EntityDefinition("user","用户"));
        Mockito.when(fieldObj.getFieldId()).thenReturn("age");
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, ()->visitor.visit(fieldObj));
        Assertions.assertTrue(exception.getMessage().contains("BEntity could not format field to float"));

        Mockito.when(fieldObj.getValue()).thenReturn(new Date());
        Mockito.when(fieldObj.getEntityDefinition()).thenReturn(new EntityDefinition("user","用户"));
        Mockito.when(fieldObj.getFieldId()).thenReturn("createTime");
        exception = Assertions.assertThrows(IllegalArgumentException.class, ()->visitor.visit(fieldObj));
        Assertions.assertTrue(exception.getMessage().contains("BEntity could not support format field to float"));
    }

    @Test
    public void test_5_Double_Visit() {
        FieldObjectVisitor visitor = new FieldObjectFormatVisitor();
        DoubleFieldObject fieldObj = Mockito.mock(DoubleFieldObject.class);
        Mockito.when(fieldObj.getValue()).thenReturn(null);
        Object result = visitor.visit(fieldObj);
        Assertions.assertNull(result);
        Mockito.verify(fieldObj, Mockito.never()).setValue(null);

        Mockito.when(fieldObj.getValue()).thenReturn(1.0d);
        result = visitor.visit(fieldObj);
        Assertions.assertTrue(result instanceof Double);
        Assertions.assertEquals(0, ((Double) result).compareTo(1.0d));
        Mockito.verify(fieldObj, Mockito.never()).setValue(1.0d);

        Mockito.when(fieldObj.getValue()).thenReturn("1234567.8");
        DoubleFieldDefinition dfd = new DoubleFieldDefinition("num", "num");
        dfd.setDecimalDigit(2);
        Mockito.when(fieldObj.getDoubleFieldDefinition()).thenReturn(dfd);
        result = visitor.visit(fieldObj);
        Assertions.assertTrue(result instanceof Double);
        Assertions.assertEquals(0, ((Double) result).compareTo(1234567.8d));

        Mockito.when(fieldObj.getValue()).thenReturn("2.0");
        result = visitor.visit(fieldObj);
        Assertions.assertTrue(result instanceof Double);
        Assertions.assertEquals(0, ((Double) result).compareTo(2.0d));
        Mockito.verify(fieldObj).setValue(2.0d);

        Mockito.when(fieldObj.getValue()).thenReturn(2);
        result = visitor.visit(fieldObj);
        Assertions.assertTrue(result instanceof Double);
        Assertions.assertEquals(0, ((Double) result).compareTo(2d));

        Mockito.when(fieldObj.getValue()).thenReturn(true);
        result = visitor.visit(fieldObj);
        Assertions.assertTrue(result instanceof Double);
        Assertions.assertEquals(0, ((Double) result).compareTo(1d));

        Mockito.when(fieldObj.getValue()).thenReturn("abc");
        Mockito.when(fieldObj.getEntityDefinition()).thenReturn(new EntityDefinition("user","用户"));
        Mockito.when(fieldObj.getFieldId()).thenReturn("age");
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, ()->visitor.visit(fieldObj));
        Assertions.assertTrue(exception.getMessage().contains("BEntity could not format field to double"));

        Mockito.when(fieldObj.getValue()).thenReturn(new Date());
        Mockito.when(fieldObj.getEntityDefinition()).thenReturn(new EntityDefinition("user","用户"));
        Mockito.when(fieldObj.getFieldId()).thenReturn("createTime");
        exception = Assertions.assertThrows(IllegalArgumentException.class, ()->visitor.visit(fieldObj));
        Assertions.assertTrue(exception.getMessage().contains("BEntity could not support format field to double"));
    }

    @Test
    public void test_6_Boolean_Visit() {

        FieldObjectVisitor visitor = new FieldObjectFormatVisitor();
        BooleanFieldObject fieldObj = Mockito.mock(BooleanFieldObject.class);
        Mockito.when(fieldObj.getValue()).thenReturn(null);
        Object result = visitor.visit(fieldObj);
        Assertions.assertNull(result);
        Mockito.verify(fieldObj, Mockito.never()).setValue(null);

        Mockito.when(fieldObj.getValue()).thenReturn(true);
        result = visitor.visit(fieldObj);
        Assertions.assertTrue(result instanceof Boolean);
        Assertions.assertEquals(0, ((Boolean) result).compareTo(true));
        Mockito.verify(fieldObj, Mockito.never()).setValue(true);

        Mockito.when(fieldObj.getValue()).thenReturn(1);
        result = visitor.visit(fieldObj);
        Assertions.assertTrue(result instanceof Boolean);
        Assertions.assertEquals(0, ((Boolean) result).compareTo(true));

        Mockito.when(fieldObj.getValue()).thenReturn("TRUE");
        result = visitor.visit(fieldObj);
        Assertions.assertTrue(result instanceof Boolean);
        Assertions.assertEquals(0, ((Boolean) result).compareTo(true));

        Mockito.when(fieldObj.getValue()).thenReturn("");
        result = visitor.visit(fieldObj);
        Assertions.assertTrue(result instanceof Boolean);
        Assertions.assertEquals(0, ((Boolean) result).compareTo(false));

        Mockito.when(fieldObj.getValue()).thenReturn("abc");
        Mockito.when(fieldObj.getEntityDefinition()).thenReturn(new EntityDefinition("user","用户"));
        Mockito.when(fieldObj.getFieldId()).thenReturn("age");
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, ()->visitor.visit(fieldObj));
        Assertions.assertTrue(exception.getMessage().contains("BEntity could not format field to boolean"));

        Mockito.when(fieldObj.getValue()).thenReturn(new Date());
        Mockito.when(fieldObj.getEntityDefinition()).thenReturn(new EntityDefinition("user","用户"));
        Mockito.when(fieldObj.getFieldId()).thenReturn("createTime");
        exception = Assertions.assertThrows(IllegalArgumentException.class, ()->visitor.visit(fieldObj));
        Assertions.assertTrue(exception.getMessage().contains("BEntity could not support format field to boolean"));
    }

    @Test
    public void test_7_Date_Visit() {
        FieldObjectVisitor visitor = new FieldObjectFormatVisitor();
        DateFieldObject fieldObj = Mockito.mock(DateFieldObject.class);
        Mockito.when(fieldObj.getValue()).thenReturn(null);
        Object result = visitor.visit(fieldObj);
        Assertions.assertNull(result);
        Mockito.verify(fieldObj, Mockito.never()).setValue(null);

        Date today = new Date();
        Mockito.when(fieldObj.getValue()).thenReturn(today);
        result = visitor.visit(fieldObj);
        Assertions.assertTrue(result instanceof Date);
        Date formattedDate = (Date)result;
        Assertions.assertEquals(formattedDate.getYear(), today.getYear());
        Assertions.assertEquals(formattedDate.getMonth(), today.getMonth());
        Assertions.assertEquals(formattedDate.getDay(), today.getDay());
        Assertions.assertEquals(0, formattedDate.getHours());
        Assertions.assertEquals(0, formattedDate.getMinutes());
        Assertions.assertEquals(0, formattedDate.getSeconds());
        Mockito.verify(fieldObj).setValue(Mockito.any());

        Mockito.when(fieldObj.getValue()).thenReturn("2022-9-3");
        DateFieldDefinition df = new DateFieldDefinition("date", "日期");
        Mockito.when(fieldObj.getFieldDefinition()).thenReturn(df);
        Mockito.when(fieldObj.getDateFieldDefinition()).thenReturn(df);
        result = visitor.visit(fieldObj);
        Assertions.assertTrue(result instanceof Date);
        Calendar cal = Calendar.getInstance();
        cal.setTime((Date)result);
        Assertions.assertEquals(2022, cal.get(Calendar.YEAR));
        Assertions.assertEquals(8, cal.get(Calendar.MONTH));
        Assertions.assertEquals(3, cal.get(Calendar.DAY_OF_MONTH));
        Assertions.assertEquals(0, cal.get(Calendar.HOUR));
        Assertions.assertEquals(0, cal.get(Calendar.MINUTE));
        Assertions.assertEquals(0, cal.get(Calendar.SECOND));
        Assertions.assertEquals(0, cal.get(Calendar.MILLISECOND));

        Mockito.when(fieldObj.getValue()).thenReturn("abc");
        Mockito.when(fieldObj.getEntityDefinition()).thenReturn(new EntityDefinition("user","用户"));
        Mockito.when(fieldObj.getFieldId()).thenReturn("age");
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, ()->visitor.visit(fieldObj));
        Assertions.assertTrue(exception.getMessage().contains("BEntity could not format field to date"));

        Mockito.when(fieldObj.getValue()).thenReturn(123456);
        Mockito.when(fieldObj.getEntityDefinition()).thenReturn(new EntityDefinition("user","用户"));
        Mockito.when(fieldObj.getFieldId()).thenReturn("createTime");
        exception = Assertions.assertThrows(IllegalArgumentException.class, ()->visitor.visit(fieldObj));
        Assertions.assertTrue(exception.getMessage().contains("BEntity could not support format field to date"));
    }

    @Test
    public void test_8_DateTime_Visit() {
        FieldObjectVisitor visitor = new FieldObjectFormatVisitor();
        DateTimeFieldObject fieldObj = Mockito.mock(DateTimeFieldObject.class);
        Mockito.when(fieldObj.getValue()).thenReturn(null);
        Object result = visitor.visit(fieldObj);
        Assertions.assertNull(result);
        Mockito.verify(fieldObj, Mockito.never()).setValue(null);

        Date today = new Date();
        Mockito.when(fieldObj.getValue()).thenReturn(today);
        result = visitor.visit(fieldObj);
        Assertions.assertTrue(result instanceof Date);
        Assertions.assertEquals(result, today);

        Mockito.when(fieldObj.getValue()).thenReturn("2022-5-3 13:21:30");
        DateTimeFieldDefinition df = new DateTimeFieldDefinition("creatTime", "创建时间");
        Mockito.when(fieldObj.getFieldDefinition()).thenReturn(df);
        Mockito.when(fieldObj.getDateFieldDefinition()).thenReturn(df);
        result = visitor.visit(fieldObj);
        Assertions.assertTrue(result instanceof Date);
        Calendar cal = Calendar.getInstance();
        cal.setTime((Date)result);
        Assertions.assertEquals(2022, cal.get(Calendar.YEAR));
        Assertions.assertEquals(4, cal.get(Calendar.MONTH));
        Assertions.assertEquals(3, cal.get(Calendar.DAY_OF_MONTH));
        Assertions.assertEquals(13, cal.get(Calendar.HOUR_OF_DAY));
        Assertions.assertEquals(21, cal.get(Calendar.MINUTE));
        Assertions.assertEquals(30, cal.get(Calendar.SECOND));
        Assertions.assertEquals(0, cal.get(Calendar.MILLISECOND));

        Mockito.when(fieldObj.getValue()).thenReturn("abc");
        Mockito.when(fieldObj.getEntityDefinition()).thenReturn(new EntityDefinition("user","用户"));
        Mockito.when(fieldObj.getFieldId()).thenReturn("age");
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, ()->visitor.visit(fieldObj));
        Assertions.assertTrue(exception.getMessage().contains("BEntity could not format field to date"));

        Mockito.when(fieldObj.getValue()).thenReturn(123456);
        Mockito.when(fieldObj.getEntityDefinition()).thenReturn(new EntityDefinition("user","用户"));
        Mockito.when(fieldObj.getFieldId()).thenReturn("createTime");
        exception = Assertions.assertThrows(IllegalArgumentException.class, ()->visitor.visit(fieldObj));
        Assertions.assertTrue(exception.getMessage().contains("BEntity could not support format field to datetime"));
    }

    @Test
    public void test_9_Entity_Visit() {
        FieldObjectVisitor visitor = new FieldObjectFormatVisitor();
        EntityFieldObject fieldObj = Mockito.mock(EntityFieldObject.class);
        Mockito.when(fieldObj.getValue()).thenReturn(null);
        Object result = visitor.visit(fieldObj);
        Assertions.assertNull(result);
        Mockito.verify(fieldObj, Mockito.never()).setValue(null);

        Date today = new Date();
        Mockito.when(fieldObj.getValue()).thenReturn(today);
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, ()->visitor.visit(fieldObj));
        Assertions.assertTrue(exception.getMessage().contains("BEntity entity field value could not be String or Date type"));

        String str = "abc";
        Mockito.when(fieldObj.getValue()).thenReturn(str);
        exception = Assertions.assertThrows(IllegalArgumentException.class, ()->visitor.visit(fieldObj));
        Assertions.assertTrue(exception.getMessage().contains("BEntity entity field value could not be String or Date type"));

        Map<String, Object> user = new HashMap<>();
        Mockito.when(fieldObj.getValue()).thenReturn(user);
        result = visitor.visit(fieldObj);
        Assertions.assertEquals(result, user);
    }

    @Test
    public void test_10_List_Visit() {

        FieldObjectVisitor visitor = new FieldObjectFormatVisitor();
        ListFieldObject fieldObj = Mockito.mock(ListFieldObject.class);
        Mockito.when(fieldObj.getValue()).thenReturn(null);
        Object result = visitor.visit(fieldObj);
        Assertions.assertNull(result);
        Mockito.verify(fieldObj, Mockito.never()).setValue(null);

        Map<String, Object> user = new HashMap<>();
        Mockito.when(fieldObj.getValue()).thenReturn(user);
        Mockito.when(fieldObj.getEntityDefinition()).thenReturn(new EntityDefinition("user","用户"));
        Mockito.when(fieldObj.getFieldId()).thenReturn("list");

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, ()->visitor.visit(fieldObj));
        Assertions.assertTrue(exception.getMessage().contains("BEntity field is not List"));

        List<Map<String, Object>> userList = new ArrayList<>();
        Mockito.when(fieldObj.getValue()).thenReturn(userList);
        result = visitor.visit(fieldObj);
        Assertions.assertEquals(result, userList);
    }

}