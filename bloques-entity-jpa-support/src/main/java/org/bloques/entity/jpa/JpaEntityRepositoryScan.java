/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-jpa-support
 * FileName:JpaEntityRepositoryScan.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.jpa;

import lombok.extern.slf4j.Slf4j;
import org.bloques.entity.clazz.register.ClassScanner;
import org.bloques.entity.core.repository.EntityRepository;
import org.bloques.entity.core.repository.EntityRepositoryRegister;
import org.bloques.entity.core.repository.EntityRepositoryScan;
import org.bloques.entity.jpa.impl.JpaEntityRepositoryImpl;
import org.springframework.context.ApplicationContext;

import javax.persistence.Entity;
import java.util.Set;

@Slf4j
class JpaEntityRepositoryScan implements EntityRepositoryScan {

    private final String[] jpaEntityPackages;

    private final ApplicationContext applicationContext;

    public JpaEntityRepositoryScan(String[] japEntityPackages, ApplicationContext applicationContext) {
        this.jpaEntityPackages = japEntityPackages;
        this.applicationContext = applicationContext;
    }

    @Override
    public void doScan(EntityRepositoryRegister register) {
        if (this.jpaEntityPackages != null) {
            scanForJpaEntity(register);
        }
    }

    private void scanForJpaEntity(EntityRepositoryRegister register) {
        Set<Class> classSet = ClassScanner.scan(this.jpaEntityPackages);
        for (Class clazz : classSet) {
            if (clazz.isAnnotationPresent(Entity.class)) {
                EntityRepository er = new JpaEntityRepositoryImpl(this.applicationContext);
                register.register(clazz.getName(), er);
            }
        }
    }
}