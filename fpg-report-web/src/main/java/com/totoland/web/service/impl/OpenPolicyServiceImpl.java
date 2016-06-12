/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.totoland.web.service.impl;

import com.totoland.db.bean.OpenPolicyCriteria;
import com.totoland.db.entity.OpenPolicy;
import com.totoland.db.openpolicy.dao.OpenPolicyDao;
import com.totoland.web.service.OpenPolicyService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author thanapong.n
 */
@Service(value = "openPolicyService")
public class OpenPolicyServiceImpl extends GennericServiceImpl<OpenPolicy> implements OpenPolicyService{

    @Autowired
    OpenPolicyDao openPolicyDao;
    
    @Override
    public List<OpenPolicy> findByCriteria(OpenPolicyCriteria criteria) {
        return openPolicyDao.findByCriteria(criteria);
    }
    
    
}
