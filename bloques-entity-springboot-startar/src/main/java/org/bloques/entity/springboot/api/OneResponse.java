/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-springboot-startar
 * FileName:OneResponse.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.springboot.api;

import lombok.Data;

@Data
public class OneResponse extends BaseApiResponse
{
    private Object data;

    public OneResponse(Object data){
        this.data = data;
    }
}