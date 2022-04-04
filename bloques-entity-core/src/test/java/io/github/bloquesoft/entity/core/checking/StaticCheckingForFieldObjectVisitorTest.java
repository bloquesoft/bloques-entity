/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:StaticCheckingForFieldObjectVisitorTest.java
 * Author:zhangjian
 * date:2022/3/31 下午10:15
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.core.checking;

import io.github.bloquesoft.entity.core.definition.ListFieldDefinition;
import io.github.bloquesoft.entity.core.definition.basicValue.*;
import io.github.bloquesoft.entity.core.error.template.LogicErrorTemplate;
import io.github.bloquesoft.entity.core.logic.error.LogicException;
import io.github.bloquesoft.entity.core.object.fieldObject.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.powermock.api.mockito.PowerMockito;

import java.util.ArrayList;
import java.util.Date;

class StaticCheckingForFieldObjectVisitorTest {


    @Test
    void visit_String() {

        LogicErrorTemplate.importDefault();
        StringFieldDefinition sfd = PowerMockito.mock(StringFieldDefinition.class);
        PowerMockito.when(sfd.getAllowEmpty()).thenReturn(false);
        PowerMockito.when(sfd.getName()).thenReturn("名称");
        PowerMockito.when(sfd.getMinLength()).thenReturn(6);
        PowerMockito.when(sfd.getMaxLength()).thenReturn(50);

        StringFieldObject sfo = PowerMockito.mock(StringFieldObject.class);
        PowerMockito.when(sfo.getStringFieldDefinition()).thenReturn(sfd);
        PowerMockito.when(sfo.isEmptyValue()).thenReturn(true);

        StaticCheckingForFieldObjectVisitor visitor = new StaticCheckingForFieldObjectVisitor();
        LogicException exception = Assertions.assertThrows(LogicException.class, () -> visitor.visit(sfo));
        Assertions.assertTrue(exception.getErrorMessage().contains("不能为空"));

        PowerMockito.when(sfo.isEmptyValue()).thenReturn(false);
        PowerMockito.when(sfo.getValue()).thenReturn("abcde");
        exception = Assertions.assertThrows(LogicException.class, () -> visitor.visit(sfo));
        Assertions.assertTrue(exception.getErrorMessage().contains("之间"));

        PowerMockito.when(sfo.getValue()).thenReturn("abcdef");
        Assertions.assertEquals(visitor.visit(sfo), null);
    }

    @Test
    void visit_Integer() {
        LogicErrorTemplate.importDefault();
        IntegerFieldDefinition ifd = PowerMockito.mock(IntegerFieldDefinition.class);
        PowerMockito.when(ifd.getAllowEmpty()).thenReturn(false);
        PowerMockito.when(ifd.getName()).thenReturn("名称");
        PowerMockito.when(ifd.getMin()).thenReturn(1);
        PowerMockito.when(ifd.getMax()).thenReturn(100);

        IntegerFieldObject sfo = PowerMockito.mock(IntegerFieldObject.class);
        PowerMockito.when(sfo.getIntegerFieldDefinition()).thenReturn(ifd);
        PowerMockito.when(sfo.isEmptyValue()).thenReturn(true);

        StaticCheckingForFieldObjectVisitor visitor = new StaticCheckingForFieldObjectVisitor();
        LogicException exception = Assertions.assertThrows(LogicException.class, () -> visitor.visit(sfo));
        Assertions.assertTrue(exception.getErrorMessage().contains("不能为空"));

        PowerMockito.when(sfo.isEmptyValue()).thenReturn(false);
        PowerMockito.when(sfo.getValue()).thenReturn(0);
        exception = Assertions.assertThrows(LogicException.class, () -> visitor.visit(sfo));
        Assertions.assertTrue(exception.getErrorMessage().contains("之间"));

        PowerMockito.when(sfo.getValue()).thenReturn(1);
        Assertions.assertEquals(visitor.visit(sfo), null);

    }

    @Test
    void visit_Long() {
        LogicErrorTemplate.importDefault();
        LongFieldDefinition ldf = PowerMockito.mock(LongFieldDefinition.class);
        PowerMockito.when(ldf.getAllowEmpty()).thenReturn(false);
        PowerMockito.when(ldf.getName()).thenReturn("名称");
        PowerMockito.when(ldf.getMin()).thenReturn(50L);
        PowerMockito.when(ldf.getMax()).thenReturn(1000L);

        LongFieldObject sfo = PowerMockito.mock(LongFieldObject.class);
        PowerMockito.when(sfo.getLongFieldDefinition()).thenReturn(ldf);
        PowerMockito.when(sfo.isEmptyValue()).thenReturn(true);

        StaticCheckingForFieldObjectVisitor visitor = new StaticCheckingForFieldObjectVisitor();
        LogicException exception = Assertions.assertThrows(LogicException.class, () -> visitor.visit(sfo));
        Assertions.assertTrue(exception.getErrorMessage().contains("不能为空"));

        PowerMockito.when(sfo.isEmptyValue()).thenReturn(false);
        PowerMockito.when(sfo.getValue()).thenReturn(1000000L);
        exception = Assertions.assertThrows(LogicException.class, () -> visitor.visit(sfo));
        Assertions.assertTrue(exception.getErrorMessage().contains("之间"));

        PowerMockito.when(sfo.getValue()).thenReturn(56L);
        Assertions.assertEquals(visitor.visit(sfo), null);
    }

    @Test
    void visit_Float() {
        LogicErrorTemplate.importDefault();
        FloatFieldDefinition fdf = PowerMockito.mock(FloatFieldDefinition.class);
        PowerMockito.when(fdf.getAllowEmpty()).thenReturn(false);
        PowerMockito.when(fdf.getName()).thenReturn("名称");
        PowerMockito.when(fdf.getMin()).thenReturn(1f);
        PowerMockito.when(fdf.getMax()).thenReturn(100f);

        FloatFieldObject ffo = PowerMockito.mock(FloatFieldObject.class);
        PowerMockito.when(ffo.getFloatFieldDefinition()).thenReturn(fdf);
        PowerMockito.when(ffo.isEmptyValue()).thenReturn(true);

        StaticCheckingForFieldObjectVisitor visitor = new StaticCheckingForFieldObjectVisitor();
        LogicException exception = Assertions.assertThrows(LogicException.class, () -> visitor.visit(ffo));
        Assertions.assertTrue(exception.getErrorMessage().contains("不能为空"));

        PowerMockito.when(ffo.isEmptyValue()).thenReturn(false);
        PowerMockito.when(ffo.getValue()).thenReturn(1000000f);
        exception = Assertions.assertThrows(LogicException.class, () -> visitor.visit(ffo));
        Assertions.assertTrue(exception.getErrorMessage().contains("之间"));

        PowerMockito.when(ffo.getValue()).thenReturn(56f);
        Assertions.assertEquals(visitor.visit(ffo), null);
    }

    @Test
    void visit_Double() {
        LogicErrorTemplate.importDefault();
        DoubleFieldDefinition fdf = PowerMockito.mock(DoubleFieldDefinition.class);
        PowerMockito.when(fdf.getAllowEmpty()).thenReturn(false);
        PowerMockito.when(fdf.getName()).thenReturn("名称");
        PowerMockito.when(fdf.getMin()).thenReturn(1d);
        PowerMockito.when(fdf.getMax()).thenReturn(100d);

        DoubleFieldObject ffo = PowerMockito.mock(DoubleFieldObject.class);
        PowerMockito.when(ffo.getDoubleFieldDefinition()).thenReturn(fdf);
        PowerMockito.when(ffo.isEmptyValue()).thenReturn(true);

        StaticCheckingForFieldObjectVisitor visitor = new StaticCheckingForFieldObjectVisitor();
        LogicException exception = Assertions.assertThrows(LogicException.class, () -> visitor.visit(ffo));
        Assertions.assertTrue(exception.getErrorMessage().contains("不能为空"));

        PowerMockito.when(ffo.isEmptyValue()).thenReturn(false);
        PowerMockito.when(ffo.getValue()).thenReturn(1000000d);
        exception = Assertions.assertThrows(LogicException.class, () -> visitor.visit(ffo));
        Assertions.assertTrue(exception.getErrorMessage().contains("之间"));

        PowerMockito.when(ffo.getValue()).thenReturn(56d);
        Assertions.assertEquals(visitor.visit(ffo), null);
    }

    @Test
    void test_Boolean() {
        LogicErrorTemplate.importDefault();
        BooleanFieldDefinition fdf = PowerMockito.mock(BooleanFieldDefinition.class);
        PowerMockito.when(fdf.getAllowEmpty()).thenReturn(false);
        PowerMockito.when(fdf.getName()).thenReturn("名称");

        BooleanFieldObject ffo = PowerMockito.mock(BooleanFieldObject.class);
        PowerMockito.when(ffo.getBooleanFieldDefinition()).thenReturn(fdf);
        PowerMockito.when(ffo.isEmptyValue()).thenReturn(true);

        StaticCheckingForFieldObjectVisitor visitor = new StaticCheckingForFieldObjectVisitor();
        LogicException exception = Assertions.assertThrows(LogicException.class, () -> visitor.visit(ffo));
        Assertions.assertTrue(exception.getErrorMessage().contains("不能为空"));

        PowerMockito.when(ffo.isEmptyValue()).thenReturn(false);
        PowerMockito.when(ffo.getValue()).thenReturn(true);
        Assertions.assertEquals(visitor.visit(ffo), null);
    }

    @Test
    void visit_Date() {
        LogicErrorTemplate.importDefault();
        DateFieldDefinition fdf = PowerMockito.mock(DateFieldDefinition.class);
        PowerMockito.when(fdf.getAllowEmpty()).thenReturn(false);
        PowerMockito.when(fdf.getName()).thenReturn("名称");

        DateFieldObject ffo = PowerMockito.mock(DateFieldObject.class);
        PowerMockito.when(ffo.getDateFieldDefinition()).thenReturn(fdf);
        PowerMockito.when(ffo.isEmptyValue()).thenReturn(true);

        StaticCheckingForFieldObjectVisitor visitor = new StaticCheckingForFieldObjectVisitor();
        LogicException exception = Assertions.assertThrows(LogicException.class, () -> visitor.visit(ffo));
        Assertions.assertTrue(exception.getErrorMessage().contains("不能为空"));

        PowerMockito.when(ffo.isEmptyValue()).thenReturn(false);
        PowerMockito.when(ffo.getValue()).thenReturn(new Date());
        Assertions.assertEquals(visitor.visit(ffo), null);
    }

    @Test
    void visit_Datetime() {
        LogicErrorTemplate.importDefault();
        DateTimeFieldDefinition fdf = PowerMockito.mock(DateTimeFieldDefinition.class);
        PowerMockito.when(fdf.getAllowEmpty()).thenReturn(false);
        PowerMockito.when(fdf.getName()).thenReturn("名称");

        DateTimeFieldObject ffo = PowerMockito.mock(DateTimeFieldObject.class);
        PowerMockito.when(ffo.getDateTimeFieldDefinition()).thenReturn(fdf);
        PowerMockito.when(ffo.isEmptyValue()).thenReturn(true);

        StaticCheckingForFieldObjectVisitor visitor = new StaticCheckingForFieldObjectVisitor();
        LogicException exception = Assertions.assertThrows(LogicException.class, () -> visitor.visit(ffo));
        Assertions.assertTrue(exception.getErrorMessage().contains("不能为空"));

        PowerMockito.when(ffo.isEmptyValue()).thenReturn(false);
        PowerMockito.when(ffo.getValue()).thenReturn(new Date());
        Assertions.assertEquals(visitor.visit(ffo), null);
    }

    @Test
    void visit_Entity() {

        EntityFieldObject ffo = PowerMockito.mock(EntityFieldObject.class);
        StaticCheckingForFieldObjectVisitor visitor = new StaticCheckingForFieldObjectVisitor();
        Assertions.assertEquals(visitor.visit(ffo), null);

    }

    @Test
    void visit_List() {
        LogicErrorTemplate.importDefault();
        ListFieldDefinition fdf = PowerMockito.mock(ListFieldDefinition.class);
        PowerMockito.when(fdf.getAllowEmpty()).thenReturn(false);
        PowerMockito.when(fdf.getName()).thenReturn("名称");

        ListFieldObject ffo = PowerMockito.mock(ListFieldObject.class);
        PowerMockito.when(ffo.getListFieldDefinition()).thenReturn(fdf);
        PowerMockito.when(ffo.isEmptyValue()).thenReturn(true);

        StaticCheckingForFieldObjectVisitor visitor = new StaticCheckingForFieldObjectVisitor();
        LogicException exception = Assertions.assertThrows(LogicException.class, () -> visitor.visit(ffo));
        Assertions.assertTrue(exception.getErrorMessage().contains("不能为空"));

        PowerMockito.when(ffo.isEmptyValue()).thenReturn(false);
        PowerMockito.when(ffo.getValue()).thenReturn(new ArrayList());
        Assertions.assertEquals(visitor.visit(ffo), null);
    }
}