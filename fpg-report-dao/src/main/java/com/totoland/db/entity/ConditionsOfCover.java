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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author totoland
 */
@Entity
@Table(name = "conditions_of_cover")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConditionsOfCover.findAll", query = "SELECT c FROM ConditionsOfCover c")})
public class ConditionsOfCover implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "conditions_cover_id")
    private Integer conditionsCoverId;
    @Lob
    @Column(name = "air_conditions")
    private String airConditions;
    @Lob
    @Column(name = "vessel_conditions")
    private String vesselConditions;
    @Lob
    @Column(name = "truck_conditions")
    private String truckConditions;
    @Lob
    @Column(name = "air_subject")
    private String airSubject;
    @Lob
    @Column(name = "vessel_subject")
    private String vesselSubject;
    @Lob
    @Column(name = "truck_subject")
    private String truckSubject;

    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "open_policy_no")
    private String openPolicyNo;

    public ConditionsOfCover() {
    }

    public ConditionsOfCover(Integer conditionsCoverId) {
        this.conditionsCoverId = conditionsCoverId;
    }

    public Integer getConditionsCoverId() {
        return conditionsCoverId;
    }

    public void setConditionsCoverId(Integer conditionsCoverId) {
        this.conditionsCoverId = conditionsCoverId;
    }

    public String getAirConditions() {
        return airConditions;
    }

    public void setAirConditions(String airConditions) {
        this.airConditions = airConditions;
    }

    public String getVesselConditions() {
        return vesselConditions;
    }

    public void setVesselConditions(String vesselConditions) {
        this.vesselConditions = vesselConditions;
    }

    public String getTruckConditions() {
        return truckConditions;
    }

    public void setTruckConditions(String truckConditions) {
        this.truckConditions = truckConditions;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getOpenPolicyNo() {
        return openPolicyNo;
    }

    public void setOpenPolicyNo(String openPolicyNo) {
        this.openPolicyNo = openPolicyNo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (conditionsCoverId != null ? conditionsCoverId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConditionsOfCover)) {
            return false;
        }
        ConditionsOfCover other = (ConditionsOfCover) object;
        if ((this.conditionsCoverId == null && other.conditionsCoverId != null) || (this.conditionsCoverId != null && !this.conditionsCoverId.equals(other.conditionsCoverId))) {
            return false;
        }
        return true;
    }

    public String getAirSubject() {
        return airSubject;
    }

    public void setAirSubject(String airSubject) {
        this.airSubject = airSubject;
    }

    public String getVesselSubject() {
        return vesselSubject;
    }

    public void setVesselSubject(String vesselSubject) {
        this.vesselSubject = vesselSubject;
    }

    public String getTruckSubject() {
        return truckSubject;
    }

    public void setTruckSubject(String truckSubject) {
        this.truckSubject = truckSubject;
    }

    @Override
    public String toString() {
        return "ConditionsOfCover{" + "conditionsCoverId=" + conditionsCoverId + ", airConditions=" + airConditions + ", vesselConditions=" + vesselConditions + ", truckConditions=" + truckConditions + ", airSubject=" + airSubject + ", vesselSubject=" + vesselSubject + ", truckSubject=" + truckSubject + ", customerId=" + customerId + ", openPolicyNo=" + openPolicyNo + '}';
    }
}
