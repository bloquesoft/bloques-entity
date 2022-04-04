/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:EntityRepositoryInBatch.java
 * Author:zhangjian
 * date:2022/3/31 下午10:15
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.core.repository;

import io.github.bloquesoft.entity.core.object.AbstractEntityObject;

public interface EntityRepositoryInBatch
{
    void appendForAdd(AbstractEntityObject entityObject);

    void appendForUpdate(AbstractEntityObject entityObject);

    void appendForDelete(AbstractEntityObject entityObject);

    void commit(Boolean transaction);
}