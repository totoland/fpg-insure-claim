/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.totoland.reporting.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author totoland
 */
public class DebitNote implements Serializable {

    private static final long serialVersionUID = 7410557423558657621L;
    
    //เลขประจำตัวผู้เสียภาษี
    private String taxNo;
    //ชื่อและที่อยู่ผู้เอาประกัน
    private String insuredName;
    private String insuredValue;
    private String insuredAddress;
    //เลขที่ No.
    private String certificationNumber;
    //วันที่
    private Date issueDate;
    //เบี้ยประกันภัย
    private BigDecimal premium;
    //อากรแสตมป์
    private BigDecimal stampDuty;
    //ประเภทกรมธรรม์
    private String typeOfPolicy;
    //รวม
    private BigDecimal total;
    //กรมธรรม์เลขที่
    private String policyNo;
    //ภาษีมูลค่าเพิ่ม
    private BigDecimal vat;
    //ระยะเวลาประกันภัย
    private Date warrantyFrom;
    private Date warrantyTo;
    //รวมเป็นเงิน
    private BigDecimal grandTotal;
    //นายหน้า/ตัวแทน
    private String brokerName;

    private String companyLogoURL;

    private String homeOffice;
    private String branchNo;
    private String homeOfficeLogoURL;
    private String branchCheckBoxURL;
    
    private String taxId;
    private String authorizedSignatureURL;
    private String previewURL;

    private String copyType;
    
    public String getTaxNo() {
        return taxNo;
    }

    public void setTaxNo(String taxNo) {
        this.taxNo = taxNo;
    }

    public String getInsuredName() {
        return insuredName;
    }

    public void setInsuredName(String insuredName) {
        this.insuredName = insuredName;
    }

    public String getInsuredAddress() {
        return insuredAddress;
    }

    public void setInsuredAddress(String insuredAddress) {
        this.insuredAddress = insuredAddress;
    }

    public String getCertificationNumber() {
        return certificationNumber;
    }

    public void setCertificationNumber(String certificationNumber) {
        this.certificationNumber = certificationNumber;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public BigDecimal getPremium() {
        return premium;
    }

    public void setPremium(BigDecimal premium) {
        this.premium = premium;
    }

    public BigDecimal getStampDuty() {
        return stampDuty;
    }

    public void setStampDuty(BigDecimal stampDuty) {
        this.stampDuty = stampDuty;
    }

    public String getTypeOfPolicy() {
        return typeOfPolicy;
    }

    public void setTypeOfPolicy(String typeOfPolicy) {
        this.typeOfPolicy = typeOfPolicy;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getPolicyNo() {
        return policyNo;
    }

    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo;
    }

    public BigDecimal getVat() {
        return vat;
    }

    public void setVat(BigDecimal vat) {
        this.vat = vat;
    }

    public Date getWarrantyFrom() {
        return warrantyFrom;
    }

    public void setWarrantyFrom(Date warrantyFrom) {
        this.warrantyFrom = warrantyFrom;
    }

    public Date getWarrantyTo() {
        return warrantyTo;
    }

    public void setWarrantyTo(Date warrantyTo) {
        this.warrantyTo = warrantyTo;
    }

    public BigDecimal getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(BigDecimal grandTotal) {
        this.grandTotal = grandTotal;
    }

    public String getBrokerName() {
        return brokerName;
    }

    public void setBrokerName(String brokerName) {
        this.brokerName = brokerName;
    }

    public String getCompanyLogoURL() {
        return companyLogoURL;
    }

    public void setCompanyLogoURL(String companyLogoURL) {
        this.companyLogoURL = companyLogoURL;
    }

    public String getInsuredValue() {
        return insuredValue;
    }

    public void setInsuredValue(String insuredValue) {
        this.insuredValue = insuredValue;
    }

    public String getHomeOffice() {
        return homeOffice;
    }

    public void setHomeOffice(String homeOffice) {
        this.homeOffice = homeOffice;
    }

    public String getBranchNo() {
        return branchNo;
    }

    public void setBranchNo(String branchNo) {
        this.branchNo = branchNo;
    }

    public String getHomeOfficeLogoURL() {
        return homeOfficeLogoURL;
    }

    public void setHomeOfficeLogoURL(String homeOfficeLogoURL) {
        this.homeOfficeLogoURL = homeOfficeLogoURL;
    }

    public String getBranchCheckBoxURL() {
        return branchCheckBoxURL;
    }

    public void setBranchCheckBoxURL(String branchCheckBoxURL) {
        this.branchCheckBoxURL = branchCheckBoxURL;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getAuthorizedSignatureURL() {
        return authorizedSignatureURL;
    }

    public void setAuthorizedSignatureURL(String authorizedSignatureURL) {
        this.authorizedSignatureURL = authorizedSignatureURL;
    }

    public String getPreviewURL() {
        return previewURL;
    }

    public void setPreviewURL(String previewURL) {
        this.previewURL = previewURL;
    }

    public String getCopyType() {
        return copyType;
    }

    public void setCopyType(String copyType) {
        this.copyType = copyType;
    }

    @Override
    public String toString() {
        return "DebitNote{" + "taxNo=" + taxNo + ", insuredName=" + insuredName + ", insuredValue=" + insuredValue + ", insuredAddress=" + insuredAddress + ", certificationNumber=" + certificationNumber + ", issueDate=" + issueDate + ", premium=" + premium + ", stampDuty=" + stampDuty + ", typeOfPolicy=" + typeOfPolicy + ", total=" + total + ", policyNo=" + policyNo + ", vat=" + vat + ", warrantyFrom=" + warrantyFrom + ", warrantyTo=" + warrantyTo + ", grandTotal=" + grandTotal + ", brokerName=" + brokerName + ", companyLogoURL=" + companyLogoURL + ", homeOffice=" + homeOffice + ", branchNo=" + branchNo + ", homeOfficeLogoURL=" + homeOfficeLogoURL + ", branchCheckBoxURL=" + branchCheckBoxURL + ", taxId=" + taxId + ", authorizedSignatureURL=" + authorizedSignatureURL + ", previewURL=" + previewURL + ", copyType=" + copyType + '}';
    }
}
