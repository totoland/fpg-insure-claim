/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.totoland.db.user.dao.hibernate;

import com.totoland.db.bean.UserCriteria;
import com.totoland.db.dao.BaseDao;
import com.totoland.db.entity.ViewUser;
import com.totoland.db.user.dao.UserDao;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author totoland
 */
@Repository("userDao")
public class UserDaoImpl extends BaseDao implements UserDao {

    @Override
    public List<ViewUser> searchByUserCriteria(UserCriteria criteria) {
        List<Object> lst = new ArrayList<Object>();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT group_lvl.GROUP_LVL_NAME,user_group.USER_GROUP_NAME,sv_user.USER_ID,sv_user.USERNAME,sv_user.PASSWORD,");
        sql.append("sv_user.IS_ACTIVE,sv_user.FNAME,sv_user.LNAME,sv_user.SEX,sv_user.USER_GROUP_ID,sv_user.USER_GROUP_LVL ");
        sql.append("FROM sv_user INNER JOIN group_lvl ON sv_user.USER_GROUP_LVL = group_lvl.GROUP_LVL_ID INNER JOIN user_group ON sv_user.USER_GROUP_ID = user_group.USER_GROUP_ID ");
        sql.append("WHERE 1=1 ");
        
        if (criteria.getGroupId() != null && criteria.getGroupId() != -1) {
            sql.append("AND sv_user.USER_GROUP_ID = ? ");
            lst.add(criteria.getGroupId());
        }

        if (criteria.getUserName() != null && !criteria.getUserName().trim().isEmpty()) {
            sql.append("AND sv_user.FNAME LIKE ? OR sv_user.LNAME LIKE ? ");
            lst.add("%"+criteria.getUserName()+"%");
            lst.add("%"+criteria.getUserName()+"%");
        }

        if (criteria.getGroupLvl() != null && criteria.getGroupLvl() != -1) {
            sql.append("AND sv_user.USER_GROUP_LVL = ? ");
            lst.add(criteria.getGroupLvl());
        }

        return findNativeQuery(sql.toString(), ViewUser.class, lst);
    }

    @Override
    public ViewUser searchByUserName(String criteria) {

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT group_lvl.GROUP_LVL_NAME,user_group.USER_GROUP_NAME,sv_user.USER_ID,sv_user.USERNAME,sv_user.PASSWORD,");
        sql.append("sv_user.IS_ACTIVE,sv_user.FNAME,sv_user.LNAME,sv_user.SEX,sv_user.USER_GROUP_ID,sv_user.USER_GROUP_LVL ");
        sql.append("FROM sv_user INNER JOIN group_lvl ON sv_user.USER_GROUP_LVL = group_lvl.GROUP_LVL_ID INNER JOIN user_group ON sv_user.USER_GROUP_ID = user_group.USER_GROUP_ID ");
        sql.append("WHERE 1=1 ");
        
        if (criteria != null && !criteria.trim().isEmpty()) {
            sql.append("AND sv_user.USERNAME = ? ");
        }else{
            return null;
        }
        
        return (ViewUser)findUniqNativeQuery(sql.toString(), ViewUser.class, criteria);
        
    }
}
