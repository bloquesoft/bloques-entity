/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:GraphqlJavaParserImpl.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.core.graph.graphql;

import graphql.language.*;
import org.bloques.entity.core.graph.GraphField;
import org.bloques.entity.core.graph.GraphParser;
import org.bloques.entity.core.graph.GraphRoot;

import java.util.ArrayList;
import java.util.List;

public class GraphqlJavaParserImpl implements GraphParser {

    private static final String RECURSIVE_PREFIX_EXPRESSION = "__________recursive__________";

    private static final String RECURSIVE_CHAR = ":";

    @Override
    public GraphRoot parse(String loadedGraph) {
        String escapedGraph = loadedGraph.replaceAll(RECURSIVE_CHAR, RECURSIVE_PREFIX_EXPRESSION);
        Document doc = graphql.parser.Parser.parse(escapedGraph);
        if (doc.getDefinitions().size() > 0) {
            OperationDefinition definition = (OperationDefinition) doc.getDefinitions().get(0);
            return readRoot(definition);
        }
        return null;
    }


    private List<GraphField> readChildren(SelectionSet selectionSet) {
        List<GraphField> list = new ArrayList<>();
        if (selectionSet != null) {
            for (Node<?> node : selectionSet.getSelections()) {
                GraphField loadedField = readField((Field) node);
                list.add(loadedField);
            }
        }
        return list;
    }

    private GraphField readField(Field field) {
        GraphField loadedField = createField(field);
        List<GraphField> children = readChildren(field.getSelectionSet());
        loadedField.setChildren(children);
        return loadedField;
    }

    private GraphField createField(Field field) {
        if (field.getName().startsWith(RECURSIVE_PREFIX_EXPRESSION)) {
            String fieldName = field.getName().substring(RECURSIVE_PREFIX_EXPRESSION.length());
            return new GraphField(fieldName, true);
        } else {
            return new GraphField(field.getName(), false);
        }
    }

    private GraphRoot readRoot(OperationDefinition definition) {
        GraphRoot root = new GraphRoot();
        List<GraphField> children = readChildren(definition.getSelectionSet());
        root.setChildren(children);
        return root;
    }
}