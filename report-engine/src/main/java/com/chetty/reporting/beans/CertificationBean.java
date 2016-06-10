/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chetty.reporting.beans;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author totoland
 */
public class CertificationBean implements Serializable {

    private static final long serialVersionUID = -6111808733320333073L;

    private String nameOfAssured;
    private String vesselAirline;
    private String referenceNumber;
    private String voyage;
    private String from;
    private String to;
    private String amountInsuredHereunder;
    private String policyNumber;
    private String voyageFlightNo;
    private Date sailingFlightDate;
    private Date issueDate;

    private String subject;
    private String detail;
    private Date issueOn;
    private String copyType;
    private String certificationNumber;
    private String companyLogoURL;
    private String signature1URL;
    private String signature2URL;
    private String certImageURL;

    private String quantity;

    private String surveyorCompany;
    private String surveyorAddress;
    private String surveyorTel;
    private String surveyorFax;
    private String contactName;

    private String commodityDescription;

    private String currencyType;

    public String getNameOfAssured() {
        return nameOfAssured;
    }

    public void setNameOfAssured(String nameOfAssured) {
        this.nameOfAssured = nameOfAssured;
    }

    public String getVesselAirline() {
        return vesselAirline;
    }

    public void setVesselAirline(String vesselAirline) {
        this.vesselAirline = vesselAirline;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public String getVoyage() {
        return voyage;
    }

    public void setVoyage(String voyage) {
        this.voyage = voyage;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getAmountInsuredHereunder() {
        return amountInsuredHereunder;
    }

    public void setAmountInsuredHereunder(String amountInsuredHereunder) {
        this.amountInsuredHereunder = amountInsuredHereunder;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public String getVoyageFlightNo() {
        return voyageFlightNo;
    }

    public void setVoyageFlightNo(String voyageFlightNo) {
        this.voyageFlightNo = voyageFlightNo;
    }

    public Date getSailingFlightDate() {
        return sailingFlightDate;
    }

    public void setSailingFlightDate(Date sailingFlightDate) {
        this.sailingFlightDate = sailingFlightDate;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Date getIssueOn() {
        return issueOn;
    }

    public void setIssueOn(Date issueOn) {
        this.issueOn = issueOn;
    }

    public String getCopyType() {
        return copyType;
    }

    public void setCopyType(String copyType) {
        this.copyType = copyType;
    }

    public String getCertificationNumber() {
        return certificationNumber;
    }

    public void setCertificationNumber(String certificationNumber) {
        this.certificationNumber = certificationNumber;
    }

    public String getCompanyLogoURL() {
        return companyLogoURL;
    }

    public void setCompanyLogoURL(String companyLogoURL) {
        this.companyLogoURL = companyLogoURL;
    }

    public String getSignature1URL() {
        return signature1URL;
    }

    public void setSignature1URL(String signature1URL) {
        this.signature1URL = signature1URL;
    }

    public String getSignature2URL() {
        return signature2URL;
    }

    public void setSignature2URL(String signature2URL) {
        this.signature2URL = signature2URL;
    }

    public String getCommodityDescription() {
        return commodityDescription;
    }

    public void setCommodityDescription(String commodityDescription) {
        this.commodityDescription = commodityDescription;
    }

    public String getSurveyorCompany() {
        return surveyorCompany;
    }

    public void setSurveyorCompany(String surveyorCompany) {
        this.surveyorCompany = surveyorCompany;
    }

    public String getSurveyorAddress() {
        return surveyorAddress;
    }

    public void setSurveyorAddress(String surveyorAddress) {
        this.surveyorAddress = surveyorAddress;
    }

    public String getSurveyorTel() {
        return surveyorTel;
    }

    public void setSurveyorTel(String surveyorTel) {
        this.surveyorTel = surveyorTel;
    }

    public String getSurveyorFax() {
        return surveyorFax;
    }

    public void setSurveyorFax(String surveyorFax) {
        this.surveyorFax = surveyorFax;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public String getCertImageURL() {
        return certImageURL;
    }

    public void setCertImageURL(String certImageURL) {
        this.certImageURL = certImageURL;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    @Override
    public String toString() {
        return "CertificationBean{" + "nameOfAssured=" + nameOfAssured + ", vesselAirline=" + vesselAirline + ", referenceNumber=" + referenceNumber + ", voyage=" + voyage + ", from=" + from + ", to=" + to + ", amountInsuredHereunder=" + amountInsuredHereunder + ", policyNumber=" + policyNumber + ", voyageFlightNo=" + voyageFlightNo + ", sailingFlightDate=" + sailingFlightDate + ", issueDate=" + issueDate + ", subject=" + subject + ", detail=" + detail + ", issueOn=" + issueOn + ", copyType=" + copyType + ", certificationNumber=" + certificationNumber + ", companyLogoURL=" + companyLogoURL + ", signature1URL=" + signature1URL + ", signature2URL=" + signature2URL + ", certImageURL=" + certImageURL + ", quantity=" + quantity + ", surveyorCompany=" + surveyorCompany + ", surveyorAddress=" + surveyorAddress + ", surveyorTel=" + surveyorTel + ", surveyorFax=" + surveyorFax + ", contactName=" + contactName + ", commodityDescription=" + commodityDescription + ", currencyType=" + currencyType + '}';
    }
}
