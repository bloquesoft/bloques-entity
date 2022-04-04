/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:StaticCheckingForFieldObjectVisitor.java
 * Author:zhangjian
 * date:2022/4/2 下午10:04
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.core.checking;

import io.github.bloquesoft.entity.core.definition.ListFieldDefinition;
import io.github.bloquesoft.entity.core.definition.basicValue.*;
import io.github.bloquesoft.entity.core.logic.error.LogicErrors;
import io.github.bloquesoft.entity.core.object.fieldObject.*;

public class StaticCheckingForFieldObjectVisitor implements FieldObjectVisitor {

    @Override
    public Object visit(StringFieldObject stringFieldObject) {

        StringFieldDefinition fd = stringFieldObject.getStringFieldDefinition();
        if (stringFieldObject.isEmptyValue()) {
            if (!fd.getAllowEmpty()) {
                LogicErrors.FIELD_EMPTY_ERROR.throwException(fd);
            }
        } else {
            String strFieldValue = stringFieldObject.getValue().toString();
            if ((strFieldValue.length() < fd.getMinLength() && fd.getMinLength() >= 0) || (strFieldValue.length() > fd.getMaxLength() && fd.getMaxLength() >= 0)) {
                LogicErrors.STRING_FIELD_RANGE_ERROR.throwException(fd);
            }
        }
        return null;
    }

    @Override
    public Object visit(IntegerFieldObject integerFieldObject) {
        IntegerFieldDefinition fd = integerFieldObject.getIntegerFieldDefinition();
        if (integerFieldObject.isEmptyValue()) {
            if (!fd.getAllowEmpty()) {
                LogicErrors.FIELD_EMPTY_ERROR.throwException(fd);
            }
        } else {
            Integer integerFieldValue = (Integer) integerFieldObject.getValue();
            if (integerFieldValue < fd.getMin() || integerFieldValue > fd.getMax()) {
                LogicErrors.INTEGER_FIELD_RANGE_ERROR.throwException(fd);
            }
        }
        return null;
    }

    @Override
    public Object visit(LongFieldObject longFieldObject) {
        LongFieldDefinition fd = longFieldObject.getLongFieldDefinition();
        if (longFieldObject.isEmptyValue()) {
            if (!fd.getAllowEmpty()) {
                LogicErrors.FIELD_EMPTY_ERROR.throwException(fd);
            }
        } else {
            Long longFieldValue = (Long) longFieldObject.getValue();
            if (longFieldValue < fd.getMin() || longFieldValue > fd.getMax()) {
                LogicErrors.LONG_FIELD_RANGE_ERROR.throwException(fd);
            }
        }
        return null;
    }

    @Override
    public Object visit(FloatFieldObject floatFieldObject) {
        FloatFieldDefinition fd = floatFieldObject.getFloatFieldDefinition();
        if (floatFieldObject.isEmptyValue()) {
            if (!fd.getAllowEmpty()) {
                LogicErrors.FIELD_EMPTY_ERROR.throwException(fd);
            }
        } else {
            Float floatValue = (Float) floatFieldObject.getValue();
            if (floatValue < fd.getMin() || floatValue > fd.getMax()) {
                LogicErrors.LONG_FIELD_RANGE_ERROR.throwException(fd);
            }
        }
        return null;
    }

    @Override
    public Object visit(DoubleFieldObject doubleFieldObject) {
        DoubleFieldDefinition fd = doubleFieldObject.getDoubleFieldDefinition();
        if (doubleFieldObject.isEmptyValue()) {
            if (!fd.getAllowEmpty()) {
                LogicErrors.FIELD_EMPTY_ERROR.throwException(fd);
            }
        } else {
            Double doubleValue = (Double) doubleFieldObject.getValue();
            if (doubleValue < fd.getMin() || doubleValue > fd.getMax()) {
                LogicErrors.LONG_FIELD_RANGE_ERROR.throwException(fd);
            }
        }
        return null;
    }

    @Override
    public Object visit(BooleanFieldObject booleanFieldObject) {
        BooleanFieldDefinition fd = booleanFieldObject.getBooleanFieldDefinition();
        if (booleanFieldObject.isEmptyValue()) {
            if (!fd.getAllowEmpty()) {
                LogicErrors.FIELD_EMPTY_ERROR.throwException(fd);
            }
        }
        return null;
    }

    @Override
    public Object visit(DateFieldObject dateFieldObject) {
        DateFieldDefinition fd = dateFieldObject.getDateFieldDefinition();
        if (dateFieldObject.isEmptyValue()) {
            if (!fd.getAllowEmpty()) {
                LogicErrors.FIELD_EMPTY_ERROR.throwException(fd);
            }
        }
        return null;
    }

    @Override
    public Object visit(DateTimeFieldObject dateTimeFieldObject) {

        DateTimeFieldDefinition fd = dateTimeFieldObject.getDateTimeFieldDefinition();
        if (dateTimeFieldObject.isEmptyValue()) {
            if (!fd.getAllowEmpty()) {
                LogicErrors.FIELD_EMPTY_ERROR.throwException(fd);
            }
        }
        return null;
    }

    @Override
    public Object visit(EntityFieldObject entityFieldObject) {
        return null;
    }

    @Override
    public Object visit(ListFieldObject listFieldObject) {

        ListFieldDefinition fd = listFieldObject.getListFieldDefinition();
        if (listFieldObject.isEmptyValue()) {
            if (!fd.getAllowEmpty()) {
                LogicErrors.FIELD_EMPTY_ERROR.throwException(fd);
            }
        }
        return null;
    }
}