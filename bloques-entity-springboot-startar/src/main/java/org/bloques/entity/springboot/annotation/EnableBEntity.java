/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-springboot-startar
 * FileName:EnableBEntity.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.springboot.annotation;

import org.bloques.entity.springboot.BEntityInitialize;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({BEntityInitialize.class})
public @interface EnableBEntity {
    String[] classEntityDefinitionPackages() default {};
    boolean enableApi() default false;
}