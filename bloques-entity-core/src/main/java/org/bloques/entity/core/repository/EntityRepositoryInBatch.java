/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:EntityRepositoryInBatch.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.core.repository;

import org.bloques.entity.core.object.AbstractEntityObject;

public interface EntityRepositoryInBatch
{
    void appendForAdd(AbstractEntityObject entityObject);

    void appendForUpdate(AbstractEntityObject entityObject);

    void appendForDelete(AbstractEntityObject entityObject);

    void commit(Boolean transaction);
}