/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-clazz
 * FileName:User.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package testData.usercenter;

import lombok.Data;
import org.bloques.entity.clazz.annotation.*;

import java.util.Date;
import java.util.List;

@Data
@BEntity(title = "用户", packageName = "usercenter")
public class User
{
   @PrimaryKey
   Long id;

   @BasicField(name="用户编号")
   String code;

   String name;

   @BasicField(name="年龄")
   Integer age;

   @BasicField(name="整数")
   Long number;

   @BasicField(name="工资", decimalDigit = 0)
   Float salary;

   @BasicField(name="存款")
   Double storage;

   @BasicField(name="是否管理员")
   Boolean administrator;

   @DateField(name="出生日期")
   Date birthday;

   @DateField(name="建立时间", isDateTime = true)
   Date createTime;

   @ListField(relationShips = "id:userId")
   List<OrganizationRoleUser> organizationRoleList;
}