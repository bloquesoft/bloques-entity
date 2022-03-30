/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-clazz
 * FileName:PrimaryKeyReader.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.clazz.reader;

import org.bloques.entity.core.common.AbstractResponsibleNode;
import org.bloques.entity.core.common.ResponsibleChainResult;
import org.bloques.entity.core.definition.PrimaryKeyDefinition;
import org.bloques.entity.clazz.annotation.PrimaryKey;
import org.springframework.util.Assert;

import java.lang.reflect.Field;

public class PrimaryKeyReader extends AbstractResponsibleNode implements DefinitionReader<Field, PrimaryKeyDefinition> {

    @Override
    public PrimaryKeyDefinition read(Field field)
    {
        boolean pkAnnotationPresent = field.isAnnotationPresent(PrimaryKey.class);
        if (pkAnnotationPresent)
        {
            Assert.isTrue(Long.class.equals(field.getType()), "Primary key field type must be Long type," + field.getDeclaringClass().getName() + "." + field.getName());
            return new PrimaryKeyDefinition(field.getName());
        }
        return null;
    }

    @Override
    public ResponsibleChainResult exec(Object inputParamter) {
        if (inputParamter instanceof Field)
        {
            Field field = (Field)inputParamter;
            PrimaryKeyDefinition pk = this.read(field);
            Boolean success = pk != null;
            return new ResponsibleChainResult(success, pk);
        }
        return new ResponsibleChainResult(false, null);
    }
}