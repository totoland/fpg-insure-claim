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
public class UserCriteria implements Serializable{
    private static final long serialVersionUID = 7352292656993154981L;
    
    private String userName;
    private Integer groupId;
    private Integer groupLvl;

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

    @Override
    public String toString() {
        return "UserCriteria{" + "userName=" + userName + ", groupId=" + groupId + ", groupLvl=" + groupLvl + '}';
    }
}
