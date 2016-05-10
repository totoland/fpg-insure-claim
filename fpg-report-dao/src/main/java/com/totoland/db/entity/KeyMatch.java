/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.totoland.db.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author totoland
 */
@Entity
@Table(name = "key_match")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "KeyMatch.findAll", query = "SELECT k FROM KeyMatch k")})
public class KeyMatch implements Serializable {

    private static final long serialVersionUID = 154353543548L;
    @Id
    @Basic(optional = false)
    @Column(name = "CUST_ID")
    private int custId;
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "KEY_MATCH_ID")
    private Integer keyMatchId = 0;
    @Column(name = "BROKER_ID")
    private Integer brokerId;
    @Column(name = "OPEN_POLICY_NO")
    private String openPolicyNo;
    @Column(name = "CERTIFICATE_NO")
    private String certificateNo;
    @Column(name = "CLAIM_NO")
    private String claimNo;
    @Column(name = "CREATED_BY")
    private Integer createdBy;
    @Column(name = "CREATED_DATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDateTime;
    @Column(name = "UPDATED_BY")
    private Integer updatedBy;
    @Column(name = "UPDATED_DATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDateTime;

    public KeyMatch() {
    }

    public KeyMatch(Integer keyMatchId) {
        this.keyMatchId = keyMatchId;
    }

    public KeyMatch(Integer keyMatchId, int custId) {
        this.keyMatchId = keyMatchId;
        this.custId = custId;
    }

    public Integer getKeyMatchId() {
        return keyMatchId;
    }

    public void setKeyMatchId(Integer keyMatchId) {
        this.keyMatchId = keyMatchId;
    }

    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }

    public Integer getBrokerId() {
        return brokerId;
    }

    public void setBrokerId(Integer brokerId) {
        this.brokerId = brokerId;
    }

    public String getOpenPolicyNo() {
        return openPolicyNo;
    }

    public void setOpenPolicyNo(String openPolicyNo) {
        this.openPolicyNo = openPolicyNo;
    }

    public String getCertificateNo() {
        return certificateNo;
    }

    public void setCertificateNo(String certificateNo) {
        this.certificateNo = certificateNo;
    }

    public String getClaimNo() {
        return claimNo;
    }

    public void setClaimNo(String claimNo) {
        this.claimNo = claimNo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (keyMatchId != null ? keyMatchId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof KeyMatch)) {
            return false;
        }
        KeyMatch other = (KeyMatch) object;
        if ((this.keyMatchId == null && other.keyMatchId != null) || (this.keyMatchId != null && !this.keyMatchId.equals(other.keyMatchId))) {
            return false;
        }
        return true;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(Date createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public Integer getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedDateTime() {
        return updatedDateTime;
    }

    public void setUpdatedDateTime(Date updatedDateTime) {
        this.updatedDateTime = updatedDateTime;
    }

    @Override
    public String toString() {
        return "KeyMatch{" + "keyMatchId=" + keyMatchId + ", custId=" + custId + ", brokerId=" + brokerId + ", openPolicyNo=" + openPolicyNo + ", certificateNo=" + certificateNo + ", claimNo=" + claimNo + ", createdBy=" + createdBy + ", createdDateTime=" + createdDateTime + ", updatedBy=" + updatedBy + ", updatedDateTime=" + updatedDateTime + '}';
    }
}
