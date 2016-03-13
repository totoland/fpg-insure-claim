/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.totoland.test.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Totoland
 */
@Entity
@Table(catalog = "thaihua", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contract.findAll", query = "SELECT c FROM Contract c"),
    @NamedQuery(name = "Contract.findByContractID", query = "SELECT c FROM Contract c WHERE c.contractID = :contractID"),
    @NamedQuery(name = "Contract.findByContractNo", query = "SELECT c FROM Contract c WHERE c.contractNo = :contractNo"),
    @NamedQuery(name = "Contract.findBySpID", query = "SELECT c FROM Contract c WHERE c.spID = :spID"),
    @NamedQuery(name = "Contract.findByApproveDateTime", query = "SELECT c FROM Contract c WHERE c.approveDateTime = :approveDateTime"),
    @NamedQuery(name = "Contract.findByApproveBy", query = "SELECT c FROM Contract c WHERE c.approveBy = :approveBy"),
    @NamedQuery(name = "Contract.findByPayType", query = "SELECT c FROM Contract c WHERE c.payType = :payType"),
    @NamedQuery(name = "Contract.findByPayTerm", query = "SELECT c FROM Contract c WHERE c.payTerm = :payTerm"),
    @NamedQuery(name = "Contract.findByProductID", query = "SELECT c FROM Contract c WHERE c.productID = :productID"),
    @NamedQuery(name = "Contract.findByWeightNet", query = "SELECT c FROM Contract c WHERE c.weightNet = :weightNet"),
    @NamedQuery(name = "Contract.findByPriceStandard", query = "SELECT c FROM Contract c WHERE c.priceStandard = :priceStandard"),
    @NamedQuery(name = "Contract.findByPriceAdj", query = "SELECT c FROM Contract c WHERE c.priceAdj = :priceAdj"),
    @NamedQuery(name = "Contract.findByPriceCn", query = "SELECT c FROM Contract c WHERE c.priceCn = :priceCn"),
    @NamedQuery(name = "Contract.findByContractDate", query = "SELECT c FROM Contract c WHERE c.contractDate = :contractDate"),
    @NamedQuery(name = "Contract.findByDueDate", query = "SELECT c FROM Contract c WHERE c.dueDate = :dueDate"),
    @NamedQuery(name = "Contract.findByStatus", query = "SELECT c FROM Contract c WHERE c.status = :status"),
    @NamedQuery(name = "Contract.findByRejectReason", query = "SELECT c FROM Contract c WHERE c.rejectReason = :rejectReason"),
    @NamedQuery(name = "Contract.findByRemarkCn", query = "SELECT c FROM Contract c WHERE c.remarkCn = :remarkCn"),
    @NamedQuery(name = "Contract.findByRemarkInternal", query = "SELECT c FROM Contract c WHERE c.remarkInternal = :remarkInternal"),
    @NamedQuery(name = "Contract.findByCreateDateTime", query = "SELECT c FROM Contract c WHERE c.createDateTime = :createDateTime"),
    @NamedQuery(name = "Contract.findByCreateBy", query = "SELECT c FROM Contract c WHERE c.createBy = :createBy"),
    @NamedQuery(name = "Contract.findByUpdateDateTime", query = "SELECT c FROM Contract c WHERE c.updateDateTime = :updateDateTime"),
    @NamedQuery(name = "Contract.findByUpdateBy", query = "SELECT c FROM Contract c WHERE c.updateBy = :updateBy"),
    @NamedQuery(name = "Contract.findByWeightContract", query = "SELECT c FROM Contract c WHERE c.weightContract = :weightContract"),
    @NamedQuery(name = "Contract.findByDrc", query = "SELECT c FROM Contract c WHERE c.drc = :drc")})
public class Contract implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer contractID;
    @Column(length = 10)
    private String contractNo;
    private Integer spID;
    @Temporal(TemporalType.TIMESTAMP)
    private Date approveDateTime;
    private Integer approveBy;
    private Integer payType;
    private Integer payTerm;
    private Integer productID;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(precision = 18, scale = 3)
    private BigDecimal weightNet;
    @Column(precision = 18, scale = 3)
    private BigDecimal priceStandard;
    @Column(precision = 18, scale = 3)
    private BigDecimal priceAdj;
    @Column(precision = 18, scale = 3)
    private BigDecimal priceCn;
    @Temporal(TemporalType.TIMESTAMP)
    private Date contractDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dueDate;
    private Integer status;
    @Column(length = 2147483647)
    private String rejectReason;
    @Column(length = 2147483647)
    private String remarkCn;
    @Basic(optional = false)
    @Column(nullable = false, length = 2147483647)
    private String remarkInternal;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDateTime;
    private Integer createBy;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDateTime;
    private Integer updateBy;
    @Column(precision = 18, scale = 3)
    private BigDecimal weightContract;
    @Column(precision = 18, scale = 3)
    private BigDecimal drc;

    public Contract() {
    }

    public Contract(Integer contractID) {
        this.contractID = contractID;
    }

    public Contract(Integer contractID, String remarkInternal) {
        this.contractID = contractID;
        this.remarkInternal = remarkInternal;
    }

    public Integer gectontractID() {
        return contractID;
    }

    public void sectontractID(Integer contractID) {
        this.contractID = contractID;
    }

    public String gectontractNo() {
        return contractNo;
    }

    public void sectontractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public Integer getSpID() {
        return spID;
    }

    public void setSpID(Integer spID) {
        this.spID = spID;
    }

    public Date getApproveDateTime() {
        return approveDateTime;
    }

    public void setApproveDateTime(Date approveDateTime) {
        this.approveDateTime = approveDateTime;
    }

    public Integer getApproveBy() {
        return approveBy;
    }

    public void setApproveBy(Integer approveBy) {
        this.approveBy = approveBy;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Integer getPayTerm() {
        return payTerm;
    }

    public void setPayTerm(Integer payTerm) {
        this.payTerm = payTerm;
    }

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public BigDecimal getWeightNet() {
        return weightNet;
    }

    public void setWeightNet(BigDecimal weightNet) {
        this.weightNet = weightNet;
    }

    public BigDecimal getPriceStandard() {
        return priceStandard;
    }

    public void setPriceStandard(BigDecimal priceStandard) {
        this.priceStandard = priceStandard;
    }

    public BigDecimal getPriceAdj() {
        return priceAdj;
    }

    public void setPriceAdj(BigDecimal priceAdj) {
        this.priceAdj = priceAdj;
    }

    public BigDecimal getPriceCn() {
        return priceCn;
    }

    public void setPriceCn(BigDecimal priceCn) {
        this.priceCn = priceCn;
    }

    public Date gectontractDate() {
        return contractDate;
    }

    public void sectontractDate(Date contractDate) {
        this.contractDate = contractDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    public String getRemarkCn() {
        return remarkCn;
    }

    public void setRemarkCn(String remarkCn) {
        this.remarkCn = remarkCn;
    }

    public String getRemarkInternal() {
        return remarkInternal;
    }

    public void setRemarkInternal(String remarkInternal) {
        this.remarkInternal = remarkInternal;
    }

    public Date gectreateDateTime() {
        return createDateTime;
    }

    public void sectreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }

    public Integer gectreateBy() {
        return createBy;
    }

    public void sectreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(Date updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public BigDecimal getWeightContract() {
        return weightContract;
    }

    public void setWeightContract(BigDecimal weightContract) {
        this.weightContract = weightContract;
    }

    public BigDecimal getDrc() {
        return drc;
    }

    public void setDrc(BigDecimal drc) {
        this.drc = drc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (contractID != null ? contractID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contract)) {
            return false;
        }
        Contract other = (Contract) object;
        if ((this.contractID == null && other.contractID != null) || (this.contractID != null && !this.contractID.equals(other.contractID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.totoland.test.entity.Contract[ contractID=" + contractID + " ]";
    }

}
