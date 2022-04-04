
/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-springboot-startar
 * FileName:BeanFactory.java
 * Author:zhangjian
 * date:2022/4/2 下午9:08
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.springboot;

import io.github.bloquesoft.entity.core.executor.EntityExecutor;
import io.github.bloquesoft.entity.core.executor.EntityExecutorImpl;
import io.github.bloquesoft.entity.core.graph.graphql.GraphqlJavaParserImpl;
import io.github.bloquesoft.entity.core.loader.impl.EntityGraphLoaderImpl;
import io.github.bloquesoft.entity.core.register.EntityDefinitionRegister;
import io.github.bloquesoft.entity.core.register.EntityDefinitionRegisterImpl;
import io.github.bloquesoft.entity.core.repository.EntityRepositoryRegister;
import io.github.bloquesoft.entity.core.repository.EntityRepositoryRegisterImpl;

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