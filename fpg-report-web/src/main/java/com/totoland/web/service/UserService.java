/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.totoland.web.service;

import com.totoland.db.bean.UserCriteria;
import com.totoland.db.entity.ViewUser;
import java.util.List;

/**
 *
 * @author totoland
 */
public interface UserService {
    ViewUser findByUserId(Integer userId);
    List<ViewUser> findByUserCriteria(UserCriteria userName);
    ViewUser findByUserName(String criteria);
}
