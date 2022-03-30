/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:EntityLoader.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.core.loader;

import org.bloques.entity.core.object.fieldObject.EntityFieldObject;
import org.bloques.entity.core.object.fieldObject.ListFieldObject;
import org.bloques.entity.core.repository.EntityRepositoryFactory;

public interface EntityLoader
{
    void loadListField(ListFieldObject listFieldObject, EntityRepositoryFactory entityRepositoryFactory);

    void loadEntityField(EntityFieldObject entityFieldObject, EntityRepositoryFactory entityRepositoryFactory);
}