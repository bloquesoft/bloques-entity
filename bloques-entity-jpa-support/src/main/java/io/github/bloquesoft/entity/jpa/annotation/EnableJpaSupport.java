/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-jpa-support
 * FileName:EnableJpaSupport.java
 * Author:zhangjian
 * date:2022/3/31 下午10:17
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.jpa.annotation;

import io.github.bloquesoft.entity.jpa.JpaEntityRepositoryInitialize;
import io.github.bloquesoft.entity.jpa.impl.JpaEntityManagerImpl;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({JpaEntityRepositoryInitialize.class, JpaEntityManagerImpl.class})
public @interface EnableJpaSupport {

}