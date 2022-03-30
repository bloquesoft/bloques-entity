/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-springboot-startar
 * FileName:ListResponse.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.springboot.api;

import lombok.Data;

import java.util.List;

@Data
public class ListResponse extends BaseApiResponse {

    private List<Object> list;

    public ListResponse(){
        super();
    }

    public ListResponse(List<Object> list){
        this.list = list;
    }
}