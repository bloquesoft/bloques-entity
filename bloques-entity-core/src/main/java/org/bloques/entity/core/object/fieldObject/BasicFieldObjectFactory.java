/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:BasicFieldObjectFactory.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.core.object.fieldObject;

import org.bloques.entity.core.definition.basicValue.*;
import org.bloques.entity.core.object.AbstractEntityObject;
import org.springframework.util.Assert;

public class BasicFieldObjectFactory {
    public static AbstractBasicFieldObject create(AbstractEntityObject entityObj, AbstractBasicValueFieldDefinition basicFieldDefinition) {
        Assert.notNull(entityObj);
        Assert.notNull(basicFieldDefinition);
        if (basicFieldDefinition instanceof StringFieldDefinition) {
            return new StringFieldObject(entityObj, (StringFieldDefinition) basicFieldDefinition);
        }
        if (basicFieldDefinition instanceof IntegerFieldDefinition) {
            return new IntegerFieldObject(entityObj, (IntegerFieldDefinition) basicFieldDefinition);
        }
        if (basicFieldDefinition instanceof LongFieldDefinition) {
            return new LongFieldObject(entityObj, (LongFieldDefinition) basicFieldDefinition);
        }
        if (basicFieldDefinition instanceof FloatFieldDefinition) {
            return new FloatFieldObject(entityObj, (FloatFieldDefinition) basicFieldDefinition);
        }
        if (basicFieldDefinition instanceof DoubleFieldDefinition) {
            return new DoubleFieldObject(entityObj, (DoubleFieldDefinition) basicFieldDefinition);
        }
        if (basicFieldDefinition instanceof BooleanFieldDefinition) {
            return new BooleanFieldObject(entityObj, (BooleanFieldDefinition) basicFieldDefinition);
        }
        if (basicFieldDefinition instanceof DateFieldDefinition) {
            return new DateFieldObject(entityObj, (DateFieldDefinition) basicFieldDefinition);
        }
        if (basicFieldDefinition instanceof DateTimeFieldDefinition) {
            return new DateTimeFieldObject(entityObj, (DateTimeFieldDefinition) basicFieldDefinition);
        }
        throw new IllegalArgumentException("JEntity Could not recognize basicFieldDefinition type," + entityObj);
    }
}
