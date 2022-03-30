/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:FieldObjectVisitor.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.core.object.fieldObject;

public interface FieldObjectVisitor
{
    Object visit(StringFieldObject stringFieldObject);

    Object visit(IntegerFieldObject integerFieldObject);

    Object visit(LongFieldObject longFieldObject);

    Object visit(FloatFieldObject floatFieldObject);

    Object visit(DoubleFieldObject doubleFieldObject);

    Object visit(BooleanFieldObject booleanFieldObject);

    Object visit(DateFieldObject dateFieldObject);

    Object visit(DateTimeFieldObject dateTimeFieldObject);

    Object visit(EntityFieldObject entityFieldObject);

    Object visit(ListFieldObject listFieldObject);
}