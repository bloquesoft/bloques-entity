/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-clazz
 * FileName:OrganizationRole.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package testData.usercenter;

import lombok.Data;
import org.bloques.entity.clazz.annotation.EntityField;
import org.bloques.entity.clazz.annotation.BEntity;
import org.bloques.entity.clazz.annotation.ListField;
import org.bloques.entity.clazz.annotation.PrimaryKey;

import java.util.List;

@Data
@BEntity(title = "组织角色", packageName = "testData/usercenter")
public class OrganizationRole {

    @PrimaryKey
    Long id;

    Long organizationId;

    Long roleId;

    @EntityField(associatedFieldId = "organizationId")
    Organization organization;

    @EntityField(associatedFieldId = "roleId")
    Role role;

    @ListField(relationShips = "id:organizationRoleId")
    List<OrganizationRoleUser> orgRoleUserList;
}
