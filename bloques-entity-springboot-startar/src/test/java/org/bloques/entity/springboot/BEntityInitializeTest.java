/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-springboot-startar
 * FileName:BEntityInitializeTest.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.springboot;

import junit.framework.TestCase;
import org.bloques.entity.clazz.executor.ClassEntityExecutor;
import org.bloques.entity.core.executor.EntityExecutor;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class BEntityInitializeTest extends TestCase {

    @Autowired
    private EntityExecutor executor;

    @Autowired
    private ClassEntityExecutor classEntityExecutor;

    @Test
    public void test(){
        Assertions.assertNotNull(executor);
        Assertions.assertNotNull(classEntityExecutor);
    }
}