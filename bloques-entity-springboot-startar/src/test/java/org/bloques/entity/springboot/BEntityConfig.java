/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-springboot-startar
 * FileName:BEntityConfig.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.springboot;

import org.bloques.entity.springboot.annotation.EnableBEntity;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBEntity(classEntityDefinitionPackages = "testData")
public class BEntityConfig {

}