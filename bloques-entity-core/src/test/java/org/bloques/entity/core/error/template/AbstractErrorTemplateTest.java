/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:AbstractErrorTemplateTest.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.core.error.template;

import junit.framework.TestCase;
import org.bloques.entity.core.resource.i18n.ResourceReader;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Locale;
import java.util.Map;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AbstractErrorTemplateTest extends TestCase {

    @Test
    public void test_1_Input() {

        ResourceReader reader = new ResourceReader();
        Map<String, String> map = reader.readResourceMap("i18n/logicErrorTemplate", new Locale("default", ""));
        LogicErrorTemplate.getSingleton().input(map);
        Assert.assertTrue(LogicErrorTemplate.getSingleton().getKeys().size() == 6);
    }

    public void test_2_GetErrorTemplate() {

        String value = LogicErrorTemplate.getSingleton().getErrorTemplate(LogicErrorTemplate.FIELD_VALUE_NOT_VALID_ERROR);
        Assert.assertTrue(value.equals("%s值无效"));
    }
}