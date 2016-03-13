/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.totoland.db.common.dao.hibernate;

import com.totoland.db.common.dao.GennericDao;
import com.totoland.db.dao.BaseDao;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author totoland
 */
@Repository("gennericDao")
public class GennericDaoImpl<T> extends BaseDao implements GennericDao<T>, Serializable {

    private static Logger logger = Logger.getLogger(GennericDaoImpl.class);
    private static final long serialVersionUID = 5620842485855893208L;

    @Override
    @Transactional
    public void create(T entity) {
        getHibernateTemplate().save(entity);
    }

    @Override
    @Transactional
    public void edit(T entity) {
        getHibernateTemplate().merge(entity);
    }

    @Override
    @Transactional
    public void remove(T entity) {
        getHibernateTemplate().delete(entity);
    }

    @Override
    @Transactional
    public T find(Long id, Class<T> entityClass) {
        return getHibernateTemplate().load(entityClass, id);
    }

    @Override
    @Transactional
    public T find(Integer id, Class<T> entityClass) {
        return getHibernateTemplate().load(entityClass, id);
    }

    @Override
    public List<T> findAll(Class<T> entityClass) {
        return getHibernateTemplate().loadAll(entityClass);
    }

    @Override
    public List<T> findRange(int[] range) {
        return getHibernateTemplate().findByExample(range, range[0], range[1] - range[0] + 1);
    }

    @Override
    public long count(Class<T> entityClass) {
        List t = getHibernateTemplate().find("SELECT count(c) FROM " + entityClass.getSimpleName() + " c");

        if (t == null) {
            return 0;
        }

        return (Long) t.get(0);
    }

    @Override
    public List<T> findByStatus(Integer status, Class<T> entityClass) {
        List t = getHibernateTemplate().find("SELECT count(c) FROM " + entityClass.getSimpleName() + " c where c.flowStatusId = ?", status);

        return t;
    }

    @Override
    public List<T> findByDynamicField(Class<T> entityClass, Map<String, Object> hasValue) {

        StringBuilder sql = new StringBuilder().append(" where 1=1 ");
        
        Iterator it = hasValue.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry) it.next();
            System.out.println(pairs.getKey() + " = " + pairs.getValue());
            sql.append(" and ").append("c.").append(pairs.getKey()).append(" = ").append(pairs.getValue());
            it.remove(); 
        }

        List t = getHibernateTemplate().find("select c from " + entityClass.getSimpleName() +" c "+ sql.toString());

        return t;
    }
}
