/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:ResourceReader.java
 * Author:zhangjian
 * date:2022/3/31 下午10:15
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.core.resource.i18n;

import java.util.*;

public class ResourceReader {

    public Map<String, String> readResourceMap(String path, Locale locale) {
        ResourceBundle resourceBoudle = ResourceBundle.getBundle(path, locale);
        Map<String, String> map = new HashMap<>();
        Enumeration<String> keys = resourceBoudle.getKeys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            map.put(key, resourceBoudle.getString(key));
        }
        return map;
    }
}