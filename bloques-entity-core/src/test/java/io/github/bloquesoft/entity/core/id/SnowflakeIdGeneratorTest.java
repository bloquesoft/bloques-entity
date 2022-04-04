/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:SnowflakeIdGeneratorTest.java
 * Author:zhangjian
 * date:2022/3/31 下午10:15
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.core.id;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.Date;

public class SnowflakeIdGeneratorTest extends TestCase {

    @Test
    public void testGenerate() {
        SnowflakeIdGenerator generator = new SnowflakeIdGenerator(0,1);
        System.out.println(new Date());
        for(int i=0; i<2000000; i++){
            generator.generate();
        }
        System.out.println(new Date());
    }
}