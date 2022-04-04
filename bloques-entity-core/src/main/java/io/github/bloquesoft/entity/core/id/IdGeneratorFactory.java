/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:IdGeneratorFactory.java
 * Author:zhangjian
 * date:2022/3/31 下午10:15
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.core.id;

import io.github.bloquesoft.entity.core.definition.EntityDefinition;

public class IdGeneratorFactory
{
    private final LongIdGenerator generator = new SnowflakeIdGenerator(0,0);

    private static final IdGeneratorFactory FACTORY = new IdGeneratorFactory();

    public static IdGeneratorFactory singleton(){
        return FACTORY;
    }

    public LongIdGenerator getGenerator(EntityDefinition ed){
        return generator;
    }
}