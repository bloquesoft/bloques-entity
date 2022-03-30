/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-springboot-startar
 * FileName:FindListRequest.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.springboot.api;

import lombok.Data;

import java.util.LinkedHashMap;

@Data
public class FindListRequest {

    private LinkedHashMap<String, Object> match;

    private String graph;
}
