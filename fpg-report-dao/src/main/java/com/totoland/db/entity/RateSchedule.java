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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author totoland
 */
@Entity
@Table(name = "rate_schedule")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RateSchedule.findAll", query = "SELECT r FROM RateSchedule r")})
public class RateSchedule implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "rate_schedule_id")
    private Integer rateScheduleId;
    @Column(name = "rate_schedule_name")
    private String rateScheduleName;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "rate")
    private BigDecimal rate;
    @Column(name = "rate_schedule_code")
    private String rateScheduleCode;

    public RateSchedule() {
    }

    public RateSchedule(Integer rateScheduleId) {
        this.rateScheduleId = rateScheduleId;
    }

    public Integer getRateScheduleId() {
        return rateScheduleId;
    }

    public void setRateScheduleId(Integer rateScheduleId) {
        this.rateScheduleId = rateScheduleId;
    }

    public String getRateScheduleName() {
        return rateScheduleName;
    }

    public void setRateScheduleName(String rateScheduleName) {
        this.rateScheduleName = rateScheduleName;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public String getRateScheduleCode() {
        return rateScheduleCode;
    }

    public void setRateScheduleCode(String rateScheduleCode) {
        this.rateScheduleCode = rateScheduleCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rateScheduleId != null ? rateScheduleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RateSchedule)) {
            return false;
        }
        RateSchedule other = (RateSchedule) object;
        if ((this.rateScheduleId == null && other.rateScheduleId != null) || (this.rateScheduleId != null && !this.rateScheduleId.equals(other.rateScheduleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RateSchedule{" + "rateScheduleId=" + rateScheduleId + ", rateScheduleName=" + rateScheduleName + ", rate=" + rate + ", rateScheduleCode=" + rateScheduleCode + '}';
    }

}
