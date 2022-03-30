/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:FieldObjectFormatVisitor.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.core.object.fieldObject;

import org.bloques.entity.core.common.DateUtil;
import org.bloques.entity.core.common.NumberUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FieldObjectFormatVisitor implements FieldObjectVisitor {

    @Override
    public Object visit(StringFieldObject stringFieldObject) {
        Object value = stringFieldObject.getValue();
        if (value != null) {
            if (value instanceof String) {
                return value;
            } else {
                String strValue = value.toString();
                stringFieldObject.setValue(strValue);
                return strValue;
            }
        }
        return null;
    }

    @Override
    public Object visit(IntegerFieldObject integerFieldObject) {
        Object value = integerFieldObject.getValue();
        if (value != null) {
            if (value instanceof Integer) {
                return value;
            }
            if (value instanceof String || value instanceof Long) {
                try {
                    Integer intValue = Integer.valueOf(value.toString());
                    integerFieldObject.setValue(intValue);
                    return intValue;
                } catch (NumberFormatException ept) {
                    throw new IllegalArgumentException(String.format("JEntity could not format field to integer, field:%s.%s,value:%s", integerFieldObject.getEntityDefinition().getId(), integerFieldObject.getFieldId(), value));
                }
            }
            if (value instanceof Boolean) {
                Boolean bValue = (Boolean) value;
                if (bValue) {
                    integerFieldObject.setValue(1);
                    return 1;
                } else {
                    integerFieldObject.setValue(0);
                    return 0;
                }
            }
            throw new IllegalArgumentException(String.format("JEntity could not support format field to integer, field:%s.%s,value:%s", integerFieldObject.getEntityDefinition().getId(), integerFieldObject.getFieldId(), value));
        }
        return null;
    }

    @Override
    public Object visit(LongFieldObject longFieldObject) {
        Object value = longFieldObject.getValue();
        if (value != null) {
            if (value instanceof Long) {
                return value;
            }
            if (value instanceof String || value instanceof Integer || value instanceof Float || value instanceof Double) {
                try {
                    Long longValue = Long.valueOf(value.toString());
                    longFieldObject.setValue(longValue);
                    return longValue;
                } catch (NumberFormatException ept) {
                    throw new IllegalArgumentException(String.format("JEntity could not format field to long, field:%s.%s,value:%s", longFieldObject.getEntityDefinition().getId(), longFieldObject.getFieldId(), value));
                }
            }
            if (value instanceof Boolean) {
                Boolean bValue = (Boolean) value;
                if (bValue) {
                    longFieldObject.setValue(1L);
                    return 1L;
                } else {
                    longFieldObject.setValue(0L);
                    return 0L;
                }
            }
            throw new IllegalArgumentException(String.format("JEntity could not support format field to long, field:%s.%s,value:%s", longFieldObject.getEntityDefinition().getId(), longFieldObject.getFieldId(), value));
        }
        return null;
    }

    @Override
    public Object visit(FloatFieldObject floatFieldObject) {
        Object value = floatFieldObject.getValue();
        if (value != null) {
            if (value instanceof Float) {
                return value;
            }
            if (value instanceof String) {
                try {
                    Float floatValue = Float.valueOf(value.toString());
                    Float formattedValue = NumberUtil.formatFloat(floatValue, floatFieldObject.getFloatFieldDefinition().getDecimalDigit());
                    floatFieldObject.setValue(formattedValue);
                    return formattedValue;
                } catch (NumberFormatException ept) {
                    throw new IllegalArgumentException(String.format("JEntity could not format field to float, field:%s.%s,value:%s", floatFieldObject.getEntityDefinition().getId(), floatFieldObject.getFieldId(), value));
                }
            }

            if (value instanceof Integer || value instanceof Long || value instanceof Double) {
                Float floatValue = NumberUtil.formatFloat(value, floatFieldObject.getFloatFieldDefinition().getDecimalDigit());
                floatFieldObject.setValue(floatValue);
                return floatValue;
            }

            if (value instanceof Boolean) {
                Boolean bValue = (Boolean) value;
                if (bValue) {
                    floatFieldObject.setValue(1f);
                    return 1f;
                } else {
                    floatFieldObject.setValue(0f);
                    return 0f;
                }
            }
            throw new IllegalArgumentException(String.format("JEntity could not support format field to float, field:%s.%s,value:%s", floatFieldObject.getEntityDefinition().getId(), floatFieldObject.getFieldId(), value));
        }
        return null;
    }

    @Override
    public Object visit(DoubleFieldObject doubleFieldObject) {
        Object value = doubleFieldObject.getValue();
        if (value != null) {
            if (value instanceof Double) {
                return value;
            }
            if (value instanceof String) {
                try {
                    Double doubleValue = Double.valueOf(value.toString());
                    Double formattedValue = NumberUtil.formatDouble(doubleValue, doubleFieldObject.getDoubleFieldDefinition().getDecimalDigit());
                    doubleFieldObject.setValue(formattedValue);
                    return formattedValue;
                } catch (NumberFormatException ept) {
                    throw new IllegalArgumentException(String.format("JEntity could not format field to double, field:%s.%s,value:%s", doubleFieldObject.getEntityDefinition().getId(), doubleFieldObject.getFieldId(), value));
                }
            }

            if (value instanceof Integer || value instanceof Long || value instanceof Float) {
                Double doubleValue = NumberUtil.formatDouble(value, doubleFieldObject.getDoubleFieldDefinition().getDecimalDigit());
                doubleFieldObject.setValue(doubleValue);
                return doubleValue;
            }
            if (value instanceof Boolean) {
                Boolean bValue = (Boolean) value;
                if (bValue) {
                    doubleFieldObject.setValue(1d);
                    return 1d;
                } else {
                    doubleFieldObject.setValue(0d);
                    return 0d;
                }
            }
            throw new IllegalArgumentException(String.format("JEntity could not support format field to double, field:%s.%s,value:%s", doubleFieldObject.getEntityDefinition().getId(), doubleFieldObject.getFieldId(), value));
        }
        return null;
    }

    @Override
    public Object visit(BooleanFieldObject booleanFieldObject) {
        Object value = booleanFieldObject.getValue();
        if (value == null) {
            return null;
        }
        if (value instanceof Boolean) {
            return value;
        }
        if (value instanceof Integer || value instanceof Long) {
            long dValue = Long.parseLong(value.toString());
            if (dValue == 0L) {
                booleanFieldObject.setValue(false);
                return false;
            }
            if (dValue == 1L) {
                booleanFieldObject.setValue(true);
                return true;
            }
        }
        if (value instanceof String) {
            if (value.toString().equalsIgnoreCase("false") || value.toString().equals("0") || value.toString().equals("")) {
                booleanFieldObject.setValue(false);
                return false;
            }
            if (value.toString().equalsIgnoreCase("true") || value.toString().equals("1")) {
                booleanFieldObject.setValue(true);
                return true;
            }
            throw new IllegalArgumentException(String.format("JEntity could not format field to boolean, field:%s.%s,value:%s", booleanFieldObject.getEntityDefinition().getId(), booleanFieldObject.getFieldId(), value));
        }
        throw new IllegalArgumentException(String.format("JEntity could not support format field to boolean, field:%s.%s,value:%s", booleanFieldObject.getEntityDefinition().getId(), booleanFieldObject.getFieldId(), value));
    }

    @Override
    public Object visit(DateFieldObject dateFieldObject) {
        Object value = dateFieldObject.getValue();
        if (value == null) {
            return null;
        }
        if (value instanceof Date) {
            Date dDate = (Date) value;
            Date dateValue = DateUtil.formatToDate(dDate);
            dateFieldObject.setValue(dateValue);
            return dateValue;
        }
        if (value instanceof String) {
            String strDate = value.toString().toLowerCase();
            if (strDate.equals("")) {
                dateFieldObject.setValue(null);
                return null;
            }
            if (strDate.equals("now")) {
                Date dDate = DateUtil.formatToDate(new Date());
                dateFieldObject.setValue(dDate);
                return dDate;
            }
            return formatForString(dateFieldObject, strDate);
        }
        throw new IllegalArgumentException(String.format("JEntity could not support format field to date, field:%s.%s,value:%s", dateFieldObject.getEntityDefinition().getId(), dateFieldObject.getFieldId(), value));
    }

    @Override
    public Object visit(DateTimeFieldObject dateTimeFieldObject) {
        Object value = dateTimeFieldObject.getValue();
        if (value == null) {
            return null;
        }
        if (value instanceof Date) {
            return value;
        }
        if (value instanceof String) {
            String strDate = value.toString().toLowerCase();
            if (strDate.equals("")) {
                dateTimeFieldObject.setValue(null);
                return null;
            }
            if (strDate.equals("now")) {
                Date dateValue = new Date();
                dateTimeFieldObject.setValue(dateValue);
                return dateValue;
            }
            return formatForString(dateTimeFieldObject, strDate);
        }
        throw new IllegalArgumentException(String.format("JEntity could not support format field to datetime, field:%s.%s,value:%s", dateTimeFieldObject.getEntityDefinition().getId(), dateTimeFieldObject.getFieldId(), value));
    }

    @Override
    public Object visit(EntityFieldObject entityFieldObject) {
        Object value = entityFieldObject.getValue();
        if (value == null) {
            return null;
        }
        if (value.getClass().isPrimitive()) {
            throw new IllegalArgumentException(String.format("JEntity entity field value could not be primitive type,value:%s", value));
        }
        if (value instanceof String || value instanceof Date) {
            throw new IllegalArgumentException(String.format("JEntity entity field value could not be String or Date type,value:%s", value));
        }
        return value;
    }

    @Override
    public Object visit(ListFieldObject listFieldObject) {
        Object value = listFieldObject.getValue();
        if (value != null && !(value instanceof List)) {
            throw new IllegalArgumentException(String.format("JEntity field is not List, field:%s.%s,value:%s", listFieldObject.getEntityDefinition().getId(), listFieldObject.getFieldId(), value));
        }
        return value;
    }

    private Object formatForString(AbstractDateFieldObject entityFieldObject, String strDate) {
        SimpleDateFormat format = new SimpleDateFormat(entityFieldObject.getDateFieldDefinition().getDateFormat());
        try {
            Date dateValue = format.parse(strDate);
            entityFieldObject.setValue(dateValue);
            return dateValue;
        } catch (ParseException e) {
            throw new IllegalArgumentException(String.format("JEntity could not format field to date type, field:%s.%s,value:%s", entityFieldObject.getEntityDefinition().getId(), entityFieldObject.getFieldId(), strDate));
        }
    }
}