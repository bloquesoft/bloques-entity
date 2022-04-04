/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-clazz
 * FileName:BasicField.java
 * Author:zhangjian
 * date:2022/3/31 下午10:16
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.clazz.annotation;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BasicField
{
    String name() default "";
    boolean allowEmpty() default true;
    boolean allowModify() default true;
    int minLength() default 1;
    int maxLength() default 50;
    String defaultValue() default "";
    int maxInteger() default Integer.MAX_VALUE;
    int minInteger() default Integer.MIN_VALUE;
    long maxLong() default Long.MAX_VALUE;
    long minLong() default Long.MIN_VALUE;
    float maxFloat() default Float.MAX_VALUE;
    float minFloat() default Float.MIN_VALUE;
    double maxDouble() default Double.MAX_VALUE;
    double minDouble() default Double.MIN_VALUE;
    int decimalDigit() default 2;
}