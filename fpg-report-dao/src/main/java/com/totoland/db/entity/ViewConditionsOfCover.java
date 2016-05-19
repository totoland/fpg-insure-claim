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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

/**
 *
 * @author totoland
 */
@Entity
public class ViewConditionsOfCover implements Serializable {

    private static final long serialVersionUID = 5717813324763066002L;

    @Id
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
    @Column(name = "customer_name")
    private String customerName;

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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.conditionsCoverId);
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
        final ViewConditionsOfCover other = (ViewConditionsOfCover) obj;
        if (!Objects.equals(this.conditionsCoverId, other.conditionsCoverId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ViewConditionsOfCover{" + "conditionsCoverId=" + conditionsCoverId + ", airConditions=" + airConditions + ", vesselConditions=" + vesselConditions + ", truckConditions=" + truckConditions + ", airSubject=" + airSubject + ", vesselSubject=" + vesselSubject + ", truckSubject=" + truckSubject + ", customerId=" + customerId + ", customerName=" + customerName + '}';
    }
}
