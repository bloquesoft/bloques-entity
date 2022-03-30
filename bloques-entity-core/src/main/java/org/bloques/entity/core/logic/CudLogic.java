/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:CudLogic.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.core.logic;

import lombok.Getter;
import org.bloques.entity.core.checking.FieldStaticChecking;
import org.bloques.entity.core.definition.EntityFieldDefinition;
import org.bloques.entity.core.definition.FieldUniqDefinition;
import org.bloques.entity.core.definition.ListRelationShip;
import org.bloques.entity.core.definition.basicValue.AbstractBasicValueFieldDefinition;
import org.bloques.entity.core.loader.EntityLoader;
import org.bloques.entity.core.loader.impl.EntityLoaderImpl;
import org.bloques.entity.core.logic.error.LogicErrors;
import org.bloques.entity.core.object.AbstractEntityObject;
import org.bloques.entity.core.object.EntityObjectCreator;
import org.bloques.entity.core.object.fieldObject.AbstractBasicFieldObject;
import org.bloques.entity.core.object.fieldObject.AbstractFieldObject;
import org.bloques.entity.core.object.fieldObject.ListFieldObject;
import org.bloques.entity.core.repository.EntityRepository;
import org.bloques.entity.core.repository.EntityRepositoryFactory;
import org.springframework.util.Assert;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public class CudLogic {

    private static final EntityLoader entityLoader = new EntityLoaderImpl();

    @Getter
    private final EntityRepositoryFactory entityRepositoryFactory;

    @Getter
    private final CudLog cudLog;

    public CudLogic(EntityRepositoryFactory entityRepositoryFactory, CudLog log) {
        this.entityRepositoryFactory = entityRepositoryFactory;
        this.cudLog = log;
    }

    public void add(AbstractEntityObject entityObj) {
        doAdd(entityObj);
    }

    public void delete(AbstractEntityObject entityObj) {
        doDelete(entityObj);
    }

    public void update(AbstractEntityObject newEntityObjValue, AbstractEntityObject targetEntityObj) {
        doUpdate(newEntityObjValue, targetEntityObj);
    }

    private void doAdd(AbstractEntityObject entityObj) {
        accessEntityTree(entityObj, this::doStaticCheckingEntity);
        accessEntityTree(entityObj, this::doDynamicCheckingEntity);
    }

    protected void doStaticCheckingEntity(AbstractEntityObject entityObj) {
        accessFields(entityObj, this::doStaticCheckField);
        if (entityObj.isNewEntity()) {
            entityObj.generatePk();
        }
        //执行自定义静态认证
        accessListFields(entityObj, this::setListFieldRelationShip);
    }

    private void doStaticCheckField(AbstractFieldObject fo) {
        fo.setDefaultValue();
        FieldStaticChecking.singleton().check(fo);
    }

    private void setListFieldRelationShip(ListFieldObject lfo) {
        if (lfo.getListItemObjects() != null) {
            for (AbstractEntityObject listItemObj : lfo.getListItemObjects()) {
                for (ListRelationShip rs : lfo.getListFieldDefinition().getRelationShips()) {
                    Object associatedFieldValue = lfo.getEntityObject().getFieldValue(rs.getAssociatedFieldId());
                    String listItemFieldId = rs.getAssociatedListItemFieldId();
                    listItemObj.setFieldValue(listItemFieldId, associatedFieldValue);
                }
            }
        }
    }

    private void doDynamicCheckingEntity(AbstractEntityObject entityObj) {
        checkEntityFieldsValid(entityObj);
        checkEntityFieldsUniques(entityObj);
        //执行自定义动态认证
        cudLog.appendEntity(entityObj);
    }

    private void checkEntityFieldsValid(AbstractEntityObject entityObj) {
        for (EntityFieldDefinition efd : entityObj.getEntityDefinition().getEntityFields()) {
            Object associatedFieldValue = entityObj.getFieldValue(efd.getAssociatedFieldId());
            checkEntityFieldValid(efd, associatedFieldValue);
        }
    }

    private void checkEntityFieldValid(EntityFieldDefinition efd, Object associatedFieldValue) {
        if (associatedFieldValue != null) {
            if (associatedFieldValue instanceof Long) {
                if ((Long) associatedFieldValue <= 0) {
                    return;
                }
            }
        }
        if (cudLog.isValidPk(efd.getAssociatedEntity(), associatedFieldValue)) {
            return;
        }
        EntityRepository repo = entityRepositoryFactory.getRepository(efd.getAssociatedEntityId());
        Object obj = repo.findById(efd.getAssociatedEntity(), associatedFieldValue);
        if (obj == null) {
            LogicErrors.FIELD_VALUE_NOT_VALID_ERROR.throwException(efd.getAssociatedField());
        }
    }

    private void checkEntityFieldsUniques(AbstractEntityObject entityObj) {
        List<FieldUniqDefinition> uniques = entityObj.getEntityDefinition().getFieldUniqs();
        if (uniques != null)
        {
            for (FieldUniqDefinition uniqueDefinition : uniques) {
                checkEntityFieldsUnique(entityObj, uniqueDefinition);
            }
        }
    }

    private void checkEntityFieldsUnique(AbstractEntityObject entityObj, FieldUniqDefinition uniqDefinition) {
        String entityDefinitionId = entityObj.getEntityDefinition().getId();
        EntityRepository repo = entityRepositoryFactory.getRepository(entityDefinitionId);
        Assert.notNull(repo, "Could not find EntityRepository entityDefinitionId:" + entityDefinitionId);
        LinkedHashMap<String, Object> args = new LinkedHashMap<>();
        for (AbstractBasicValueFieldDefinition fieldDefinition : uniqDefinition.getUniqFields()) {
            String fieldId = fieldDefinition.getId();
            Object fieldValue = entityObj.getFieldValue(fieldId);
            args.put(fieldId, fieldValue);
        }
        List<AbstractEntityObject> listEntity = cudLog.findEntityList(entityObj.getEntityDefinition(), args);
        if(listEntity.size() > 0){
            LogicErrors.FIELD_UNIQUE_ERROR.throwException(uniqDefinition);
        }
        List<Object> list = repo.findList(entityObj.getEntityDefinition(), args);
        if (list.size() > 0) {
            if (list.size() == 1) {
                AbstractEntityObject existEntityValue = EntityObjectCreator.getSingleton().create(entityObj.getEntityDefinition(), list.get(0));
                if (!entityObj.match(existEntityValue)) {
                    LogicErrors.FIELD_UNIQUE_ERROR.throwException(uniqDefinition);
                }
            } else {
                LogicErrors.FIELD_UNIQUE_ERROR.throwException(uniqDefinition);
            }
        }
    }

    private void doDelete(AbstractEntityObject entityObj) {
        this.accessEntityTree(entityObj, cudLog::deleteEntity);
    }

    private void doUpdate(AbstractEntityObject newEntityObjValue, AbstractEntityObject targetEntityObj) {

        for (AbstractBasicFieldObject fo : targetEntityObj.getBasicFieldObjects()) {
            String fieldName = fo.getFieldId();
            if (newEntityObjValue.isExistOriginalField(fieldName)) {
                if (!fo.getFieldDefinition().getAllowModify()) {
                    continue;
                }
                Object newFieldValue = newEntityObjValue.getFieldValue(fieldName);
                if (newFieldValue != null) {
                    AbstractFieldObject oldTargetFieldObj = targetEntityObj.getFieldObject(fieldName);
                    Object oldTargetFieldValue = oldTargetFieldObj.getValue();
                    if (oldTargetFieldValue == null || !oldTargetFieldValue.equals(newFieldValue)) {
                        targetEntityObj.setFieldValue(fieldName, newFieldValue);
                        FieldStaticChecking.singleton().check(oldTargetFieldObj);
                        //动态验证Entity

                    }
                }
            }
        }

        accessListFields(newEntityObjValue, this::setListFieldRelationShip);
        for (ListFieldObject targetListFieldObj : targetEntityObj.getListFieldObjects()) {
            if (!targetListFieldObj.getListFieldDefinition().getAllowModify()) {
                continue;
            }
            String fieldName = targetListFieldObj.getFieldId();
            if (newEntityObjValue.isExistOriginalField(fieldName)) {
                if (targetListFieldObj.getListValue() == null) {
                    entityLoader.loadListField(targetListFieldObj, this.entityRepositoryFactory);
                }
                ListFieldObject newListFieldObject = newEntityObjValue.getListFieldObject(fieldName);
                update(targetListFieldObj, newListFieldObject);
                FieldStaticChecking.singleton().check(targetListFieldObj);
            }
        }
        //自定义静态认证
        for (EntityFieldDefinition efd : targetEntityObj.getEntityDefinition().getEntityFields()) {
            if (newEntityObjValue.isExistOriginalField(efd.getAssociatedFieldId()))
            {
                Object associatedFieldValue = targetEntityObj.getFieldValue(efd.getAssociatedFieldId());
                checkEntityFieldValid(efd, associatedFieldValue);
            }
        }

        checkEntityFieldsUniques(targetEntityObj);
        //自定义动态认证
        cudLog.updateField(targetEntityObj);
    }

    private void update(ListFieldObject targetListFieldObj, ListFieldObject newListFieldObject) {
        List<AbstractEntityObject> listForDelete = new LinkedList<>();
        if (targetListFieldObj.getListItemObjects() != null) {
            for (AbstractEntityObject targetListItemObj : targetListFieldObj.getListItemObjects()) {
                AbstractEntityObject newMatchedItemObj = newListFieldObject.findFirstMatchedItem(targetListItemObj);
                if (newMatchedItemObj != null) {
                    doUpdate(newMatchedItemObj, targetListItemObj);
                } else {
                    listForDelete.add(targetListItemObj);
                }
            }
        }
        for (AbstractEntityObject item : listForDelete) {
            doDelete(item);
            targetListFieldObj.removeItemByPk(item.getPkValue());
        }
        if (newListFieldObject.getListItemObjects() != null) {
            for (AbstractEntityObject newListItemObj : newListFieldObject.getListItemObjects()) {
                if (newListItemObj.getPkValue() == null) {
                    doAdd(newListItemObj);
                    targetListFieldObj.appendItem(newListItemObj.getValue());
                } else {
                    if (targetListFieldObj.findFirstMatchedItem(newListItemObj) == null) {
                        //不存在对应要修改的列表项目
                    }
                }
            }
        }
    }

    private void accessFields(AbstractEntityObject entityObj, Consumer<AbstractFieldObject> function) {
        for (AbstractFieldObject fo : entityObj.getFieldObjects()) {
            function.accept(fo);
        }
    }

    private void accessListFields(AbstractEntityObject entityObj, Consumer<ListFieldObject> function) {
        for (ListFieldObject lfo : entityObj.getListFieldObjects()) {
            function.accept(lfo);
        }
    }

    private void accessEntityTree(AbstractEntityObject entityObj, Consumer<AbstractEntityObject> function) {
        function.accept(entityObj);
        for (ListFieldObject listField : entityObj.getListFieldObjects()) {
            if (listField.getListItemObjects() != null) {
                for (AbstractEntityObject listItem : listField.getListItemObjects()) {
                    accessEntityTree(listItem, function);
                }
            }
        }
    }
}