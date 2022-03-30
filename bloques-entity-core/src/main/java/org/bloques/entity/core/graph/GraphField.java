/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:GraphField.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.core.graph;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class GraphField {

    @Getter
    private final String fieldName;

    @Getter
    @Setter
    private boolean recursiveLoaded;

    @Setter
    @Getter
    private List<GraphField> children;

    public GraphField(String fieldName, boolean recursiveLoaded)
    {
        this.fieldName = fieldName;
        this.recursiveLoaded = recursiveLoaded;
    }

    @Override
    public String toString() {
        return "GraphField{" +
                "fieldName='" + fieldName + '\'' +
                ", recursiveLoaded=" + recursiveLoaded +
                ", children=" + children +
                '}';
    }
}
