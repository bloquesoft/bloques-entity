/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-springboot-startar
 * FileName:BEntityInitialize.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.springboot;

import lombok.extern.slf4j.Slf4j;
import org.bloques.entity.clazz.executor.ClassEntityExecutor;
import org.bloques.entity.clazz.executor.ClassEntityExecutorImpl;
import org.bloques.entity.clazz.object.ClsEntityObjectFactory;
import org.bloques.entity.clazz.register.ClassEntityDefinitionRegistrarImpl;
import org.bloques.entity.clazz.register.ClassEntityDefinitionScanner;
import org.bloques.entity.core.error.template.LogicErrorTemplate;
import org.bloques.entity.core.executor.EntityExecutor;
import org.bloques.entity.core.executor.EntityExecutorImpl;
import org.bloques.entity.core.object.EntityObjectCreator;
import org.bloques.entity.core.object.EntityObjectFactory;
import org.bloques.entity.core.object.MapEntityObjectFactory;
import org.bloques.entity.core.register.EntityDefinitionRegister;
import org.bloques.entity.core.register.EntityDefinitionRegisterImpl;
import org.bloques.entity.core.repository.EntityRepositoryRegister;
import org.bloques.entity.core.repository.EntityRepositoryRegisterImpl;
import org.bloques.entity.springboot.annotation.EnableBEntity;
import org.bloques.entity.springboot.api.ApiController;
import org.bloques.entity.springboot.api.ControllerExceptionAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.Assert;

import java.util.Map;

@Slf4j
public class BEntityInitialize implements ImportBeanDefinitionRegistrar {

    @Autowired
    BEntityConfiguration bEntityConfiguration;

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

        Map<String, Object> settingMap = importingClassMetadata.getAnnotationAttributes(EnableBEntity.class.getName());
        registerBean(registry);
        enableApi(settingMap, registry);
        classEntityDefinitionScan(importingClassMetadata);
        initializeLogicErrorTemplate(settingMap);
        registerEntityObjectFactory();
    }

    private void initializeLogicErrorTemplate(Map<String, Object> settingMap) {
        if (bEntityConfiguration != null && bEntityConfiguration.getLogicErrorTemplateConfiguration() != null) {
            LogicErrorTemplate.getSingleton().input(bEntityConfiguration.getLogicErrorTemplateConfiguration());
        } else {
            LogicErrorTemplate.getSingleton().input(LogicErrorTemplate.DEFAULT);
        }
    }

    private void enableApi(Map<String, Object> settingMap, BeanDefinitionRegistry registr) {
        if (settingMap.containsKey("enableApi")) {
            if ((Boolean) settingMap.get("enableApi")) {
                register(registr, ApiController.class, ApiController.class.getSimpleName(), null, null);
                register(registr, ControllerExceptionAdvice.class, ControllerExceptionAdvice.class.getSimpleName(), null, null);
            }
        }
    }

    private void registerBean(BeanDefinitionRegistry registry) {
        String beanFactoryBeanName = BeanFactory.class.getName();
        register(registry, BeanFactory.class, beanFactoryBeanName, null, null);
        log.info("register BeanFactory bean complete");
        register(registry, EntityDefinitionRegisterImpl.class, EntityDefinitionRegister.class.getName(), beanFactoryBeanName, "getEntityDefinitionRegisterBean");
        log.info("register EntityDefinitionRegister bean complete");
        register(registry, EntityRepositoryRegisterImpl.class, EntityRepositoryRegister.class.getName(), beanFactoryBeanName, "getEntityRepositoryRegisterBean");
        log.info("register EntityRepositoryRegister bean complete");
        register(registry, EntityExecutorImpl.class, EntityExecutor.class.getName(), beanFactoryBeanName, "getEntityExecutorBean");
        log.info("register EntityExecutor bean complete");
        registerClassBeanDefinition(registry);
    }

    private void register(BeanDefinitionRegistry registry, Class<?> beanClass, String beanName, String factoryBeanName, String factoryMethodName) {
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(beanClass);
        if (factoryBeanName != null) {
            beanDefinition.setFactoryBeanName(factoryBeanName);
        }
        if (factoryMethodName != null) {
            beanDefinition.setFactoryMethodName(factoryMethodName);
        }
        registry.registerBeanDefinition(beanName, beanDefinition);
    }

    private void registerClassBeanDefinition(BeanDefinitionRegistry registry) {
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(ClassEntityExecutorImpl.class);
        beanDefinition.getConstructorArgumentValues().addIndexedArgumentValue(0, BeanFactory.singleton().getEntityExecutorBean());
        registry.registerBeanDefinition(ClassEntityExecutor.class.getName(), beanDefinition);
        log.info("register ClassEntityExecutor bean complete");
    }

    private void classEntityDefinitionScan(AnnotationMetadata importingClassMetadata) {
        Map<String, Object> settingMap = importingClassMetadata.getAnnotationAttributes(EnableBEntity.class.getName());
        if (settingMap != null && settingMap.containsKey("classEntityDefinitionPackages")) {
            Object packagesObj = settingMap.get("classEntityDefinitionPackages");
            Assert.isTrue(packagesObj instanceof String[], "classEntityDefinitionPackages must be String[]");
            String[] packages = (String[]) packagesObj;
            if (packages.length > 0) {
                ClassEntityDefinitionScanner scanner = new ClassEntityDefinitionScanner(packages);
                ClassEntityDefinitionRegistrarImpl classRegister = new ClassEntityDefinitionRegistrarImpl(BeanFactory.singleton().getEntityDefinitionRegisterBean());
                scanner.doScan(classRegister);
            }
        }
    }

    public void registerEntityObjectFactory() {
        if (bEntityConfiguration != null) {
            for (EntityObjectFactory factory : bEntityConfiguration.getEntityObjectFactoryList()) {
                EntityObjectCreator.getSingleton().register(factory);
            }
        }
        EntityObjectCreator.getSingleton().register(new MapEntityObjectFactory());
        EntityObjectCreator.getSingleton().register(new ClsEntityObjectFactory());
    }
}