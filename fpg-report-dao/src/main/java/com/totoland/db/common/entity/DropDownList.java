/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.totoland.db.common.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 *
 * @author Totoland
 */
@Entity
public class DropDownList implements Serializable{
    private static final long serialVersionUID = 296386523126655771L;
    @Column(name="feild_name")
    @Id
    private String name;
    @Column(name="feild_value")
    private String value;
    @Column(name="table_name")
    private String tableName;
    
    @Column(name="schema_name")
    private String schema;
    @Transient
    private String condition;
    
    @Column(name="order_by")
    private String orderByField;
    @Column(name="sort_by")
    private String sortName = "asc";
    
    public DropDownList(){
    
    }
    
    public DropDownList(String name,String value){
        this.name = name;
        this.value = value;
    }
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @return the tableName
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * @param tableName the tableName to set
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * @return the schema
     */
    public String getSchema() {
        return schema;
    }

    /**
     * @param schema the schema to set
     */
    public void setSchema(String schema) {
        this.schema = schema;
    }

    /**
     * @return the orderByField
     */
    public String getOrderByField() {
        return orderByField;
    }

    /**
     * @param orderByField the orderByField to set
     */
    public void setOrderByField(String orderByField) {
        this.orderByField = orderByField;
    }

    /**
     * @return the sortName
     */
    public String getSortName() {
        return sortName;
    }

    /**
     * @param sortName the sortName to set
     */
    public void setSortName(String sortName) {
        this.sortName = sortName;
    }
    @Override
    public String toString(){
        return "TableName : "+this.getTableName()+" name : "+this.getName()+" value : "+this.getValue();
    }

    /**
     * @return the condition
     */
    public String getCondition() {
        return condition;
    }

    /**
     * @param condition the condition to set
     */
    public void setCondition(String condition) {
        this.condition = condition;
    }
}
