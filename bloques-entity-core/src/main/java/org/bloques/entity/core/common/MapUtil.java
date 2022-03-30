/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:MapUtil.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.core.common;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MapUtil {

    public static <KeyT, ValueT> Set<ValueT> getSetByKey(Map<KeyT, Set<ValueT>> map, KeyT key) {
        if (map.containsKey(key)) {
            return map.get(key);
        }
        Set<ValueT> set = new HashSet<>();
        map.put(key, set);
        return set;
    }
}
