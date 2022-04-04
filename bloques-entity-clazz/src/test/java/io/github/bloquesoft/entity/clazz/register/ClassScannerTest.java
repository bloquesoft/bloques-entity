/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-clazz
 * FileName:ClassScannerTest.java
 * Author:zhangjian
 * date:2022/3/31 下午10:16
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.clazz.register;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import testData.usercenter.Role;
import testData.usercenter.User;

import java.io.IOException;
import java.util.Set;

class ClassScannerTest {

    @Test
    public void scanUserCenterClass() throws IOException {

        Set<Class> classSet = ClassScanner.scan("testData.usercenter");
        Assertions.assertTrue(classSet.size() == 5);
        Assertions.assertTrue(classSet.contains(User.class));
        Assertions.assertTrue(classSet.contains(Role.class));
    }
}