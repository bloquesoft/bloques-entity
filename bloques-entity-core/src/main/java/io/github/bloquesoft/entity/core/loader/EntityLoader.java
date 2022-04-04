/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:EntityLoader.java
 * Author:zhangjian
 * date:2022/3/31 下午10:15
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.core.loader;

import io.github.bloquesoft.entity.core.object.fieldObject.EntityFieldObject;
import io.github.bloquesoft.entity.core.object.fieldObject.ListFieldObject;
import io.github.bloquesoft.entity.core.repository.EntityRepositoryFactory;

public interface EntityLoader
{
    void loadListField(ListFieldObject listFieldObject, EntityRepositoryFactory entityRepositoryFactory);

    void loadEntityField(EntityFieldObject entityFieldObject, EntityRepositoryFactory entityRepositoryFactory);
}