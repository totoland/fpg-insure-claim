/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.totoland.web.service;

import java.util.List;
import java.util.Map;

/**
 *
 * @author totoland
 */
public interface GennericService<T> {
    
    public void create(T entity);

    public void edit(T entity);

    public void remove(T entity);

    public T find(Long id,Class<T> entityClass);
    
    public T find(Integer id,Class<T> entityClass);

    public List<T> findAll(Class<T> entityClass);

    public List<T> findRange(int[] range);

    public long count(Class<T> entityClass);
    
    public List<T> findByStatusId(Integer status,Class<T> entityClass);
    
    public List<T> findByDynamicField(Class<T> entityClass, Map<String, Object> hasValue);
}
