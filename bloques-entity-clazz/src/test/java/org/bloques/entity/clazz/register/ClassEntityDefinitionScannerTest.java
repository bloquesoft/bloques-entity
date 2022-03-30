/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-clazz
 * FileName:ClassEntityDefinitionScannerTest.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.clazz.register;

import org.bloques.entity.clazz.definition.ClassEntityDefinition;
import org.bloques.entity.core.register.EntityDefinitionRegister;
import org.bloques.entity.core.register.EntityDefinitionRegisterImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
class ClassEntityDefinitionScannerTest {

    @Test
    public void scanUserCenter() {

        String[] packages = new String[] {"testData.usercenter"};
        EntityDefinitionRegister entityRegister =  new EntityDefinitionRegisterImpl();
        ClassEntityDefinitionRegister classRegistrar = new ClassEntityDefinitionRegistrarImpl(entityRegister);
        ClassEntityDefinitionScanner scanner = new ClassEntityDefinitionScanner(packages);
        scanner.doScan(classRegistrar);
        Assertions.assertNotNull(classRegistrar.getEntityDefinition("testData.usercenter.User"));
        Assertions.assertNotNull(classRegistrar.getEntityDefinition("usercenter", "User"));
        ClassEntityDefinition ced = classRegistrar.getClassEntityDefinition(testData.usercenter.User.class);
        Assertions.assertTrue(ced.getClazz().equals(testData.usercenter.User.class));
        System.out.println("success");
    }

}