/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:NumberUtilTest.java
 * Author:zhangjian
 * date:2022/3/31 下午10:15
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.core.common;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class NumberUtilTest {

    @Test
    public void testFormatToFloat() {

        Float result1 = NumberUtil.formatFloat(0.21723f, 4);
        Assertions.assertTrue(result1.equals(0.2172f));

        Float result2 = NumberUtil.formatFloat(0.217f, 2);
        Assertions.assertTrue(result2.equals(0.22f));

        Float result3 = NumberUtil.formatFloat(0.217f, 5);
        Assertions.assertTrue(result3.equals(0.217f));
    }

    @Test
    public void testFormatToDouble() {
        Double result1 = NumberUtil.formatDouble(0.21723d, 4);
        Assertions.assertTrue(result1.equals(0.2172d));

        Double result2 = NumberUtil.formatDouble(0.217d, 2);
        Assertions.assertTrue(result2.equals(0.22d));

        Double result3 = NumberUtil.formatDouble(0.217d, 5);
        Assertions.assertTrue(result3.equals(0.217d));
    }

    @Test
    public void testGetDecimalFormatString() {
        String result2 = NumberUtil.getDecimalFormatString(2);
        Assertions.assertTrue("#.##".equals(result2));
        String result5 = NumberUtil.getDecimalFormatString(5);
        Assertions.assertTrue("#.#####".equals(result5));
    }

}