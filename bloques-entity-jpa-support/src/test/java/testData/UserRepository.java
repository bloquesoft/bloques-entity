/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-jpa-support
 * FileName:UserRepository.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package testData;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

        User findByName(String name);

        List<User> findByDepartment(String dept);
}