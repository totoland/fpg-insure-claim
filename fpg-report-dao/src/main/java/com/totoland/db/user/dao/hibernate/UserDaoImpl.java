/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.totoland.db.user.dao.hibernate;

import com.totoland.db.bean.UserCriteria;
import com.totoland.db.common.dao.hibernate.GennericDaoImpl;
import com.totoland.db.entity.SvUser;
import com.totoland.db.entity.ViewUser;
import com.totoland.db.enums.UserType;
import com.totoland.db.user.dao.UserDao;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author totoland
 */
@Repository("userDao")
public class UserDaoImpl extends GennericDaoImpl<SvUser> implements UserDao {

    private static final long serialVersionUID = 443080361867434758L;

    @Transactional(readOnly = true)
    @Override
    public List<ViewUser> searchByUserCriteria(UserCriteria criteria) {
        List<Object> lst = new ArrayList<>();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT group_lvl.GROUP_LVL_NAME,user_group.USER_GROUP_NAME,sv_user.USER_ID,sv_user.USERNAME,sv_user.PASSWORD,");
        sql.append("sv_user.IS_ACTIVE,sv_user.FNAME,sv_user.LNAME,sv_user.SEX,sv_user.USER_GROUP_ID,sv_user.USER_GROUP_LVL , sv_user.ADDRESS , sv_user.COMPANY_NAME ,sv_user.POLICY_NO, ");
        sql.append("sv_user.COMPANY_TYPE,sv_user.BRANCH_DESC,sv_user.TAX_ID ");
        sql.append("FROM sv_user INNER JOIN group_lvl ON sv_user.USER_GROUP_LVL = group_lvl.GROUP_LVL_ID INNER JOIN user_group ON sv_user.USER_GROUP_ID = user_group.USER_GROUP_ID ");
        sql.append("WHERE 1=1 ");

        if (criteria.getUserId() != null && !criteria.getUserId().trim().isEmpty()) {
            sql.append("AND sv_user.USER_ID = ? ");
            lst.add(criteria.getUserId());
        }

        if (criteria.getInsuredName() != null && !criteria.getInsuredName().trim().isEmpty()) {
            sql.append("AND sv_user.COMPANY_NAME = ? ");
            lst.add(criteria.getInsuredName());
        }

        if (criteria.getGroupId() != null && criteria.getGroupId() != -1) {
            sql.append("AND sv_user.USER_GROUP_ID = ? ");
            lst.add(criteria.getGroupId());
        }

        if (criteria.getUserName() != null && !criteria.getUserName().trim().isEmpty()) {
            sql.append("AND sv_user.FNAME LIKE ? OR sv_user.LNAME LIKE ? ");
            lst.add("%" + criteria.getUserName() + "%");
            lst.add("%" + criteria.getUserName() + "%");
        }

        if (criteria.getGroupLvl() != null && criteria.getGroupLvl() != -1) {
            sql.append("AND sv_user.USER_GROUP_LVL = ? ");
            lst.add(criteria.getGroupLvl());
        } else {
            sql.append("AND sv_user.USER_GROUP_LVL <> 3 ");
        }

        return findNativeQuery(sql.toString(), ViewUser.class, lst);
    }

    @Transactional(readOnly = true)
    @Override
    public ViewUser searchByUserName(String criteria) {

         StringBuilder sql = new StringBuilder();
        sql.append("SELECT group_lvl.GROUP_LVL_NAME,user_group.USER_GROUP_NAME,sv_user.USER_ID,sv_user.USERNAME,sv_user.PASSWORD,");
        sql.append("sv_user.IS_ACTIVE,sv_user.FNAME,sv_user.LNAME,sv_user.SEX,sv_user.USER_GROUP_ID,sv_user.USER_GROUP_LVL , sv_user.ADDRESS , sv_user.COMPANY_NAME ,sv_user.POLICY_NO, ");
        sql.append("sv_user.COMPANY_TYPE,sv_user.BRANCH_DESC,sv_user.TAX_ID ");
        sql.append("FROM sv_user INNER JOIN group_lvl ON sv_user.USER_GROUP_LVL = group_lvl.GROUP_LVL_ID INNER JOIN user_group ON sv_user.USER_GROUP_ID = user_group.USER_GROUP_ID ");
        sql.append("WHERE 1=1 ");

        if (criteria != null && !criteria.trim().isEmpty()) {
            sql.append("AND sv_user.USERNAME = ? ");
        } else {
            return null;
        }

        return (ViewUser) findUniqNativeQuery(sql.toString(), ViewUser.class, criteria);

    }

    @Transactional(readOnly = true)
    @Override
    public long countUser(UserType userType) {
        BigInteger count = (BigInteger) countNativeQuery("SELECT COUNT(1) FROM SV_USER WHERE USER_GROUP_LVL = ?", userType.getId());

        if (count == null) {
            return 0L;
        }

        return count.longValue();
    }

    ;
    
    
    @Transactional(readOnly = true)
    @Override
    public SvUser findById(Long id) {
        return (SvUser) findUniqNativeQuery("SELECT * FROM SV_USER WHERE USER_ID = ?", SvUser.class, id);
    }
}
