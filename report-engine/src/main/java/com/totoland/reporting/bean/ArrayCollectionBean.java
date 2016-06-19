/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.totoland.reporting.bean;

import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author totoland
 */
public class ArrayCollectionBean implements Serializable {

    private static final long serialVersionUID = -1780052226831681117L;

    private String jrxml;
    private Collection<?> beanCollection;

    public ArrayCollectionBean(String jrxml, Collection<?> beanCollection) {
        this.jrxml = jrxml;
        this.beanCollection = beanCollection;
    }
    
    public String getJrxml() {
        return jrxml;
    }

    public void setJrxml(String jrxml) {
        this.jrxml = jrxml;
    }

    public Collection<?> getBeanCollection() {
        return beanCollection;
    }

    public void setBeanCollection(Collection<?> beanCollection) {
        this.beanCollection = beanCollection;
    }

}
