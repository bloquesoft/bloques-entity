/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:EntityGraphLoader.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.core.loader;

import org.bloques.entity.core.definition.EntityDefinition;
import org.bloques.entity.core.object.AbstractEntityObject;

import java.util.List;

public interface EntityGraphLoader
{
    void load(AbstractEntityObject entityObj, String graph);

    void load(EntityDefinition ed, Object entity, String graph);

    void loadList(EntityDefinition ed, List<Object> list, String graph);

    void loadList(List<AbstractEntityObject> list, String graph);
}
