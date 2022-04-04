/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:LogicException.java
 * Author:zhangjian
 * date:2022/3/31 下午10:15
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.core.logic.error;

import io.github.bloquesoft.entity.core.error.AbstractBEntityException;

public class LogicException extends AbstractBEntityException {
    public LogicException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }
}