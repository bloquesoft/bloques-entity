/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:GraphqlJavaParserImplTest.java
 * Author:zhangjian
 * date:2022/3/31 下午10:15
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.core.graph.graphql;

import junit.framework.TestCase;
import io.github.bloquesoft.entity.core.graph.GraphParser;
import io.github.bloquesoft.entity.core.graph.GraphRoot;
import org.junit.Test;

public class GraphqlJavaParserImplTest extends TestCase {

    @Test
    public void testParse() {
        GraphParser gp = new GraphqlJavaParserImpl();
        GraphRoot root = gp.parse("{:parent{parent,children}, :children{parent,children}}");
    }
}