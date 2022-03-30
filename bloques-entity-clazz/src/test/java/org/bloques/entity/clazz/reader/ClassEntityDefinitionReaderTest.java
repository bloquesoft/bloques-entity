/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-clazz
 * FileName:ClassEntityDefinitionReaderTest.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.clazz.reader;

import org.bloques.entity.core.definition.EntityDefinition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import testData.usercenter.Role;
import testData.usercenter.User;

class ClassEntityDefinitionReaderTest {

    @Test
    public void readUserTest()
    {
        ClassEntityDefinitionReader reader = new ClassEntityDefinitionReader();
        EntityDefinition edUser = reader.readEntityDefinition(User.class);
        Assertions.assertNotNull(edUser);
        Assertions.assertEquals("testData.usercenter.User", edUser.getId());
        Assertions.assertEquals("用户", edUser.getTitle());

        EntityDefinition edRole = reader.readEntityDefinition(Role.class);
        Assertions.assertNotNull(edRole);
        Assertions.assertEquals("testData.usercenter.Role", edRole.getId());
        Assertions.assertEquals("Role", edRole.getName());
    }
}