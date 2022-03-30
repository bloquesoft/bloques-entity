/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:AbstractResponsibleNode.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.core.common;

import lombok.Getter;
import lombok.Setter;

public abstract class AbstractResponsibleNode
{
    @Getter
    @Setter
    private AbstractResponsibleNode next;

    public abstract ResponsibleChainResult exec(Object parameter);
}