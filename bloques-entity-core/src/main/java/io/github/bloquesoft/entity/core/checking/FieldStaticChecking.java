/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:FieldStaticChecking.java
 * Author:zhangjian
 * date:2022/4/2 下午10:04
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.core.checking;

import io.github.bloquesoft.entity.core.logic.error.LogicException;
import io.github.bloquesoft.entity.core.object.fieldObject.AbstractFieldObject;

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