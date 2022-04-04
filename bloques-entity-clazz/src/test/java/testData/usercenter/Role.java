/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-clazz
 * FileName:Role.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package testData.usercenter;

import io.github.bloquesoft.entity.clazz.annotation.BEntity;
import io.github.bloquesoft.entity.clazz.annotation.ListField;
import io.github.bloquesoft.entity.clazz.annotation.PrimaryKey;

import java.util.List;

@BEntity(title = "角色", packageName = "testData/usercenter")
public class Role {

    @PrimaryKey
    Long id;

    String name;

    @ListField(relationShips = {"id:roleId"})
    List<OrganizationRole> orgRoleList;
}
