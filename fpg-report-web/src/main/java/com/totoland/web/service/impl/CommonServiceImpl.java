/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.totoland.web.service.impl;

import com.totoland.db.common.dao.CommonDao;
import com.totoland.db.common.entity.DropDownList;
import com.totoland.web.service.CommonService;
import java.io.Serializable;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Totoland
 */
@Service("commonService")
public class CommonServiceImpl implements CommonService, Serializable{
    private static final long serialVersionUID = 3016372310313613240L;
    @Autowired
    private CommonDao commonDao;
    
    private static Logger logger = LoggerFactory.getLogger(CommonServiceImpl.class);
    
    @Override
    public List<DropDownList> getDropdownList(DropDownList dropDownList) {
        return commonDao.getDropdownList(dropDownList);
    }

    /**
     * @return the commonDao
     */
    public CommonDao getCommonDao() {
        return commonDao;
    }

    /**
     * @param commonDao the commonDao to set
     */
    public void setCommonDao(CommonDao commonDao) {
        this.commonDao = commonDao;
    }

}
