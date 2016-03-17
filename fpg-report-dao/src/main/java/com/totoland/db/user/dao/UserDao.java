/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.totoland.db.user.dao;

import com.totoland.db.bean.UserCriteria;
import com.totoland.db.common.dao.GennericDao;
import com.totoland.db.entity.SvUser;
import com.totoland.db.entity.ViewUser;
import com.totoland.db.enums.UserType;
import java.util.List;

/**
 *
 * @author totoland
 */
public interface UserDao extends GennericDao<SvUser>{
    List<ViewUser>searchByUserCriteria(UserCriteria criteria);

    ViewUser searchByUserName(String criteria);
    
    long countUser(UserType userType);
}
