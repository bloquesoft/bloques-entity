/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-springboot-startar
 * FileName:User.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package testData;

import org.bloques.entity.clazz.annotation.BEntity;
import org.bloques.entity.clazz.annotation.PrimaryKey;

@BEntity(packageName = "UserCenter")
public class User
{
    @PrimaryKey
    Long id;
}