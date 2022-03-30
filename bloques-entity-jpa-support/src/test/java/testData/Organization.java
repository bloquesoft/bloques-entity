/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-jpa-support
 * FileName:Organization.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package testData;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Organization {

    @Id
    private Integer id;

    private String name;

    private String department;

    public Organization(Integer id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }

    public Organization() {
    }
}