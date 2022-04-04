/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:NumberUtil.java
 * Author:zhangjian
 * date:2022/3/31 下午10:15
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.core.common;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class NumberUtil {

    private static final Map<Integer, String> DECIMAL_FORMAT_MAP = new HashMap<>();

    public static Float formatFloat(Object value, int decimalDigit) {
        if (value instanceof Number) {
            String decimalStr = formatToDecimalString(value, decimalDigit);
            return Float.valueOf(decimalStr);
        }
        throw new IllegalArgumentException("formatFloat argus must be number.");
    }

    public static Double formatDouble(Object value, int decimalDigit) {

        if (value instanceof Number) {
            String decimalStr = formatToDecimalString(value, decimalDigit);
            return Double.valueOf(decimalStr);
        }
        throw new IllegalArgumentException("formatDouble argus must be number.");
    }

    private static String formatToDecimalString(Object value, int decimalDigit) {
        String decimalFormatter = getDecimalFormatString(decimalDigit);
        DecimalFormat df = new DecimalFormat(decimalFormatter);
        return df.format(value);
    }

    public static String getDecimalFormatString(Integer decimalDigit) {
        if (DECIMAL_FORMAT_MAP.containsKey(decimalDigit)) {
            return DECIMAL_FORMAT_MAP.get(decimalDigit);
        } else {
            synchronized (DECIMAL_FORMAT_MAP) {
                if (!DECIMAL_FORMAT_MAP.containsKey(decimalDigit)) {
                    String formatter = decimalFormatterString(decimalDigit);
                    DECIMAL_FORMAT_MAP.put(decimalDigit, formatter);
                    return formatter;
                }
                return DECIMAL_FORMAT_MAP.get(decimalDigit);
            }
        }
    }

    private static String decimalFormatterString(int decimalDigit) {
        StringBuilder s = new StringBuilder("#.");
        for (int i = 0; i < decimalDigit; i++) {
            s.append("#");
        }
        return s.toString();
    }
}
