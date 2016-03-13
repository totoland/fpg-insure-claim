/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ect.dbmrgdao;

import com.totoland.db.bean.UserCriteria;
import com.totoland.db.common.dao.GennericDao;
import com.totoland.db.entity.GroupLvl;
import com.totoland.db.user.dao.UserDao;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 *
 * @author Totoland
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/config/BeanLocations.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
//@Transactional
public class Test {
    
    @Autowired
    GennericDao<GroupLvl>gennericDao;
    
    @Autowired
    UserDao userDao;
    
    @org.junit.Test
    public void testDao() {

         System.out.println("TEST!!");
         
         System.out.println("Count : "+gennericDao.count(GroupLvl.class));
         
         System.out.println("userDao : "+userDao.searchByUserCriteria(new UserCriteria()));
    }

}
