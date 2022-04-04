/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:AbstractErrorTemplate.java
 * Author:zhangjian
 * date:2022/3/31 下午10:15
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.core.error.template;

import io.github.bloquesoft.entity.core.resource.i18n.ResourceReader;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public abstract class AbstractErrorTemplate {

    public abstract Set<String> getKeys();

    private final Map<String, String> map = new HashMap<>();

    public void input(Map<String, String> templateMap) {
        map.clear();
        for (String key : getKeys()) {
            String template = templateMap.get(key);
            Assert.hasLength(template, "Not Config Error Template key:" + key);
            map.put(key, templateMap.get(key));
        }
    }

    public void input(ErrorTemplateConfiguration configuration) {
        ResourceReader reader = new ResourceReader();
        Map<String, String> map = reader.readResourceMap(configuration.getPath(), new Locale(configuration.getLang(), configuration.getArea()));
        this.input(map);
    }

    public String getErrorTemplate(String key) {
        return map.get(key);
    }
}
