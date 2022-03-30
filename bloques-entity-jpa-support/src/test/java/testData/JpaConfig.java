/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-jpa-support
 * FileName:JpaConfig.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package testData;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        transactionManagerRef = "jpaTransaction",
        basePackages = {"testData" })
public class JpaConfig {

    @Primary
    @Bean("MySqlDataSource")
    public DataSource mysqlDataSource(@Qualifier("MySqlDataSourceProperties") DataSourceProperties properties) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername(properties.getUsername());
        dataSource.setPassword(properties.getPassword());
        dataSource.setUrl(properties.getUrl());
        dataSource.setDriverClassName(properties.getDriverClassName());
        return dataSource;
    }

    @Primary
    @Bean("MySqlDataSourceProperties")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSourceProperties mysqlDataSourceProperties()
    {
        DataSourceProperties dsp = new DataSourceProperties();
        return dsp;
    }

    @Autowired
    private DataSource datasource;

    @Autowired
    private JpaProperties jpaProperties;

    private Map<String, String> getVendorProperties() {
        return jpaProperties.getProperties();
    }

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean mysqlJpaEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder.dataSource(datasource).packages("testData")
                .properties(getVendorProperties())
                .persistenceUnit("mysqlJpaPersistenceUnit").build();
    }

    @Bean(name = "jpaTransaction")
    public PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(mysqlJpaEntityManagerFactory(builder).getObject());
    }
}