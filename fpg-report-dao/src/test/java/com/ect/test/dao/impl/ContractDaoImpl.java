/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.totoland.test.dao.impl;

import com.totoland.test.dao.ContractDao;
import com.totoland.test.dao.utils.BaseDao;
import com.totoland.test.entity.Contract;
import java.sql.SQLException;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Totoland
 */
@Repository("contractDao")
public class ContractDaoImpl extends BaseDao implements ContractDao {

    private static Logger logger = Logger.getLogger(ContractDaoImpl.class);

    @Override
    public List<Contract> getAllListContract() {
        logger.info("getAllListContract");

        final String sql  = "Contract_SelectAll";
        
        List<Contract> result;

        result = (List<Contract>) getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(final Session session) throws HibernateException, SQLException {
                SQLQuery sq = session.createSQLQuery(sql);
                sq.addEntity(Contract.class);
                return sq.list();
            }
        });

        return result;
    }
}
