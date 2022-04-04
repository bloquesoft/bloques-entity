/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-jpa-support
 * FileName:JpaEntityManagerImplTest.java
 * Author:zhangjian
 * date:2022/3/31 下午10:17
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.jpa.impl;

import junit.framework.TestCase;
import io.github.bloquesoft.entity.clazz.definition.ClassEntityDefinition;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.powermock.api.mockito.PowerMockito;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import testData.Application;
import testData.User;
import testData.UserRepository;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class JpaEntityManagerImplTest extends TestCase implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Autowired
    private UserRepository userRepo;

    @Test
    @Transactional
    public void test_1_Add() {
        JpaEntityManagerImpl repo = new JpaEntityManagerImpl(applicationContext);
        repo.add(null, new User(1, "ABCD", "dept1"));
    }

    @Test
    @Transactional
    public void test_2_Add() {
        test_1_Add();
        ClassEntityDefinition mockEd = PowerMockito.mock(ClassEntityDefinition.class);
        PowerMockito.when(mockEd.getClazz()).thenReturn((Class)User.class);
        JpaEntityManagerImpl repo = new JpaEntityManagerImpl(applicationContext);
        Map<String, Object> map = new HashMap<>();
        map.put("id", 2);
        map.put("name", "CDEF");
        map.put("department", "dept1");
        repo.add(mockEd, map);

        Map<String, Object> map1 = new HashMap<>();
        map1.put("id", 3);
        map1.put("name", "WXYZ");
        map1.put("department", "dept2");
        repo.add(mockEd, map1);
    }

    @Test
    @Transactional
    public void test_3_FindById() {
        test_2_Add();
        ClassEntityDefinition mockEd = PowerMockito.mock(ClassEntityDefinition.class);
        PowerMockito.when(mockEd.getClazz()).thenReturn((Class)User.class);
        JpaEntityManagerImpl repo = new JpaEntityManagerImpl(applicationContext);
        User user = (User)repo.findById(mockEd, 1);
        Assert.isTrue(user.getId() == 1);
        Assert.isTrue(user.getName().equals("ABCD"));
        User user1 = (User)repo.findById(mockEd, 4);
        Assert.isNull(user1);
    }

    @Test
    @Transactional
    public void test_4_FindOne() {
        test_3_FindById();
        ClassEntityDefinition mockEd = PowerMockito.mock(ClassEntityDefinition.class);
        PowerMockito.when(mockEd.getClazz()).thenReturn((Class)User.class);
        JpaEntityManagerImpl repo = new JpaEntityManagerImpl(applicationContext);
        LinkedHashMap<String, Object> args = new LinkedHashMap();
        args.put("name", "CDEF");
        User user = (User)repo.findOne(mockEd, args);
    }

    @Test
    @Transactional
    public void test_5_FindList() {
        test_4_FindOne();
        ClassEntityDefinition mockEd = PowerMockito.mock(ClassEntityDefinition.class);
        PowerMockito.when(mockEd.getClazz()).thenReturn((Class)User.class);
        JpaEntityManagerImpl repo = new JpaEntityManagerImpl(applicationContext);
        LinkedHashMap<String, Object> args = new LinkedHashMap();
        args.put("department", "dept1");
        List list = repo.findList(mockEd, args);
        Assert.isTrue(list.size() == 2);
    }

    @Test
    @Transactional
    public void test_6_Update() {

        test_5_FindList();
        ClassEntityDefinition mockEd = PowerMockito.mock(ClassEntityDefinition.class);
        PowerMockito.when(mockEd.getClazz()).thenReturn((Class)User.class);
        JpaEntityManagerImpl repo = new JpaEntityManagerImpl(applicationContext);
        User user = (User)repo.findById(mockEd, 3);
        user.setDepartment("dept1");
        repo.update(mockEd, user);

        LinkedHashMap<String, Object> args = new LinkedHashMap();
        args.put("department", "dept1");
        List list = repo.findList(mockEd, args);
        Assert.isTrue(list.size() == 3);
    }

    @Test
    @Transactional
    public void test_7_Update() {

        test_6_Update();
        ClassEntityDefinition mockEd = PowerMockito.mock(ClassEntityDefinition.class);
        PowerMockito.when(mockEd.getClazz()).thenReturn((Class)User.class);
        JpaEntityManagerImpl repo = new JpaEntityManagerImpl(applicationContext);
        LinkedHashMap<String, Object> args = new LinkedHashMap();
        args.put("id", 3);
        args.put("name", "HIGH");
        args.put("department", "dept2");
        repo.update(mockEd, args);

        LinkedHashMap<String, Object> args1 = new LinkedHashMap();
        args1.put("department", "dept1");
        List list = repo.findList(mockEd, args1);
        Assert.isTrue(list.size() == 2);
    }

    @Test
    @Transactional
    public void test_8_Delete() {
        test_7_Update();
        ClassEntityDefinition mockEd = PowerMockito.mock(ClassEntityDefinition.class);
        PowerMockito.when(mockEd.getClazz()).thenReturn((Class)User.class);
        JpaEntityManagerImpl repo = new JpaEntityManagerImpl(applicationContext);
        repo.deleteById(mockEd, 1);
        Assert.isNull(repo.findById(mockEd, 1));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}