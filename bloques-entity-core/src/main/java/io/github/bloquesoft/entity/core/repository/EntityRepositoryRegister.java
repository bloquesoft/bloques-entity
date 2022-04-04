/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:EntityRepositoryRegister.java
 * Author:zhangjian
 * date:2022/3/31 下午10:15
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.core.repository;

public interface EntityRepositoryRegister extends EntityRepositoryFactory
{
    void register(String entityDefinitionId, EntityRepository entityRepository);
}