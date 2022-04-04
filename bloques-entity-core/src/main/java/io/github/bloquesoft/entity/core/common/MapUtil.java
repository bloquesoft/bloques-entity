/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:MapUtil.java
 * Author:zhangjian
 * date:2022/3/31 下午10:15
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.core.common;

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
