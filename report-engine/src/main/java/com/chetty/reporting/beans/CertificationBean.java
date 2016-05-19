/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chetty.reporting.beans;

import java.io.Serializable;

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
    private String sailingFlightDate;

    private String subject;
    private String detail;

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

    public String getSailingFlightDate() {
        return sailingFlightDate;
    }

    public void setSailingFlightDate(String sailingFlightDate) {
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

    @Override
    public String toString() {
        return "CertificationBean{" + "nameOfAssured=" + nameOfAssured + ", vesselAirline=" + vesselAirline + ", referenceNumber=" + referenceNumber + ", voyage=" + voyage + ", from=" + from + ", to=" + to + ", amountInsuredHereunder=" + amountInsuredHereunder + ", policyNumber=" + policyNumber + ", voyageFlightNo=" + voyageFlightNo + ", sailingFlightDate=" + sailingFlightDate + ", subject=" + subject + ", detail=" + detail + '}';
    }
}
