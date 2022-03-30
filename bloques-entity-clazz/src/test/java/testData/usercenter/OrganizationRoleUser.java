/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-clazz
 * FileName:OrganizationRoleUser.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package testData.usercenter;


import org.bloques.entity.clazz.annotation.EntityField;
import org.bloques.entity.clazz.annotation.BEntity;
import org.bloques.entity.clazz.annotation.PrimaryKey;

@BEntity(title = "组织角色用户", packageName = "testData/usercenter")
public class OrganizationRoleUser
{
    @PrimaryKey
    Long id;

    Long organizationRoleId;

    @EntityField(associatedFieldId = "organizationRoleId")
    OrganizationRole organizationRole;

    String userId;

    @EntityField(associatedFieldId = "userId")
    User user;
}