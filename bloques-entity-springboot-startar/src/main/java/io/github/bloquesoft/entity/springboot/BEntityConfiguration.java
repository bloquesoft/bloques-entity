/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-springboot-startar
 * FileName:BEntityConfiguration.java
 * Author:zhangjian
 * date:2022/4/2 下午9:08
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.springboot;

import lombok.Getter;
import lombok.Setter;
import io.github.bloquesoft.entity.core.error.template.ErrorTemplateConfiguration;
import io.github.bloquesoft.entity.core.object.EntityObjectFactory;

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