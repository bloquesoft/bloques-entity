/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-springboot-startar
 * FileName:BEntityConfig.java
 * Author:zhangjian
 * date:2022/4/2 下午9:08
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.springboot;

import io.github.bloquesoft.entity.springboot.annotation.EnableBEntity;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBEntity(classEntityDefinitionPackages = "testData")
public class BEntityConfig {

}