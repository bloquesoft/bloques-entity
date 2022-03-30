/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:EntityObjectFactory.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.core.object;

import org.bloques.entity.core.definition.EntityDefinition;

public interface EntityObjectFactory
{
    AbstractEntityObject create(EntityDefinition entityDefinition, Object value);
}