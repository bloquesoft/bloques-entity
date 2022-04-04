/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:ResponsibleChainResult.java
 * Author:zhangjian
 * date:2022/3/31 下午10:15
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.core.common;

import lombok.Getter;

public class ResponsibleChainResult {

    @Getter
    private final Boolean success;

    @Getter
    private final Object result;

    public ResponsibleChainResult(Boolean success, Object result){
        this.success = success;
        this.result = result;
    }
}