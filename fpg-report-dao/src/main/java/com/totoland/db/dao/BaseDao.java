package com.totoland.db.dao;

import java.sql.SQLException;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public abstract class BaseDao extends HibernateDaoSupport {

    @Autowired
    public void anyMethodName(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    public Object findUniqNativeQuery(final String SQL, final Class entity, final Object... value) {
        Object t = (Object) getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session sn) throws HibernateException, SQLException {
                SQLQuery query = sn.createSQLQuery(SQL).addEntity(entity);

                for (int i = 0; i < value.length; i++) {
                    query.setParameter(i, value[i]);
                }

                return query.uniqueResult();
            }
        });

        return t;
    }
    
    public List findNativeQuery(final String SQL, final Class entity, final List<Object> value) {
        List t = (List) getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session sn) throws HibernateException, SQLException {
                SQLQuery query = sn.createSQLQuery(SQL).addEntity(entity);

                for (int i = 0; i < value.size(); i++) {
                    query.setParameter(i, value.get(i));
                }

                return query.list();
            }
        });
        return t;
    }
    
    public List findNativeQuery(final String SQL, final Class entity, final Object... value) {
        List t = (List) getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session sn) throws HibernateException, SQLException {
                SQLQuery query = sn.createSQLQuery(SQL).addEntity(entity);

                for (int i = 0; i < value.length; i++) {
                    query.setParameter(i, value[i]);
                }

                return query.list();
            }
        });
        return t;
    }

    public List findNativeQuery(final String SQL, final Class entity) {
        List t = (List) getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session sn) throws HibernateException, SQLException {
                SQLQuery query = sn.createSQLQuery(SQL).addEntity(entity);
                return query.list();
            }
        });
        return t;
    }
    
    public List findNativePagginQuery(final String SQL,final int start,final int max, final Class entity) {
        List t = (List) getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session sn) throws HibernateException, SQLException {
                SQLQuery query = sn.createSQLQuery(genSQLPaggin(SQL,start,max)).addEntity(entity);
                return query.list();
            }
        });
        return t;
    }

    public String genSQLPaggin(String sql, int start, int max) {

        StringBuilder statmentSQL = new StringBuilder();
        statmentSQL.append("SELECT TOP(").append(max).append(") * FROM ( ");
        statmentSQL.append(sql);
        statmentSQL.append(" ) Y");
        statmentSQL.append(" WHERE Y.ROW_NO >");
        statmentSQL.append(start);

        return statmentSQL.toString();
    }

    public Integer countNativeQuery(final String SQL, final Object... value) {
        Object t = getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session sn) throws HibernateException, SQLException {
                SQLQuery query = sn.createSQLQuery(SQL);
                
                for (int i = 0; i < value.length; i++) {
                    query.setParameter(i, value[i]);
                }

                return ((Integer) query.uniqueResult()).intValue();
            }
        });
        return (Integer) t;
    }
    
    public Integer countNativeQuery(final String SQL) {
        return countNativeQuery(SQL, new Object[]{});
    }

    public Integer updateNativeQuery(final String SQL, final Object... value) {
        Object t = getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session sn) throws HibernateException, SQLException {
                SQLQuery query = sn.createSQLQuery(SQL);

                for (int i = 0; i < value.length; i++) {
                    query.setParameter(i, value[i]);
                }

                return query.executeUpdate();
            }
        });
        return (Integer) t;
    }
}