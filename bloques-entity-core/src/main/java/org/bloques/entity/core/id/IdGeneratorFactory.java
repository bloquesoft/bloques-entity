/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:IdGeneratorFactory.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.core.id;

import org.bloques.entity.core.definition.EntityDefinition;

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