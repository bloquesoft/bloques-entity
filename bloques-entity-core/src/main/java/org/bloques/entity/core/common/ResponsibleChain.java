/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:ResponsibleChain.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.core.common;


public class ResponsibleChain {

    private AbstractResponsibleNode first;

    private AbstractResponsibleNode last;

    public ResponsibleChain(AbstractResponsibleNode... nodes) {

        for (int i = 0; i < nodes.length; i++) {
            this.addNode(nodes[i]);
        }
    }

    public Object start(Object inputParamter) {
        if (this.first != null) {
            AbstractResponsibleNode current = this.first;
            while (current != null) {
                ResponsibleChainResult result = current.exec(inputParamter);
                if (result.getSuccess()) {
                    return result.getResult();
                } else {
                    if (current.getNext() != null) {
                        current = current.getNext();
                    } else {
                        break;
                    }
                }
            }
        }
        return null;
    }

    public void addNode(AbstractResponsibleNode node) {
        if (first == null) {
            first = node;
        }
        if (last == null) {
            last = first;
        } else {
            last.setNext(node);
            last = node;
        }
    }
}