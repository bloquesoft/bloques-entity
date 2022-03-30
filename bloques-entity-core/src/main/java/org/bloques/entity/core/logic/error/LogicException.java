/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:LogicException.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.core.logic.error;

import org.bloques.entity.core.error.AbstractJEntityException;

public class LogicException extends AbstractJEntityException {
    public LogicException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }
}