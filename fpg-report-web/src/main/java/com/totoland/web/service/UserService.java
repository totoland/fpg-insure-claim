/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.totoland.web.service;

import com.totoland.db.bean.UserCriteria;
import com.totoland.db.entity.KeyMatch;
import com.totoland.db.entity.SvUser;
import com.totoland.db.entity.ViewUser;
import java.util.List;

/**
 *
 * @author totoland
 */
public interface UserService extends GennericService<SvUser>{
    ViewUser findByUserId(Integer userId);
    SvUser findById(Long userId);
    List<ViewUser> findByUserCriteria(UserCriteria userName);
    ViewUser findByUserName(String criteria);
    long countAllUser();
    long countAllAdmin();
    void createWithKeyMatching(SvUser user,KeyMatch keyMatch);
    void updateWithKeyMatching(SvUser user,KeyMatch keyMatch);
}
