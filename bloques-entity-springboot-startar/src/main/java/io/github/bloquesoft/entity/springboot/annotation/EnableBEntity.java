/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-springboot-startar
 * FileName:EnableBEntity.java
 * Author:zhangjian
 * date:2022/4/2 下午9:08
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.springboot.annotation;

import io.github.bloquesoft.entity.springboot.BEntityInitialize;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({BEntityInitialize.class})
public @interface EnableBEntity {
    String[] classEntityDefinitionPackages() default {};
    boolean enableExecuteApi() default false;
    boolean enableDefinitionApi() default false;
}