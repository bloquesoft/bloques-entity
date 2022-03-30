

/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-jpa-support
 * FileName:JpaEntityRepositoryInitialize.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.jpa;

import org.bloques.entity.core.repository.EntityRepositoryRegister;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class JpaEntityRepositoryInitialize implements ApplicationContextAware {

    @Autowired
    private EntityRepositoryRegister registrar;

    @Autowired
    private JpaSupportConfiguration configuration;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
       JpaEntityRepositoryScan scan = new JpaEntityRepositoryScan(configuration.getJapEntityPackages(), applicationContext);
       scan.doScan(registrar);
    }
}