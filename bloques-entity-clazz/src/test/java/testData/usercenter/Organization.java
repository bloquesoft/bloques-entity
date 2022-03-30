/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-clazz
 * FileName:Organization.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package testData.usercenter;

import lombok.Data;
import org.bloques.entity.clazz.annotation.*;

import java.util.List;

@Data
@BEntity(title = "组织", packageName = "testData/usercenter")
@FieldUniq(fieldIds = {"name"})
public class Organization
{
    @PrimaryKey
    Long id;

    @BasicField(name="名称", allowEmpty = false)
    String name;

    Long parentId;

    @EntityField(name="上级组织", associatedFieldId = "parentId")
    Organization parent;

    @ListField(name="下级组织", relationShips = {"id:parentId"})
    List<Organization> children;

    @ListField(relationShips = {"id:organizationId"})
    List<OrganizationRole> orgRoleList;

    @Override
    public String toString() {
        return "Organization{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentId=" + parentId +
                ", parent=" + parent +
                ", children=" + children +
                ", orgRoleList=" + orgRoleList +
                '}';
    }
}