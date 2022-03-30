/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:ErrorTemplateConfiguration.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.core.error.template;

import lombok.Data;

@Data
public class ErrorTemplateConfiguration {

    String path;

    String lang;

    String area;

    public ErrorTemplateConfiguration() {
    }

    public ErrorTemplateConfiguration(String path, String lang, String area) {
        this.path = path;
        this.lang = lang;
        this.area = area;
    }
}
