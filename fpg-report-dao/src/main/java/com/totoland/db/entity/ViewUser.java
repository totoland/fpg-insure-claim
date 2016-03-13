/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.totoland.db.entity;

import com.totoland.db.domain.entity.DomainEntity;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author totoland
 */
@Entity
public class ViewUser extends DomainEntity{
    private static final long serialVersionUID = 3183600610528921979L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "USER_ID")
    private Integer userId;
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "IS_ACTIVE")
    private Boolean isActive;
    @Column(name = "FNAME")
    private String fname;
    @Column(name = "LNAME")
    private String lname;
    @Column(name = "SEX")
    private Integer sex;
    @Column(name = "USER_GROUP_ID")
    private Integer userGroupId;
    @Column(name ="USER_GROUP_NAME")
    private String userGroupName;
    @Column(name ="USER_GROUP_LVL")
    private Integer userGroupLvl;
    @Column(name ="GROUP_LVL_NAME")
    private String userGroupLvlName;
    
    /**
     * @return the userId
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the isActive
     */
    public Boolean getIsActive() {
        return isActive;
    }

    /**
     * @param isActive the isActive to set
     */
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    /**
     * @return the fname
     */
    public String getFname() {
        return fname;
    }

    /**
     * @param fname the fname to set
     */
    public void setFname(String fname) {
        this.fname = fname;
    }

    /**
     * @return the lname
     */
    public String getLname() {
        return lname;
    }

    /**
     * @param lname the lname to set
     */
    public void setLname(String lname) {
        this.lname = lname;
    }

    /**
     * @return the sex
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * @param sex the sex to set
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * @return the userGroupId
     */
    public Integer getUserGroupId() {
        return userGroupId;
    }

    /**
     * @param userGroupId the userGroupId to set
     */
    public void setUserGroupId(Integer userGroupId) {
        this.userGroupId = userGroupId;
    }

    /**
     * @return the userGroupName
     */
    public String getUserGroupName() {
        return userGroupName;
    }

    /**
     * @param userGroupName the userGroupName to set
     */
    public void setUserGroupName(String userGroupName) {
        this.userGroupName = userGroupName;
    }

    /**
     * @return the userGroupLvl
     */
    public Integer getUserGroupLvl() {
        return userGroupLvl;
    }

    /**
     * @param userGroupLvl the userGroupLvl to set
     */
    public void setUserGroupLvl(Integer userGroupLvl) {
        this.userGroupLvl = userGroupLvl;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + (this.userId != null ? this.userId.hashCode() : 0);
        hash = 79 * hash + (this.username != null ? this.username.hashCode() : 0);
        hash = 79 * hash + (this.password != null ? this.password.hashCode() : 0);
        hash = 79 * hash + (this.isActive != null ? this.isActive.hashCode() : 0);
        hash = 79 * hash + (this.fname != null ? this.fname.hashCode() : 0);
        hash = 79 * hash + (this.lname != null ? this.lname.hashCode() : 0);
        hash = 79 * hash + (this.sex != null ? this.sex.hashCode() : 0);
        hash = 79 * hash + (this.userGroupId != null ? this.userGroupId.hashCode() : 0);
        hash = 79 * hash + (this.userGroupName != null ? this.userGroupName.hashCode() : 0);
        hash = 79 * hash + (this.userGroupLvl != null ? this.userGroupLvl.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ViewUser other = (ViewUser) obj;
        if (this.userId != other.userId && (this.userId == null || !this.userId.equals(other.userId))) {
            return false;
        }
        if ((this.username == null) ? (other.username != null) : !this.username.equals(other.username)) {
            return false;
        }
        if ((this.password == null) ? (other.password != null) : !this.password.equals(other.password)) {
            return false;
        }
        if (this.isActive != other.isActive && (this.isActive == null || !this.isActive.equals(other.isActive))) {
            return false;
        }
        if ((this.fname == null) ? (other.fname != null) : !this.fname.equals(other.fname)) {
            return false;
        }
        if ((this.lname == null) ? (other.lname != null) : !this.lname.equals(other.lname)) {
            return false;
        }
        if (this.sex != other.sex && (this.sex == null || !this.sex.equals(other.sex))) {
            return false;
        }
        if (this.userGroupId != other.userGroupId && (this.userGroupId == null || !this.userGroupId.equals(other.userGroupId))) {
            return false;
        }
        if ((this.userGroupName == null) ? (other.userGroupName != null) : !this.userGroupName.equals(other.userGroupName)) {
            return false;
        }
        if (this.userGroupLvl != other.userGroupLvl && (this.userGroupLvl == null || !this.userGroupLvl.equals(other.userGroupLvl))) {
            return false;
        }
        return true;
    }

    /**
     * @return the userGroupLvlName
     */
    public String getUserGroupLvlName() {
        return userGroupLvlName;
    }

    /**
     * @param userGroupLvlName the userGroupLvlName to set
     */
    public void setUserGroupLvlName(String userGroupLvlName) {
        this.userGroupLvlName = userGroupLvlName;
    }

    @Override
    public String toString() {
        return "ViewUser{" + "userId=" + userId + ", username=" + username + ", isActive=" + isActive + ", fname=" + fname + ", lname=" + lname + ", sex=" + sex + ", userGroupId=" + userGroupId + ", userGroupName=" + userGroupName + ", userGroupLvl=" + userGroupLvl + ", userGroupLvlName=" + userGroupLvlName + '}';
    }
    
}
