/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-jpa-support
 * FileName:EnableJpaSupport.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.jpa.annotation;

import org.bloques.entity.jpa.JpaEntityRepositoryInitialize;
import org.bloques.entity.jpa.impl.JpaEntityManagerImpl;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({JpaEntityRepositoryInitialize.class, JpaEntityManagerImpl.class})
public @interface EnableJpaSupport {

}