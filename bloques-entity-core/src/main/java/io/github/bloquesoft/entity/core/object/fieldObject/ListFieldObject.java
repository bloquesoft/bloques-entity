/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-core
 * FileName:ListFieldObject.java
 * Author:zhangjian
 * date:2022/3/31 下午10:15
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.core.object.fieldObject;

import com.google.common.base.Preconditions;
import io.github.bloquesoft.entity.core.object.AbstractEntityObject;
import io.github.bloquesoft.entity.core.object.EntityObjectCreator;
import lombok.Getter;
import io.github.bloquesoft.entity.core.definition.ListFieldDefinition;

import java.util.LinkedList;
import java.util.List;

public class ListFieldObject extends AbstractFieldObject
{
    @Getter
    private final ListFieldDefinition listFieldDefinition;

    @Getter
    private List<AbstractEntityObject> listItemObjects;

    @Getter
    private Boolean isLoaded;

    public ListFieldObject(AbstractEntityObject entityObject, ListFieldDefinition listFieldDefinition) {
        super(entityObject, listFieldDefinition);
        this.listFieldDefinition = listFieldDefinition;
        this.intiList();
    }

    @Override
    protected void format(FieldObjectFormatVisitor formatVisitor) {
        formatVisitor.visit(this);
    }

    @Override
    public void setValue(Object value) {
        super.setValue(value);
        this.intiList();
    }

    public AbstractEntityObject findFirstMatchedItem(AbstractEntityObject entityObject)
    {
        if (entityObject == null)
        {
            return null;
        }
        if (this.getListItemObjects() != null)
        {
            for(AbstractEntityObject itemObj : this.getListItemObjects())
            {
                if (itemObj.match(entityObject))
                {
                    return itemObj;
                }
            }
        }
        return null;
    }

    public List<Object> getListValue()
    {
        Object listObj = super.getValue();
        if (listObj != null)
        {
            return (List<Object>)listObj;
        }
        return null;
    }

    private void intiList()
    {
        Object listObj = super.getValue();
        if (listObj == null) {
            if (this.getEntityObject().isPkEmpty()) {
                List<Object> list = new LinkedList<>();
                super.setValue(list);
                this.listItemObjects = new LinkedList<>();
                this.isLoaded = true;
            }
        } else{
            Preconditions.checkState(listObj instanceof List);
            this.listItemObjects = new LinkedList<>();
            for(Object item : (List)listObj)
            {
                AbstractEntityObject listItemObj = EntityObjectCreator.getSingleton().create(this.getListFieldDefinition().getListItemEntity(), item);
                this.listItemObjects.add(listItemObj);
            }
            this.isLoaded = true;
        }
    }

    public void appendItem(Object listItem)
    {
        Preconditions.checkState(this.isLoaded);
        if (listItem != null)
        {
            AbstractEntityObject newItem = EntityObjectCreator.getSingleton().create(this.getListFieldDefinition().getListItemEntity(), listItem);
            AbstractEntityObject matchedObj = this.findFirstMatchedItem(newItem);
            if (matchedObj == null){
                this.getListValue().add(listItem);
                this.listItemObjects.add(newItem);
            }
        }
    }

    public void removeItemByPk(Object itemPk)
    {
        Preconditions.checkState(this.isLoaded);
        AbstractEntityObject item = this.findItemByPk(itemPk);
        if (item != null)
        {
            List<Object> listValue = this.getListValue();
            listValue.remove(item.getValue());
            this.getListItemObjects().remove(item);
        }
    }

    private AbstractEntityObject findItemByPk(Object itemPk)
    {
        if (itemPk != null)
        {
            for(AbstractEntityObject item : this.getListItemObjects())
            {
                if (itemPk.equals(item.getPkValue()))
                {
                    return item;
                }
            }
        }
        return null;
    }

    @Override
    protected boolean isEmptyValue(Object value) {
        return value == null || ((List)value).size() == 0;
    }

    @Override
    public void accept(FieldObjectVisitor visitor) {
        visitor.visit(this);
    }
}