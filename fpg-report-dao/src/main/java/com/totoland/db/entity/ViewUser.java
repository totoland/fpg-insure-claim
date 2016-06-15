/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.totoland.db.entity;

import com.totoland.db.domain.entity.DomainEntity;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 *
 * @author totoland
 */
@Entity
public class ViewUser extends DomainEntity {

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
    @Column(name = "USER_GROUP_NAME")
    private String userGroupName;
    @Column(name = "USER_GROUP_LVL")
    private Integer userGroupLvl;
    @Column(name = "GROUP_LVL_NAME")
    private String userGroupLvlName;
    @Column(name = "COMPANY_NAME")
    private String companyName;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "POLICY_NO")
    private String policyNo;
    @Column(name = "COMPANY_TYPE")
    private String companyType;
    @Column(name = "BRANCH_DESC")
    private String branchDesc;
    @Column(name = "TAX_ID")
    private String taxId;
    @Transient
    private String sessionId;

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

    /**
     * @return the sessionId
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * @param sessionId the sessionId to set
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPolicyNo() {
        return policyNo;
    }

    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getBranchDesc() {
        return branchDesc;
    }

    public void setBranchDesc(String branchDesc) {
        this.branchDesc = branchDesc;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.userId);
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
        if (!Objects.equals(this.userId, other.userId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ViewUser{" + "userId=" + userId + ", username=" + username + ", password=" + password + ", isActive=" + isActive + ", fname=" + fname + ", lname=" + lname + ", sex=" + sex + ", userGroupId=" + userGroupId + ", userGroupName=" + userGroupName + ", userGroupLvl=" + userGroupLvl + ", userGroupLvlName=" + userGroupLvlName + ", companyName=" + companyName + ", address=" + address + ", policyNo=" + policyNo + ", companyType=" + companyType + ", branchDesc=" + branchDesc + ", taxId=" + taxId + ", sessionId=" + sessionId + '}';
    }
}
