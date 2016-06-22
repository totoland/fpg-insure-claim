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
package com.totoland.db.bean;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author totoland
 */
public class CertifaicationCriteria implements Serializable {

    private static final long serialVersionUID = 5004656164826203543L;

    private String certificateNumber;
    private String policyNumber;
    private String insuredName;
    private String status;
    private Date issueDateFrom;
    private Date issueDateTo;
    private Date brokerName;
    private Date brokerLicenses;

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public String getInsuredName() {
        return insuredName;
    }

    public void setInsuredName(String insuredName) {
        this.insuredName = insuredName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getIssueDateFrom() {
        return issueDateFrom;
    }

    public void setIssueDateFrom(Date issueDateFrom) {
        this.issueDateFrom = issueDateFrom;
    }

    public Date getIssueDateTo() {
        return issueDateTo;
    }

    public void setIssueDateTo(Date issueDateTo) {
        this.issueDateTo = issueDateTo;
    }

    public Date getBrokerName() {
        return brokerName;
    }

    public void setBrokerName(Date brokerName) {
        this.brokerName = brokerName;
    }

    public Date getBrokerLicenses() {
        return brokerLicenses;
    }

    public void setBrokerLicenses(Date brokerLicenses) {
        this.brokerLicenses = brokerLicenses;
    }

    @Override
    public String toString() {
        return "CertifaicationCriteria{" + "certificateNumber=" + certificateNumber
                + ", policyNumber=" + policyNumber + ", insuredName=" + insuredName + ", status="
                + status + ", issueDateFrom=" + issueDateFrom + ", issueDateTo=" + issueDateTo
                + ", brokerName=" + brokerName + ", brokerLicenses=" + brokerLicenses + '}';
    }
}
