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
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author totoland
 */
@Entity
@Table(name = "valuation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Valuation.findAll", query = "SELECT v FROM Valuation v")})
public class Valuation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "open_policy_no")
    private String openPolicyNo;

    @Lob
    @Column(name = "valuation_data")
    private String valuationData;

    @Column(name = "broker_name")
    private String brokerName;
    @Column(name = "valuation_id")
    private Integer valuationId;
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
    @Column(name = "sbuject_matter_insered")
    private String sbujectMatterInsered;

    public Valuation() {
    }

    public String getValuationData() {
        return valuationData;
    }

    public void setValuationData(String valuationData) {
        this.valuationData = valuationData;
    }

    public String getOpenPolicyNo() {
        return openPolicyNo;
    }

    public void setOpenPolicyNo(String openPolicyNo) {
        this.openPolicyNo = openPolicyNo;
    }

    public String getBrokerName() {
        return brokerName;
    }

    public void setBrokerName(String brokerName) {
        this.brokerName = brokerName;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.openPolicyNo);
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
        final Valuation other = (Valuation) obj;
        if (!Objects.equals(this.openPolicyNo, other.openPolicyNo)) {
            return false;
        }
        return true;
    }

    public Integer getValuationId() {
        return valuationId;
    }

    public void setValuationId(Integer valuationId) {
        this.valuationId = valuationId;
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

    public String getSbujectMatterInsered() {
        return sbujectMatterInsered;
    }

    public void setSbujectMatterInsered(String sbujectMatterInsered) {
        this.sbujectMatterInsered = sbujectMatterInsered;
    }

    @Override
    public String toString() {
        return "Valuation{" + "openPolicyNo=" + openPolicyNo + ", valuationData=" + valuationData + ", brokerName=" + brokerName + '}';
    }
}
