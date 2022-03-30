/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:AbstractLogicError.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.core.logic.error;

import org.bloques.entity.core.error.template.LogicErrorTemplate;
import org.springframework.util.Assert;

public abstract class AbstractLogicError {

    private String template;

    public abstract String getErrorCode();

    protected abstract String getErrorTemplateKey();

    protected String getErrorTemplate() {
        if (template == null) {
            synchronized (this) {
                if (template == null) {
                    template = LogicErrorTemplate.getSingleton().getErrorTemplate(getErrorTemplateKey());
                    Assert.notNull(template, "Could not find error template, key:" + getErrorTemplateKey());
                }
            }
        }
        return template;
    }
}