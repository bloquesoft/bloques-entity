/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-springboot-startar
 * FileName:User.java
 * Author:zhangjian
 * date:2022/4/4 下午9:59
 * Version:0.0.1
 */

package testData.entity;

import io.github.bloquesoft.entity.clazz.annotation.BEntity;
import io.github.bloquesoft.entity.clazz.annotation.PrimaryKey;

@BEntity(packageName = "UserCenter")
public class User
{
    @PrimaryKey
    Long id;
}