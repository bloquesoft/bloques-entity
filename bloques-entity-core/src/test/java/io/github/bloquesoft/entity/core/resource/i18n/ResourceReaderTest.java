/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:ResourceReaderTest.java
 * Author:zhangjian
 * date:2022/3/31 下午10:15
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.core.resource.i18n;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.Map;

class ResourceReaderTest {

    @Test
    public void testReadResourceMap()
    {
        ResourceReader reader = new ResourceReader();
        Map<String, String> map = reader.readResourceMap("i18n/logicErrorTemplate", new Locale("default", ""));
        map.get("fieldEmptyError");
        Assert.assertTrue(map.size() > 0);
        Assert.assertEquals("%s不能为空", map.get("fieldEmptyError"));
    }
}