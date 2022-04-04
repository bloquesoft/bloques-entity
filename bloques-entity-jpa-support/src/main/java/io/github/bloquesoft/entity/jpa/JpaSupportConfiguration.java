/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-jpa-support
 * FileName:JpaSupportConfiguration.java
 * Author:zhangjian
 * date:2022/3/31 下午10:17
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.jpa;

import lombok.Getter;

public class JpaSupportConfiguration {

    @Getter
    private String[] japEntityPackages;

    public JpaSupportConfiguration jpaEntityPackages(String[] japEntityPackages){
        this.japEntityPackages = japEntityPackages;
        return this;
    }
}
