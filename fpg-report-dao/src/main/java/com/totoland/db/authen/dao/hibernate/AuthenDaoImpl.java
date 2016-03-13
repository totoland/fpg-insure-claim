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

/**
 *
 * @author totoland
 */
@Repository("authenDao")
public class AuthenDaoImpl extends BaseDao implements AuthenDao, Serializable {

    private static final long serialVersionUID = -4586932513662487847L;

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
        sql.append("group_lvl.GROUP_LVL_NAME ");
        sql.append("FROM ");
        sql.append("sv_user ");
        sql.append("INNER JOIN user_group ON sv_user.USER_GROUP_ID = user_group.USER_GROUP_ID ");
        sql.append("INNER JOIN group_lvl ON sv_user.USER_GROUP_LVL = group_lvl.GROUP_LVL_ID ");
        sql.append("where sv_user.USERNAME = ? and sv_user.PASSWORD = ?");

        return (ViewUser) findUniqNativeQuery(sql.toString(), ViewUser.class, userName, passWord);

    }

    @Override
    public ViewUser findByUserId(Integer userId) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT     ECT_USER.USER_ID, ECT_USER.USERNAME, ECT_USER.PASSWORD, ECT_USER.IS_ACTIVE, ECT_USER.FNAME, ECT_USER.LNAME, ECT_USER.SEX,  ");
        sql.append("                      ECT_USER.PROVINCE_ID, ECT_USER.USER_GROUP_ID, ECT_USER_GROUP.USER_GROUP_NAME, ECT_USER.USER_GROUP_LVL,  ");
        sql.append("                      ECT_PROVINCE.PROVINCE_NAME, ECT_GROUP_LVL.GROUP_LVL_NAME ");
        sql.append("FROM         ECT_USER INNER JOIN ");
        sql.append("                      ECT_USER_GROUP ON ECT_USER.USER_GROUP_ID = ECT_USER_GROUP.USER_GROUP_ID INNER JOIN ");
        sql.append("                      ECT_GROUP_LVL ON ECT_USER.USER_GROUP_LVL = ECT_GROUP_LVL.GROUP_LVL_ID LEFT OUTER JOIN ");
        sql.append("                      ECT_PROVINCE ON ECT_USER.PROVINCE_ID = ECT_PROVINCE.PROVINCE_ID");
        sql.append("                              WHERE ECT_USER.USER_ID = ?");

        return (ViewUser) findUniqNativeQuery(sql.toString(), ViewUser.class, userId);
    }
}
