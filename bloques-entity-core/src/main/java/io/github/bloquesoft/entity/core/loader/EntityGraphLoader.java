/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:EntityGraphLoader.java
 * Author:zhangjian
 * date:2022/3/31 下午10:15
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.core.loader;

import io.github.bloquesoft.entity.core.object.AbstractEntityObject;
import io.github.bloquesoft.entity.core.definition.EntityDefinition;

import java.util.List;

public interface EntityGraphLoader
{
    void load(AbstractEntityObject entityObj, String graph);

    void load(EntityDefinition ed, Object entity, String graph);

    void loadList(EntityDefinition ed, List<Object> list, String graph);

    void loadList(List<AbstractEntityObject> list, String graph);
}
