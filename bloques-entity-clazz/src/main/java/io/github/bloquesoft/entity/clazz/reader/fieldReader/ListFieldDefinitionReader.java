/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-clazz
 * FileName:ListFieldDefinitionReader.java
 * Author:zhangjian
 * date:2022/3/31 下午10:16
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.clazz.reader.fieldReader;

import io.github.bloquesoft.entity.clazz.reader.DefinitionReader;
import lombok.extern.slf4j.Slf4j;
import io.github.bloquesoft.entity.clazz.annotation.ListField;
import io.github.bloquesoft.entity.core.common.AbstractResponsibleNode;
import io.github.bloquesoft.entity.core.common.ResponsibleChainResult;
import io.github.bloquesoft.entity.core.definition.AbstractFieldDefinition;
import io.github.bloquesoft.entity.core.definition.ListFieldDefinition;
import io.github.bloquesoft.entity.core.definition.ListRelationShip;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ListFieldDefinitionReader extends AbstractResponsibleNode implements DefinitionReader<Field, ListFieldDefinition> {

    @Override
    public ResponsibleChainResult exec(Object parameter) {
        if (parameter instanceof Field) {
            Field field = (Field) parameter;
            AbstractFieldDefinition ed = this.read(field);
            Boolean success = ed != null;
            return new ResponsibleChainResult(success, ed);
        }
        return new ResponsibleChainResult(false, null);
    }

    @Override
    public ListFieldDefinition read(Field field) {
        if (field.isAnnotationPresent(ListField.class)) {
            ListField annotation = field.getAnnotation(ListField.class);
            if (annotation.relationShips().length == 0) {
                log.error("Field " + field.getName() + " must set relationShips for FieldDefinition");
            }
            String id = field.getName();
            String name = field.getName();
            if (StringUtils.hasLength(annotation.name())) {
                name = annotation.name();
            }
            List<ListRelationShip> relationShipList = new ArrayList<>();
            String[] relationShipsArray = annotation.relationShips();
            for (String strRelationShip : relationShipsArray) {
                String[] fields = strRelationShip.split(":");
                if (fields.length != 2) {
                    throw new IllegalArgumentException("BEntity RelationShip format must be XXX:XXX, now relationShip:" + strRelationShip);
                }
                ListRelationShip relation = new ListRelationShip(fields[0], fields[1]);
                relationShipList.add(relation);
            }
            ListFieldDefinition fd = new ListFieldDefinition(id, name, relationShipList);
            fd.setAllowEmpty(annotation.allowEmpty());
            fd.setAllowModify(annotation.allowModify());
            return fd;
        }
        return null;
    }
}
