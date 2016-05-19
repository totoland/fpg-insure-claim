/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.totoland.db.bean;

import java.io.Serializable;

/**
 *
 * @author totoland
 */
public class UserCriteria implements Serializable {

    private static final long serialVersionUID = 7352292656993154981L;

    private String userId;
    private String userName;
    private Integer groupId;
    private Integer groupLvl;
    private String insuredName;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the groupId
     */
    public Integer getGroupId() {
        return groupId;
    }

    /**
     * @param groupId the groupId to set
     */
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    /**
     * @return the groupLvl
     */
    public Integer getGroupLvl() {
        return groupLvl;
    }

    /**
     * @param groupLvl the groupLvl to set
     */
    public void setGroupLvl(Integer groupLvl) {
        this.groupLvl = groupLvl;
    }

    public String getInsuredName() {
        return insuredName;
    }

    public void setInsuredName(String insuredName) {
        this.insuredName = insuredName;
    }

    @Override
    public String toString() {
        return "UserCriteria{" + "userId=" + userId + ", userName=" + userName + ", groupId=" + groupId + ", groupLvl=" + groupLvl + ", insuredName=" + insuredName + '}';
    }
}
