/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.totoland.db.user.dao;

import com.totoland.db.bean.UserCriteria;
import com.totoland.db.entity.ViewUser;
import java.util.List;

/**
 *
 * @author totoland
 */
public interface UserDao {
    List<ViewUser>searchByUserCriteria(UserCriteria criteria);

    ViewUser searchByUserName(String criteria);
}
