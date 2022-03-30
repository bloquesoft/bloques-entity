/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:FieldStaticChecking.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.core.checking;

import org.bloques.entity.core.logic.error.LogicException;
import org.bloques.entity.core.object.fieldObject.AbstractFieldObject;

public class FieldStaticChecking
{
    private static final FieldStaticChecking INSTANCE = new  FieldStaticChecking();

    public static FieldStaticChecking singleton()
    {
        return INSTANCE;
    }

    private final StaticCheckingForFieldObjectVisitor visitor = new StaticCheckingForFieldObjectVisitor();

    public void check(AbstractFieldObject fieldObject) throws LogicException {
        fieldObject.accept(visitor);
    }
}