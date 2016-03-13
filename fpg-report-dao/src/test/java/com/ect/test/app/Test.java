/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.totoland.test.app;

import com.totoland.test.dao.ContractDao;
import com.totoland.test.entity.Contract;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Totoland
 */
//@ContextConfiguration(locations = {
//    "classpath:spring/config/BeanLocations.xml"})
public class Test {
    private static  Logger logger = Logger.getLogger(Test.class);
    
    @Autowired
    static ContractDao contractDao;
    
    public static void main(String args[])
    {
        logger.info("Test!!");
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        
//        List list = session.createSQLQuery("Contract_SelectAll").addEntity(Contract.class).list();
//        
//        logger.debug("list size : "+list.size());
//        
//        for(int i=0;i<list.size();i++){
//            Contract contract = (Contract)list.get(i);
//            logger.debug(contract);
//        }
        
//        ApplicationContext context = new ClassPathXmlApplicationContext("spring/config/BeanLocations.xml");
//        ContractDao dao = (ContractDao)context.getBean("contractDao");
        
        logger.info("spring hibernate!!");
        List<Contract>listContract = contractDao.getAllListContract();
        
        for(Contract contract : listContract){
            logger.debug(contract.toString());
        }
    }
}
