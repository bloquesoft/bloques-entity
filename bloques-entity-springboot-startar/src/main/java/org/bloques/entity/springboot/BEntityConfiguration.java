/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-springboot-startar
 * FileName:BEntityConfiguration.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.springboot;

import lombok.Getter;
import lombok.Setter;
import org.bloques.entity.core.error.template.ErrorTemplateConfiguration;
import org.bloques.entity.core.object.EntityObjectFactory;

import java.util.ArrayList;
import java.util.List;


public class BEntityConfiguration {

    @Setter
    @Getter
    private ErrorTemplateConfiguration logicErrorTemplateConfiguration;

    @Getter
    private final List<EntityObjectFactory> entityObjectFactoryList = new ArrayList<>();

    public BEntityConfiguration setLogicErrorTemplateConfiguration(ErrorTemplateConfiguration logicErrorTemplateConfiguration){
        this.logicErrorTemplateConfiguration  = logicErrorTemplateConfiguration;
        return this;
    }

    public void appendEntityObjectFactory(EntityObjectFactory entityObjectFactory){
        this.entityObjectFactoryList.add(entityObjectFactory);
    }
}