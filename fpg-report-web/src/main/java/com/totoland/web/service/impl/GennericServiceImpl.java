/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.totoland.web.service.impl;

import com.totoland.db.common.dao.GennericDao;
import com.totoland.web.service.GennericService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author totoland
 */
@Service(value = "gennericService")
public class GennericServiceImpl<T> implements GennericService<T> {

    @Autowired
    GennericDao<T> gennericDao;

    @Override
    public void create(T entity) {
        gennericDao.create(entity);
    }

    @Override
    public void edit(T entity) {
        gennericDao.edit(entity);
    }

    @Override
    public void remove(T entity) {
        gennericDao.remove(entity);
    }

    @Override
    public T find(Long id, Class<T> entityClass) {
        return gennericDao.find(id, entityClass);
    }
    
    @Override
    public T find(Integer id, Class<T> entityClass) {
        return gennericDao.find(id, entityClass);
    }
    
    @Override
    public List<T> findAll(Class<T> entityClass) {
        return gennericDao.findAll(entityClass);
    }

    @Override
    public List<T> findRange(int[] range) {
        return gennericDao.findRange(range);
    }

    @Override
    public long count(Class<T> entityClass) {
        return gennericDao.count(entityClass);
    }

    @Override
    public List<T> findByStatusId(Integer status,Class<T> entityClass) {
        return gennericDao.findByStatus(status, entityClass);
    }
    
    @Override
    public List<T> findByDynamicField(Class<T> entityClass, Map<String, Object> hasValue){
        return gennericDao.findByDynamicField(entityClass, hasValue);
    }

}
