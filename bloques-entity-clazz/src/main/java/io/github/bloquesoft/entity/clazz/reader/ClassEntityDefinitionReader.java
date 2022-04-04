/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-clazz
 * FileName:ClassEntityDefinitionReader.java
 * Author:zhangjian
 * date:2022/3/31 下午10:16
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.clazz.reader;

import io.github.bloquesoft.entity.clazz.annotation.BEntity;
import lombok.extern.slf4j.Slf4j;
import io.github.bloquesoft.entity.clazz.annotation.FieldUniq;
import io.github.bloquesoft.entity.clazz.annotation.FieldUniqs;
import io.github.bloquesoft.entity.clazz.definition.ClassEntityDefinition;
import io.github.bloquesoft.entity.core.definition.EntityDefinition;
import io.github.bloquesoft.entity.core.definition.FieldUniqDefinition;
import io.github.bloquesoft.entity.core.definition.PackageDefinition;
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