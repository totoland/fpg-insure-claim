/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.totoland.db.authen.dao.hibernate;

import com.totoland.db.authen.dao.AuthenDao;
import com.totoland.db.dao.BaseDao;
import com.totoland.db.entity.ViewUser;
import java.io.Serializable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author totoland
 */
@Repository("authenDao")
public class AuthenDaoImpl extends BaseDao implements AuthenDao, Serializable {

    private static final long serialVersionUID = -4586932513662487847L;

    @Transactional(readOnly = true)
    @Override
    public ViewUser loginUser(String userName, String passWord) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ");
        sql.append("sv_user.USER_ID, ");
        sql.append("sv_user.USERNAME, ");
        sql.append("sv_user.PASSWORD, ");
        sql.append("sv_user.IS_ACTIVE, ");
        sql.append("sv_user.FNAME, ");
        sql.append("sv_user.LNAME, ");
        sql.append("sv_user.SEX, ");
        sql.append("user_group.USER_GROUP_NAME, ");
        sql.append("user_group.USER_GROUP_ID, ");
        sql.append("sv_user.USER_GROUP_LVL, ");
        sql.append("sv_user.ADDRESS, ");
        sql.append("sv_user.COMPANY_NAME, ");
        sql.append("sv_user.POLICY_NO, ");
        sql.append("group_lvl.GROUP_LVL_NAME, ");
        sql.append("sv_user.POLICY_NO, ");
        sql.append("sv_user.BRANCH_DESC, ");
        sql.append("sv_user.TAX_ID, ");
        sql.append("sv_user.COMPANY_TYPE ");
        sql.append("FROM ");
        sql.append("sv_user ");
        sql.append("INNER JOIN user_group ON sv_user.USER_GROUP_ID = user_group.USER_GROUP_ID ");
        sql.append("INNER JOIN group_lvl ON sv_user.USER_GROUP_LVL = group_lvl.GROUP_LVL_ID ");
        sql.append("where sv_user.USERNAME = ? and sv_user.PASSWORD = ?");

        return (ViewUser) findUniqNativeQuery(sql.toString(), ViewUser.class, userName, passWord);

    }

    @Transactional(readOnly = true)
    @Override
    public ViewUser findByUserId(Integer userId) {
        
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ");
        sql.append("sv_user.USER_ID, ");
        sql.append("sv_user.USERNAME, ");
        sql.append("sv_user.PASSWORD, ");
        sql.append("sv_user.IS_ACTIVE, ");
        sql.append("sv_user.FNAME, ");
        sql.append("sv_user.LNAME, ");
        sql.append("sv_user.SEX, ");
        sql.append("user_group.USER_GROUP_NAME, ");
        sql.append("user_group.USER_GROUP_ID, ");
        sql.append("sv_user.USER_GROUP_LVL, ");
        sql.append("sv_user.ADDRESS, ");
        sql.append("sv_user.COMPANY_NAME, ");
        sql.append("sv_user.POLICY_NO, ");
        sql.append("group_lvl.GROUP_LVL_NAME, ");
        sql.append("sv_user.POLICY_NO, ");
        sql.append("sv_user.BRANCH_DESC, ");
        sql.append("sv_user.TAX_ID, ");
        sql.append("sv_user.COMPANY_TYPE ");
        sql.append("FROM ");
        sql.append("sv_user ");
        sql.append("INNER JOIN user_group ON sv_user.USER_GROUP_ID = user_group.USER_GROUP_ID ");
        sql.append("INNER JOIN group_lvl ON sv_user.USER_GROUP_LVL = group_lvl.GROUP_LVL_ID ");
        sql.append("WHERE 1=1 ");
        sql.append("AND sv_user.USER_ID = ? ");

        return (ViewUser) findUniqNativeQuery(sql.toString(), ViewUser.class, userId);
    }
}
