/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:AbstractJEntityException.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.core.error;

import lombok.Getter;

public abstract class AbstractJEntityException extends RuntimeException {

    @Getter
    private final String errorCode;

    @Getter
    private final String errorMessage;

    public AbstractJEntityException(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}