/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.totoland.db.openpolicy.dao.test;

import com.totoland.db.bean.OpenPolicyCriteria;
import com.totoland.db.entity.OpenPolicy;
import com.totoland.db.openpolicy.dao.OpenPolicyDao;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author totoland
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "classpath:spring/config/BeanLocations.xml"
})
public class OpenPolicyDaoImplTest {

    @Autowired
    OpenPolicyDao openPolicyDao;

    @Test
    public void testQueryByCriteria() {
        //ABC-345345534
        List<OpenPolicy> list = openPolicyDao.findByCriteria(new OpenPolicyCriteria(null, null));
        System.out.println("list : "+list);
        Assert.assertTrue(true);
    }

}
