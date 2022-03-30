
/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-springboot-startar
 * FileName:BeanFactory.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.springboot;

import org.bloques.entity.core.executor.EntityExecutor;
import org.bloques.entity.core.executor.EntityExecutorImpl;
import org.bloques.entity.core.graph.graphql.GraphqlJavaParserImpl;
import org.bloques.entity.core.loader.impl.EntityGraphLoaderImpl;
import org.bloques.entity.core.register.EntityDefinitionRegister;
import org.bloques.entity.core.register.EntityDefinitionRegisterImpl;
import org.bloques.entity.core.repository.EntityRepositoryRegister;
import org.bloques.entity.core.repository.EntityRepositoryRegisterImpl;

class BeanFactory {

    private static final BeanFactory BEAN_FACTORY = new BeanFactory();

    private static EntityExecutor ENTITY_EXECUTOR;

    private static final EntityDefinitionRegister ENTITY_DEFINITION_REGISTER_BEAN = new EntityDefinitionRegisterImpl();

    private static final EntityRepositoryRegister ENTITY_REPOSITORY_REGISTER_BEAN = new EntityRepositoryRegisterImpl();

    public static BeanFactory singleton() {
        return BEAN_FACTORY;
    }

    public EntityDefinitionRegister getEntityDefinitionRegisterBean(){
        return ENTITY_DEFINITION_REGISTER_BEAN;
    }

    public EntityRepositoryRegister getEntityRepositoryRegisterBean(){
        return ENTITY_REPOSITORY_REGISTER_BEAN;
    }

    public EntityExecutor getEntityExecutorBean() {

        if (ENTITY_EXECUTOR == null) {
            synchronized (EntityExecutor.class) {
                if (ENTITY_EXECUTOR == null) {
                    EntityExecutorImpl impl = new EntityExecutorImpl();
                    impl.setEntityDefinitionFactory(this.getEntityDefinitionRegisterBean());
                    impl.setEntityRepositoryFactory(this.getEntityRepositoryRegisterBean());
                    impl.setGraphLoader(new EntityGraphLoaderImpl(this.getEntityRepositoryRegisterBean(), new GraphqlJavaParserImpl()));
                    ENTITY_EXECUTOR = impl;
                }
            }

        }
        return ENTITY_EXECUTOR;
    }
}