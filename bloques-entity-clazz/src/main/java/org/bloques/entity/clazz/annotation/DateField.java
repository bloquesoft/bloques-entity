/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-clazz
 * FileName:DateField.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.clazz.annotation;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DateField
{
    String name() default "";
    boolean allowEmpty() default true;
    boolean allowModify() default true;
    String defaultValue() default "";
    String dateFormat() default "yyyy-MM-dd";
    String dateTimeFormat() default "yyyy-MM-dd HH:mm:ss";
    boolean isDateTime() default false;
}
