/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:AbstractBEntityException.java
 * Author:zhangjian
 * date:2022/3/31 下午10:15
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.core.error;

import lombok.Getter;

public abstract class AbstractBEntityException extends RuntimeException {

    @Getter
    private final String errorCode;

    @Getter
    private final String errorMessage;

    public AbstractBEntityException(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}