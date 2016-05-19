/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.totoland.web.service.impl;

import com.totoland.db.authen.dao.AuthenDao;
import com.totoland.db.bean.UserCriteria;
import com.totoland.db.entity.KeyMatch;
import com.totoland.db.entity.SvUser;
import com.totoland.db.entity.ViewUser;
import com.totoland.db.enums.UserType;
import com.totoland.db.keymatch.dao.KeyMatchDao;
import com.totoland.db.user.dao.UserDao;
import com.totoland.web.service.UserService;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author totoland
 */
@Service("userService")
public class UserServiceImpl extends GennericServiceImpl<SvUser> implements UserService, Serializable {

    private static final long serialVersionUID = 2970904373229939989L;

    @Autowired
    AuthenDao authenDao;
    @Autowired
    UserDao userDao;
    @Autowired
    KeyMatchDao keyMatchDao;

    @Override
    public ViewUser findByUserId(Integer userId) {
        return authenDao.findByUserId(userId);
    }

    @Override
    public List<ViewUser> findByUserCriteria(UserCriteria userName) {
        return userDao.searchByUserCriteria(userName);
    }

    @Override
    public ViewUser findByUserName(String criteria) {
        return userDao.searchByUserName(criteria);
    }

    @Override
    public long countAllUser() {
        return userDao.countUser(UserType.OFFICIAL_USER);
    }

    @Override
    public long countAllAdmin() {
        return userDao.countUser(UserType.ADMIN);
    }

    @Transactional(rollbackFor = {Throwable.class})
    @Override
    public void createWithKeyMatching(SvUser user, KeyMatch keyMatch) {
        userDao.create(user);
        keyMatch.setCustId(user.getUserId());
        keyMatch.setOpenPolicyNo(user.getPolicyNo());
        keyMatchDao.updateKeyMatch(keyMatch);
    }

    @Transactional(rollbackFor = {Throwable.class})
    @Override
    public void updateWithKeyMatching(SvUser user, KeyMatch keyMatch) {
        userDao.edit(user);
        keyMatch.setCustId(user.getUserId());
        keyMatch.setOpenPolicyNo(user.getPolicyNo());
        keyMatchDao.updateKeyMatch(keyMatch);
    }
    
    @Override
    public SvUser findById(Long userId){
        return userDao.findById(userId);
    }
    
}
