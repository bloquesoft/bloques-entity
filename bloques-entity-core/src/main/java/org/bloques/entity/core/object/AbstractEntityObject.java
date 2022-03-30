/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:AbstractEntityObject.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.core.object;

import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.bloques.entity.core.definition.EntityDefinition;
import org.bloques.entity.core.definition.EntityFieldDefinition;
import org.bloques.entity.core.definition.ListFieldDefinition;
import org.bloques.entity.core.definition.basicValue.AbstractBasicValueFieldDefinition;
import org.bloques.entity.core.id.IdGeneratorFactory;
import org.bloques.entity.core.id.LongIdGenerator;
import org.bloques.entity.core.object.fieldObject.*;

import java.util.*;

@Slf4j
public abstract class AbstractEntityObject {

    @Getter
    private final Object value;

    @Getter
    private final EntityDefinition entityDefinition;

    @Getter
    private final List<AbstractFieldObject> fieldObjects = new LinkedList<>();

    @Getter
    private final List<AbstractBasicFieldObject> basicFieldObjects = new LinkedList<>();

    @Getter
    private final List<ListFieldObject> listFieldObjects = new LinkedList<>();

    @Getter
    private final List<EntityFieldObject> entityFieldObjects = new LinkedList<>();

    @Getter
    private final boolean isNewEntity;

    private final Map<String, AbstractFieldObject> fieldObjectMap = new HashMap<>();

    public AbstractEntityObject(EntityDefinition entityDefinition, Object value) {
        this.entityDefinition = Preconditions.checkNotNull(entityDefinition, "init argument entityDefinition could not be null");
        this.value = Preconditions.checkNotNull(value, "init argument value could not be null");
        this.isNewEntity = this.getPkValue() == null;
        initFieldObjects();
    }

    private void initFieldObjects() {
        for (AbstractBasicValueFieldDefinition fd : this.getEntityDefinition().getBaseFields()) {
            AbstractBasicFieldObject bfo = BasicFieldObjectFactory.create(this, fd);
            this.fieldObjectMap.put(bfo.getFieldId(), bfo);
            this.fieldObjects.add(bfo);
            this.basicFieldObjects.add(bfo);
        }
        for (ListFieldDefinition lfd : this.getEntityDefinition().getListFields()) {
            ListFieldObject lfo = new ListFieldObject(this, lfd);
            this.fieldObjectMap.put(lfo.getFieldId(), lfo);
            this.fieldObjects.add(lfo);
            this.listFieldObjects.add(lfo);
        }
        for(EntityFieldDefinition efd : this.getEntityDefinition().getEntityFields())
        {
            EntityFieldObject efo = new EntityFieldObject(this, efd);
            this.fieldObjectMap.put(efo.getFieldId(), efo);
            this.fieldObjects.add(efo);
            this.entityFieldObjects.add(efo);
        }
    }

    public boolean match(AbstractEntityObject entityObject) {
        if (entityObject != null) {
            if (this.getEntityDefinition().equals(entityObject.getEntityDefinition())) {
                if (this.isNewEntity()) {
                    if (entityObject.isNewEntity()) {
                        return this.getValue() == entityObject.getValue();
                    } else {
                        return false;
                    }
                }
                return this.getPkValue().equals(entityObject.getPkValue());
            }
        }
        log.error("match a null entityObject, AbstractEntityObject.match");
        return false;
    }

    public Object getPkValue() {
        String pkFieldId = this.getEntityDefinition().getPrimaryKey().getId();
        return this.getOriginalFieldValue(pkFieldId);
    }

    public void generatePk() {
        String pkFieldId = this.getEntityDefinition().getPrimaryKey().getId();
        LongIdGenerator generator = IdGeneratorFactory.singleton().getGenerator(this.getEntityDefinition());
        this.setOriginalFieldValue(pkFieldId, generator.generate());
    }

    public AbstractFieldObject getFieldObject(String fieldId) {
        return this.fieldObjectMap.get(fieldId);
    }

    public ListFieldObject getListFieldObject(String fieldId) {
        AbstractFieldObject fo = this.fieldObjectMap.get(fieldId);
        if (fo != null) {
            return (ListFieldObject) fo;
        }
        return null;
    }

    public EntityFieldObject getEntityFieldObject(String fieldId) {
        AbstractFieldObject fo = this.fieldObjectMap.get(fieldId);
        if (fo != null) {
            return (EntityFieldObject) fo;
        }
        return null;
    }

    public Object getFieldValue(String fieldId) {
        if (fieldId != null && this.isExistOriginalField(fieldId)) {
            return this.getOriginalFieldValue(fieldId);
        }
        return null;
    }

    public void setFieldValue(String fieldId, Object value) {
        if (fieldId != null && this.isExistOriginalField(fieldId)) {
            this.setOriginalFieldValue(fieldId, value);
        }
    }

    public abstract boolean isExistOriginalField(String fieldId);

    protected abstract Object getOriginalFieldValue(String fieldId);

    protected abstract void setOriginalFieldValue(String fieldId, Object value);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractEntityObject)) return false;
        AbstractEntityObject that = (AbstractEntityObject) o;
        return value.equals(that.value) && entityDefinition.equals(that.entityDefinition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, entityDefinition);
    }
}