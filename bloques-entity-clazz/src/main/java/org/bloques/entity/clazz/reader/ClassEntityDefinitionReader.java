/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-clazz
 * FileName:ClassEntityDefinitionReader.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.clazz.reader;

import lombok.extern.slf4j.Slf4j;
import org.bloques.entity.clazz.annotation.BEntity;
import org.bloques.entity.clazz.annotation.FieldUniq;
import org.bloques.entity.clazz.annotation.FieldUniqs;
import org.bloques.entity.clazz.definition.ClassEntityDefinition;
import org.bloques.entity.core.definition.EntityDefinition;
import org.bloques.entity.core.definition.FieldUniqDefinition;
import org.bloques.entity.core.definition.PackageDefinition;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.LinkedList;
import java.util.List;

@Slf4j
public class ClassEntityDefinitionReader {
    public ClassEntityDefinition readEntityDefinition(Class<?> clazz) {
        ClassEntityDefinition ced = new ClassEntityDefinition(clazz);
        if (clazz.isAnnotationPresent(BEntity.class)) {
            BEntity annotation = clazz.getAnnotation(BEntity.class);
            String title = annotation.title();
            if (StringUtils.hasLength(title)) {
                ced.setTitle(title);
            }
        }
        return ced;
    }

    public PackageDefinition readPackageDefinition(Class<?> clazz) {
        String name = clazz.getPackage().getName();
        if (clazz.isAnnotationPresent(BEntity.class)) {
            BEntity annotation = clazz.getAnnotation(BEntity.class);
            if (StringUtils.hasLength(annotation.packageName())) {
                name = annotation.packageName();
            }
        }
        return new PackageDefinition(name);
    }

    public List<FieldUniqDefinition> readFieldUniqsDefinition(Class<?> clazz, EntityDefinition ed) {
        Assert.notNull(clazz);
        Assert.notNull(ed);
        clazz.getAnnotations();
        List<FieldUniqDefinition> list = new LinkedList<>();

        List<FieldUniq> uniqList = new LinkedList<>();
        if (clazz.isAnnotationPresent(FieldUniqs.class)) {
            FieldUniqs annotation = clazz.getAnnotation(FieldUniqs.class);
            for (FieldUniq item : annotation.value()) {
                uniqList.add(item);
            }
        }
        if (clazz.isAnnotationPresent(FieldUniq.class)) {
            uniqList.add(clazz.getAnnotation(FieldUniq.class));
        }
        for (FieldUniq fieldUniq : uniqList) {
            if (fieldUniq.fieldIds().length > 0) {
                FieldUniqDefinition uniqDefinition = new FieldUniqDefinition(ed);
                for (String fieldId : fieldUniq.fieldIds()) {
                    uniqDefinition.addFieldId(fieldId);
                }
                list.add(uniqDefinition);
            }
        }
        return list;
    }
}