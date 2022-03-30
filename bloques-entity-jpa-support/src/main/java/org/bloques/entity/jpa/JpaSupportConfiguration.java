/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-jpa-support
 * FileName:JpaSupportConfiguration.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.jpa;

import lombok.Getter;

public class JpaSupportConfiguration {

    @Getter
    private String[] japEntityPackages;

    public JpaSupportConfiguration jpaEntityPackages(String[] japEntityPackages){
        this.japEntityPackages = japEntityPackages;
        return this;
    }
}
