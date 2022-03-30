/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:EntityGraphLoaderImpl.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.core.loader.impl;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.bloques.entity.core.definition.AbstractFieldDefinition;
import org.bloques.entity.core.definition.EntityDefinition;
import org.bloques.entity.core.definition.EntityFieldDefinition;
import org.bloques.entity.core.definition.ListFieldDefinition;
import org.bloques.entity.core.graph.GraphField;
import org.bloques.entity.core.graph.GraphParser;
import org.bloques.entity.core.graph.GraphRoot;
import org.bloques.entity.core.loader.EntityGraphLoader;
import org.bloques.entity.core.loader.EntityLoader;
import org.bloques.entity.core.object.AbstractEntityObject;
import org.bloques.entity.core.object.EntityObjectCreator;
import org.bloques.entity.core.object.fieldObject.EntityFieldObject;
import org.bloques.entity.core.object.fieldObject.ListFieldObject;
import org.bloques.entity.core.repository.EntityRepositoryFactory;

import java.util.*;

@Slf4j
public class EntityGraphLoaderImpl implements EntityGraphLoader {

    @Getter
    private final EntityRepositoryFactory entityRepositoryFactory;

    @Getter
    private final GraphParser graphParser;


    private static final EntityLoader ENTITY_LOADER = new EntityLoaderImpl();

    public EntityGraphLoaderImpl(EntityRepositoryFactory entityRepositoryFactory, GraphParser graphParser) {
        this.entityRepositoryFactory = entityRepositoryFactory;
        this.graphParser = graphParser;
    }

    @Override
    public void load(AbstractEntityObject entityObj, String graph) {
        Map<String, Object> loadedEntityObjects = new HashMap<>();
        GraphRoot root;
        try {
            root = graphParser.parse(graph);
        } catch (Exception ept) {
            throw new IllegalArgumentException("JEntity Graph is not valid, graph:" + graph);
        }
        if (root != null) {
            loadFields(entityObj, root.getChildren(), new ArrayList<>(), loadedEntityObjects);
        }
    }

    @Override
    public void load(EntityDefinition ed, Object entity, String graph) {
        AbstractEntityObject entityObj = EntityObjectCreator.getSingleton().create(ed, entity);
        this.load(entityObj, graph);
    }

    @Override
    public void loadList(EntityDefinition ed, List<Object> list, String graph) {
        for (Object item : list) {
            this.load(ed, item, graph);
        }
    }

    @Override
    public void loadList(List<AbstractEntityObject> list, String graph) {
        for (AbstractEntityObject item : list) {
            this.load(item, graph);
        }
    }

    private List<GraphField> mergeRecursiveFieldsFromFields(List<GraphField> fields, List<GraphField> recursiveFields) {
        List<GraphField> newRecursiveFields = null;
        for (GraphField field : fields) {
            if (field.isRecursiveLoaded()) {
                boolean needToAppend = true;
                for (GraphField recursiveFiels : recursiveFields) {
                    if (field.getFieldName().equals(recursiveFiels.getFieldName())) {
                        needToAppend = false;
                        break;
                    }
                }
                if (needToAppend) {
                    if (newRecursiveFields == null) {
                        newRecursiveFields = new LinkedList<>(recursiveFields);
                    }
                    newRecursiveFields.add(field);
                }
            }
        }
        if (newRecursiveFields != null) {
            return newRecursiveFields;
        } else {
            return recursiveFields;
        }
    }

    private void mergeFieldsFromRecursiveFields(List<GraphField> fields, List<GraphField> recursiveFields) {
        for (GraphField recursiveField : recursiveFields) {
            Optional<GraphField> opField = fields.stream().filter(i -> i.getFieldName().equals(recursiveField.getFieldName())).findFirst();
            if (opField.isPresent()) {
                if (!opField.get().isRecursiveLoaded()) {
                    opField.get().setRecursiveLoaded(true);
                }
            } else {
                fields.add(recursiveField);
            }
        }
    }

    private void loadFields(AbstractEntityObject entityObj, List<GraphField> fields, List<GraphField> recursiveFields, Map<String, Object> loadedEntityObjects) {
        List<GraphField> newRecursiveFields = mergeRecursiveFieldsFromFields(fields, recursiveFields);
        mergeFieldsFromRecursiveFields(fields, newRecursiveFields);
        for (GraphField field : fields) {
            loadField(entityObj, field, newRecursiveFields, loadedEntityObjects);
        }
    }

    private void loadField(AbstractEntityObject entityObj, GraphField field, List<GraphField> recursiveFields, Map<String, Object> loadedEntityObjects) {
        String fieldName = field.getFieldName();
        AbstractFieldDefinition fd = entityObj.getEntityDefinition().getField(fieldName);
        if (fd != null) {
            if (fd instanceof EntityFieldDefinition) {
                loadEntityField((EntityFieldDefinition) fd, entityObj, field, recursiveFields, loadedEntityObjects);
            } else {
                if (fd instanceof ListFieldDefinition) {
                    loadListField((ListFieldDefinition) fd, entityObj, field, recursiveFields, loadedEntityObjects);
                }
            }
        }
    }

    private void loadListField(ListFieldDefinition efd, AbstractEntityObject entityObj, GraphField field, List<GraphField> recursiveFields, Map<String, Object> loadedEntityObjects) {

        ListFieldObject listFieldObj = entityObj.getListFieldObject(efd.getId());
        ENTITY_LOADER.loadListField(listFieldObj, entityRepositoryFactory);
        for (AbstractEntityObject childEntityObj : listFieldObj.getListItemObjects()) {
            loadFields(childEntityObj, field.getChildren(), recursiveFields, loadedEntityObjects);
        }
    }

    private String loadedEntityObjectKey(EntityFieldDefinition efd, Object associatedId) {
        return efd.getEntityDefinition().getId() + "_" + associatedId.toString();
    }

    private Object readFieldValueFromLoadedObjects(EntityFieldDefinition efd, Object associatedId, Map<String, Object> loadedEntityObjects) {
        String key = loadedEntityObjectKey(efd, associatedId);
        if (loadedEntityObjects.containsKey(key)) {
            return loadedEntityObjects.get(key);
        }
        return null;
    }

    private void writeFieldValueToLoadedObjects(EntityFieldDefinition efd, Object associatedId, Object associatedObject, Map<String, Object> loadedEntityObjects) {
        String key = loadedEntityObjectKey(efd, associatedId);
        loadedEntityObjects.put(key, associatedObject);
    }

    private Object readFieldValueObject(EntityFieldDefinition efd, AbstractEntityObject entityObj, Map<String, Object> loadedEntityObjects) {

        EntityFieldObject entityFieldObject = entityObj.getEntityFieldObject(efd.getId());
        Object associatedId = entityObj.getFieldValue(efd.getAssociatedFieldId());
        if (associatedId == null) {
            return null;
        }
        Object associatedObject = readFieldValueFromLoadedObjects(efd, associatedId, loadedEntityObjects);
        if (associatedObject != null) {
            return associatedObject;
        }
        ENTITY_LOADER.loadEntityField(entityFieldObject, this.entityRepositoryFactory);
        associatedObject = entityFieldObject.getValue();
        if (associatedObject != null) {
            writeFieldValueToLoadedObjects(efd, associatedId, entityFieldObject.getValue(), loadedEntityObjects);
        }
        return associatedObject;
    }

    private void loadEntityField(EntityFieldDefinition efd, AbstractEntityObject entityObject, GraphField field, List<GraphField> recursiveFields, Map<String, Object> loadedEntityObjects) {

        Object associatedObjectValue = readFieldValueObject(efd, entityObject, loadedEntityObjects);
        if (associatedObjectValue != null) {
            entityObject.setFieldValue(efd.getId(), associatedObjectValue);
            AbstractEntityObject associatedObject = EntityObjectCreator.getSingleton().create(efd.getEntityDefinition(), associatedObjectValue);
            loadFields(associatedObject, field.getChildren(), recursiveFields, loadedEntityObjects);
        }
    }
}