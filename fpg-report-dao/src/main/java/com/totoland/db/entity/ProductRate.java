/*
 * The MIT License
 *
 * Copyright 2016 totoland.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.totoland.db.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author totoland
 */
@Entity
@Table(name = "product_rate")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductRate.findAll", query = "SELECT p FROM ProductRate p")})
public class ProductRate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "product_rate_id")
    private Integer productRateId;
    @Column(name = "product_rate")
    private BigDecimal productRate;
    @Column(name = "customer_id")
    private Integer customerId;
    @Column(name = "product_id")
    private Integer productId;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "open_policy_no")
    private String openPolicyNo;
    @Lob
    @Column(name = "clauses_air")
    private String clausesAir;
    @Lob
    @Column(name = "clauses_vessel")
    private String clausesVessel;
    @Lob
    @Column(name = "clauses_truck")
    private String clausesTruck;
    @Lob
    @Column(name = "valuation")
    private String valuation;
    @Lob
    @Column(name = "broker_name")
    private String brokerName;
    @Transient
    private String subjectMatterInsured;

    public ProductRate() {
    }

    public ProductRate(Integer productRateId, BigDecimal productRate, Integer customerId, Integer productId) {
        this.productRateId = productRateId;
        this.productRate = productRate;
        this.customerId = customerId;
        this.productId = productId;
    }

    public ProductRate(Integer productRateId) {
        this.productRateId = productRateId;
    }

    public Integer getProductRateId() {
        return productRateId;
    }

    public void setProductRateId(Integer productRateId) {
        this.productRateId = productRateId;
    }

    public BigDecimal getProductRate() {
        return productRate;
    }

    public void setProductRate(BigDecimal productRate) {
        this.productRate = productRate;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productRateId != null ? productRateId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductRate)) {
            return false;
        }
        ProductRate other = (ProductRate) object;
        if ((this.productRateId == null && other.productRateId != null) || (this.productRateId != null && !this.productRateId.equals(other.productRateId))) {
            return false;
        }
        return true;
    }

    public String getOpenPolicyNo() {
        return openPolicyNo;
    }

    public void setOpenPolicyNo(String openPolicyNo) {
        this.openPolicyNo = openPolicyNo;
    }

    public String getClausesAir() {
        return clausesAir;
    }

    public void setClausesAir(String clausesAir) {
        this.clausesAir = clausesAir;
    }

    public String getClausesVessel() {
        return clausesVessel;
    }

    public void setClausesVessel(String clausesVessel) {
        this.clausesVessel = clausesVessel;
    }

    public String getClausesTruck() {
        return clausesTruck;
    }

    public void setClausesTruck(String clausesTruck) {
        this.clausesTruck = clausesTruck;
    }

    public String getValuation() {
        return valuation;
    }

    public void setValuation(String valuation) {
        this.valuation = valuation;
    }

    public String getBrokerName() {
        return brokerName;
    }

    public void setBrokerName(String brokerName) {
        this.brokerName = brokerName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSubjectMatterInsured() {
        return subjectMatterInsured;
    }

    public void setSubjectMatterInsured(String subjectMatterInsured) {
        this.subjectMatterInsured = subjectMatterInsured;
    }

    @Override
    public String toString() {
        return "ProductRate{" + "productRateId=" + productRateId + ", productRate=" + productRate + ", customerId=" + customerId + ", productId=" + productId + ", productName=" + productName + ", openPolicyNo=" + openPolicyNo + ", clausesAir=" + clausesAir + ", clausesVessel=" + clausesVessel + ", clausesTruck=" + clausesTruck + ", valuation=" + valuation + ", brokerName=" + brokerName + '}';
    }
}
