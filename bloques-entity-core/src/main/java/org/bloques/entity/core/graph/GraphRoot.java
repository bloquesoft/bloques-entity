/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:GraphRoot.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.core.graph;

import lombok.Data;

import java.util.List;

@Data
public class GraphRoot
{
    List<GraphField> children;
}