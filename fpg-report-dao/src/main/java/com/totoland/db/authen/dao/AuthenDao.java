/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.totoland.db.authen.dao;

import com.totoland.db.entity.ViewUser;


/**
 *
 * @author totoland
 */
public interface AuthenDao {
    
    ViewUser loginUser(String userName,String passWord);

    ViewUser findByUserId(Integer userId);
    
}
