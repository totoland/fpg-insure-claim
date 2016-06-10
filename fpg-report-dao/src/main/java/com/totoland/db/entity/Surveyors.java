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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author totoland
 */
@Entity
@Table(name = "surveyors")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Surveyors.findAll", query = "SELECT s FROM Surveyors s")})
public class Surveyors implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "surveyor_id")
    private Integer surveyorId;
    @Column(name = "CARGO")
    private String cargo;
    @Column(name = "HULL")
    private String hull;
    @Column(name = "Country")
    private String country;
    @Column(name = "Location")
    private String location;
    @Column(name = "GMT")
    private String gmt;
    @Column(name = "Area_description")
    private String areadescription;
    @Column(name = "Company")
    private String company;
    @Column(name = "Address")
    private String address;
    @Column(name = "ContactName")
    private String contactName;
    @Column(name = "ContactName2")
    private String contactName2;
    @Column(name = "Tel1")
    private String tel1;
    @Column(name = "AfterHrsTel1")
    private String afterHrsTel1;
    @Column(name = "FAX1")
    private String fax1;
    @Column(name = "Mobile")
    private String mobile;
    @Column(name = "MobileContact")
    private String mobileContact;
    @Column(name = "Email")
    private String email;
    @Column(name = "EMail2")
    private String eMail2;
    @Column(name = "Website")
    private String website;
    @Column(name = "Min_Charge_USD")
    private String minChargeUSD;
    @Column(name = "Hourly_Rate_Range_USD")
    private String hourlyRateRangeUSD;

    public Surveyors() {
    }

    public Surveyors(Integer surveyorId) {
        this.surveyorId = surveyorId;
    }

    public Integer getSurveyorId() {
        return surveyorId;
    }

    public void setSurveyorId(Integer surveyorId) {
        this.surveyorId = surveyorId;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getHull() {
        return hull;
    }

    public void setHull(String hull) {
        this.hull = hull;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getGmt() {
        return gmt;
    }

    public void setGmt(String gmt) {
        this.gmt = gmt;
    }

    public String getAreadescription() {
        return areadescription;
    }

    public void setAreadescription(String areadescription) {
        this.areadescription = areadescription;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactName2() {
        return contactName2;
    }

    public void setContactName2(String contactName2) {
        this.contactName2 = contactName2;
    }

    public String getTel1() {
        return tel1;
    }

    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }

    public String getAfterHrsTel1() {
        return afterHrsTel1;
    }

    public void setAfterHrsTel1(String afterHrsTel1) {
        this.afterHrsTel1 = afterHrsTel1;
    }

    public String getFax1() {
        return fax1;
    }

    public void setFax1(String fax1) {
        this.fax1 = fax1;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobileContact() {
        return mobileContact;
    }

    public void setMobileContact(String mobileContact) {
        this.mobileContact = mobileContact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEMail2() {
        return eMail2;
    }

    public void setEMail2(String eMail2) {
        this.eMail2 = eMail2;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getMinChargeUSD() {
        return minChargeUSD;
    }

    public void setMinChargeUSD(String minChargeUSD) {
        this.minChargeUSD = minChargeUSD;
    }

    public String getHourlyRateRangeUSD() {
        return hourlyRateRangeUSD;
    }

    public void setHourlyRateRangeUSD(String hourlyRateRangeUSD) {
        this.hourlyRateRangeUSD = hourlyRateRangeUSD;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (surveyorId != null ? surveyorId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Surveyors)) {
            return false;
        }
        Surveyors other = (Surveyors) object;
        if ((this.surveyorId == null && other.surveyorId != null) || (this.surveyorId != null && !this.surveyorId.equals(other.surveyorId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Surveyors{" + "surveyorId=" + surveyorId + ", cargo=" + cargo + ", hull=" + hull + ", country=" + country + ", location=" + location + ", gmt=" + gmt + ", areadescription=" + areadescription + ", company=" + company + ", address=" + address + ", contactName=" + contactName + ", contactName2=" + contactName2 + ", tel1=" + tel1 + ", afterHrsTel1=" + afterHrsTel1 + ", fax1=" + fax1 + ", mobile=" + mobile + ", mobileContact=" + mobileContact + ", email=" + email + ", eMail2=" + eMail2 + ", website=" + website + ", minChargeUSD=" + minChargeUSD + ", hourlyRateRangeUSD=" + hourlyRateRangeUSD + '}';
    }
}
