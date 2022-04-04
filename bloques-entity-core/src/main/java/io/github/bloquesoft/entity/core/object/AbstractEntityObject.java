/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:AbstractEntityObject.java
 * Author:zhangjian
 * date:2022/3/31 下午10:15
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.core.object;

import com.google.common.base.Preconditions;
import io.github.bloquesoft.entity.core.definition.*;
import io.github.bloquesoft.entity.core.definition.basicValue.*;
import io.github.bloquesoft.entity.core.id.IdGeneratorFactory;
import io.github.bloquesoft.entity.core.id.LongIdGenerator;
import io.github.bloquesoft.entity.core.object.fieldObject.*;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

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

    private final Map<String, AbstractFieldObject> fieldObjectMap = new HashMap<>();

    public AbstractEntityObject(EntityDefinition entityDefinition, Object value) {
        this.entityDefinition = Preconditions.checkNotNull(entityDefinition, "init argument entityDefinition could not be null");
        this.value = Preconditions.checkNotNull(value, "init argument value could not be null");
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
        for (EntityFieldDefinition efd : this.getEntityDefinition().getEntityFields()) {
            EntityFieldObject efo = new EntityFieldObject(this, efd);
            this.fieldObjectMap.put(efo.getFieldId(), efo);
            this.fieldObjects.add(efo);
            this.entityFieldObjects.add(efo);
        }
    }

    public boolean match(AbstractEntityObject entityObject) {
        if (entityObject != null) {
            if (this.getEntityDefinition().equals(entityObject.getEntityDefinition())) {
                if (this.isPkEmpty()) {
                    if (entityObject.isPkEmpty()) {
                        return this.getValue().equals(entityObject.getValue());
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

    public boolean isPkEmpty() {
        Object pkValue = this.getPkValue();
        if (pkValue == null) {
            return true;
        }
        AbstractBasicValueFieldDefinition pkField = this.getEntityDefinition().getPrimaryKey().getBasicField();
        if (pkField instanceof StringFieldDefinition) {
            return !StringUtils.hasLength(pkValue.toString());
        }
        if (pkField instanceof IntegerFieldDefinition) {
            return Integer.valueOf(pkValue.toString()) <= 0;
        }
        if (pkField instanceof LongFieldDefinition) {
            return Long.valueOf(pkValue.toString()) <= 0L;
        }
        return this.getPkValue() == null;
    }

    public Object getPkValue() {
        String pkFieldId = this.getEntityDefinition().getPrimaryKey().getId();
        return this.getOriginalFieldValue(pkFieldId);
    }

    public void generatePk() {

        PrimaryKeyDefinition pk = this.getEntityDefinition().getPrimaryKey();
        if (pk.getGenerationType() == PrimaryKeyGenerationType.None) {
            return;
        }
        if (pk.getGenerationType() == PrimaryKeyGenerationType.Snowflake && pk.getBasicField().getValueType() == BasicValueType.Long) {
            String pkFieldId = this.getEntityDefinition().getPrimaryKey().getId();
            LongIdGenerator generator = IdGeneratorFactory.singleton().getGenerator(this.getEntityDefinition());
            this.setOriginalFieldValue(pkFieldId, generator.generate());
            return;
        }
        throw new IllegalArgumentException("BEntity only generate pk for Long type field.");
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