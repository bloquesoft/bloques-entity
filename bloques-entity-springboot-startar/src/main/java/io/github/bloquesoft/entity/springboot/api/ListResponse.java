/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-springboot-startar
 * FileName:ListResponse.java
 * Author:zhangjian
 * date:2022/4/2 下午9:08
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.springboot.api;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ListResponse<T> extends BaseApiResponse {

    private List<T> list;

    public ListResponse() {
        super();
    }

    public ListResponse(List<T> list) {
        this.list = list;
    }
}