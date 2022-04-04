/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-clazz
 * FileName:ClassEntityDefinitionScanner.java
 * Author:zhangjian
 * date:2022/3/31 下午10:16
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.clazz.register;

import io.github.bloquesoft.entity.clazz.annotation.FieldIgnore;
import io.github.bloquesoft.entity.clazz.annotation.PrimaryKey;
import io.github.bloquesoft.entity.clazz.definition.ClassEntityDefinition;
import io.github.bloquesoft.entity.clazz.reader.ClassEntityDefinitionReader;
import io.github.bloquesoft.entity.clazz.reader.fieldReader.*;
import io.github.bloquesoft.entity.core.common.ResponsibleChain;
import io.github.bloquesoft.entity.core.definition.*;
import io.github.bloquesoft.entity.core.definition.basicValue.AbstractBasicValueFieldDefinition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
public class ClassEntityDefinitionScanner {

    private final Map<Class<?>, ClassEntityDefinition> registering = new HashMap<>();

    private static final ClassEntityDefinitionReader classReader = new ClassEntityDefinitionReader();

    private final String[] backPackages;

    public void doScan(ClassEntityDefinitionRegister classRegister) {

        log.info("BEntity class entity scan started.");
        scan(classRegister);
        log.info("BEntity class entity scan end.");
    }

    public ClassEntityDefinitionScanner(@Nullable String[] backPackages) {
        this.backPackages = backPackages;
    }

    private void scan(ClassEntityDefinitionRegister registrar) {
        if (this.backPackages.length == 0) {
            log.error("BEntity class scan error, backPackages is empty");
            return;
        }
        Set<Class> classSet = ClassScanner.scan(this.backPackages);
        for (Class<?> clazz : classSet) {
            scanClass(clazz, registrar);
        }
    }

    private void scanClass(Class<?> clazz, ClassEntityDefinitionRegister registrar) {
        if (clazz.isAnnotation() || clazz.isInterface()) {
            return;
        }
        String edId = readEntityDefinitionId(clazz);
        if (registrar.contain(edId)) {
            log.error("Entity definition id is registed, could not repeate to registe. id:" + edId);
            return;
        }
        if (registrar.contain(clazz)) {
            log.error("ClassEntityRegistrar registed class " + clazz + ", but entityDefinitionRegistrar never registed");
            return;
        }
        if (registering.containsKey(clazz)) {
            return;
        }
        ClassEntityDefinition ced = classReader.readEntityDefinition(clazz);
        registering.put(clazz, ced);

        if (ced != null) {
            for (Field field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(FieldIgnore.class)) {
                    continue;
                }

                AbstractFieldDefinition fd = readField(field);
                if (fd == null) {
                    log.error("Read field failed, could not recognize field definition. class:" + clazz + ",field:" + field.getName());
                    continue;
                }
                fd.setEntityDefinition(ced);
                ced.addField(fd);

                if (fd instanceof EntityFieldDefinition) {
                    setAssociatedEntity((EntityFieldDefinition) fd, field, registrar);
                } else {
                    if (fd instanceof ListFieldDefinition) {
                        setAssociatedList((ListFieldDefinition) fd, field, registrar);
                    }
                }

                PrimaryKeyDefinition pk = readPrimaryKey(fd, field);
                if (pk != null) {
                    Assert.isNull(ced.getPrimaryKey(), "BEntity could have only one PrimaryKey field in class " + clazz);
                    ced.setPrimaryKey(pk);
                }
            }

            Assert.notNull(ced.getPrimaryKey(), "BEntity entity have no primary key, class:" + clazz);
            List<FieldUniqDefinition> fieldUniques = classReader.readFieldUniqsDefinition(clazz, ced);
            ced.setFieldUniqs(fieldUniques);
            registrar.register(clazz, ced);
        }
    }

    private void setAssociatedEntity(EntityFieldDefinition efd, Field field, ClassEntityDefinitionRegister registrar) {
        Class<?> associatedEntityClass = field.getType();
        if (!registrar.contain(associatedEntityClass) && !this.registering.containsKey(associatedEntityClass)) {
            scanClass(field.getType(), registrar);
        }
        if (registrar.contain(associatedEntityClass)) {
            efd.setAssociatedEntity(registrar.getEntityDefinition(associatedEntityClass));
        } else {
            Assert.isTrue(this.registering.containsKey(associatedEntityClass), "Could not find AssociatedEntity of EntityFieldDefinition class:" + efd.getEntityDefinition().getId() + ", field:" + field.getName());
            efd.setAssociatedEntity(this.registering.get(associatedEntityClass));
        }
    }

    private void setAssociatedList(ListFieldDefinition lfd, Field field, ClassEntityDefinitionRegister registrar) {
        Type[] listItemTypes = ((ParameterizedTypeImpl) field.getGenericType()).getActualTypeArguments();
        Class<?> listItemEntityClass = (Class<?>) listItemTypes[0];

        if (!registrar.contain(listItemEntityClass) && !this.registering.containsKey(listItemEntityClass)) {
            scanClass(listItemEntityClass, registrar);
        }
        if (registrar.contain(listItemEntityClass)) {
            lfd.setListItemEntity(registrar.getEntityDefinition(listItemEntityClass));
        } else {
            Assert.isTrue(this.registering.containsKey(listItemEntityClass), "Could not find ListItemEntity of ListFieldDefinition class:" + lfd.getEntityDefinition().getId() + ", field:" + field.getName());
            lfd.setListItemEntity(this.registering.get(listItemEntityClass));
        }
    }

    private AbstractFieldDefinition readField(Field field) {
        ResponsibleChain chain = new ResponsibleChain();
        chain.addNode(new StringFieldDefinitionReader());
        chain.addNode(new IntegerFieldDefinitionReader());
        chain.addNode(new LongFieldDefinitionReader());
        chain.addNode(new FloatFieldDefinitionReader());
        chain.addNode(new DoubleFieldDefinitionReader());
        chain.addNode(new BooleanFieldDefinitionReader());
        chain.addNode(new DateFieldDefinitionReader());
        chain.addNode(new EntityFieldDefinitionReader());
        chain.addNode(new ListFieldDefinitionReader());
        return (AbstractFieldDefinition) chain.start(field);
    }

    private String readEntityDefinitionId(Class<?> clazz) {
        return clazz.getName();
    }

    private PrimaryKeyDefinition readPrimaryKey(AbstractFieldDefinition fieldDefinition, Field field) {

        boolean pkAnnotationPresent = field.isAnnotationPresent(PrimaryKey.class);
        if (pkAnnotationPresent) {
            Assert.isTrue(fieldDefinition instanceof AbstractBasicValueFieldDefinition, "Primary key field type must be Long, Integer or String type," + field.getDeclaringClass().getName() + "." + field.getName());
            AbstractBasicValueFieldDefinition basicValueFieldDefinition = (AbstractBasicValueFieldDefinition) fieldDefinition;
            PrimaryKey annotation = field.getAnnotation(PrimaryKey.class);
            return new PrimaryKeyDefinition(basicValueFieldDefinition, annotation.generatorType());
        }
        return null;
    }
}