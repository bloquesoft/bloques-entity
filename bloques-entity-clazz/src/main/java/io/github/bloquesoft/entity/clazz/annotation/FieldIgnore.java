/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-clazz
 * FileName:FieldIgnore.java
 * Author:zhangjian
 * date:2022/3/31 下午10:16
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.clazz.annotation;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FieldIgnore {

}