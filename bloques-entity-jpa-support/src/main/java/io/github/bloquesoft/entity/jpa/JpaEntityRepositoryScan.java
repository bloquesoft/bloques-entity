/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-jpa-support
 * FileName:JpaEntityRepositoryScan.java
 * Author:zhangjian
 * date:2022/3/31 下午10:17
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.jpa;

import io.github.bloquesoft.entity.jpa.impl.JpaEntityRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import io.github.bloquesoft.entity.clazz.register.ClassScanner;
import io.github.bloquesoft.entity.core.repository.EntityRepository;
import io.github.bloquesoft.entity.core.repository.EntityRepositoryRegister;
import io.github.bloquesoft.entity.core.repository.EntityRepositoryScan;
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