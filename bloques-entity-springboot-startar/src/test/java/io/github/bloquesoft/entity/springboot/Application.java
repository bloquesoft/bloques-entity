/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-springboot-startar
 * FileName:Application.java
 * Author:zhangjian
 * date:2022/4/2 下午9:08
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class Application extends SpringBootServletInitializer {

    public static void main(final String[] args) {

        SpringApplication.run(Application.class, args);
    }
}