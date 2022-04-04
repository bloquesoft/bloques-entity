/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-springboot-startar
 * FileName:BEntityConfig.java
 * Author:zhangjian
 * date:2022/4/4 下午9:57
 * Version:0.0.1
 */

package testData;

import io.github.bloquesoft.entity.springboot.annotation.EnableBEntity;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBEntity(classEntityDefinitionPackages = "testData.entity", enableDefinitionApi = true)
public class BEntityConfig {

}