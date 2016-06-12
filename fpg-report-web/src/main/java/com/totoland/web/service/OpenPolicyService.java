/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.totoland.web.service;

import com.totoland.db.bean.OpenPolicyCriteria;
import com.totoland.db.entity.OpenPolicy;
import java.util.List;

/**
 *
 * @author thanapong.n
 */
public interface OpenPolicyService extends GennericService<OpenPolicy>{

    List<OpenPolicy> findByCriteria(OpenPolicyCriteria criteria);
}
