/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:ResponsibleChainResult.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.core.common;

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