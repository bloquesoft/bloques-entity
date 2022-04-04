/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-springboot-startar
 * FileName:BEntityInitializeTest.java
 * Author:zhangjian
 * date:2022/4/2 下午9:08
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.springboot;

import junit.framework.TestCase;
import io.github.bloquesoft.entity.clazz.executor.ClassEntityExecutor;
import io.github.bloquesoft.entity.core.executor.EntityExecutor;
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