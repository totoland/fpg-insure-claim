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
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author totoland
 */
@Entity
public class ViewCertificate implements Serializable {

    private static final long serialVersionUID = -1L;

    @Id
    @Basic(optional = false)
    @Column(name = "claim_id")
    private Long claimId;
    @Column(name = "policy_number")
    private String policyNumber;
    @Column(name = "certification_number")
    private String certificationNumber;
    @Column(name = "claim_status_id")
    private Integer claimStatusId;
    @Column(name = "insured_id")
    private Integer insuredId;
    @Column(name = "insured_name")
    private String insuredName;
    @Column(name = "consignee_name")
    private String consigneeName;
    @Column(name = "invoice_value")
    private BigDecimal invoiceValue;
    @Column(name = "insured_value")
    private BigDecimal insuredValue;
    @Column(name = "amount_of_insurance")
    private BigDecimal amountOfInsurance;
    @Column(name = "deductible_amount")
    private BigDecimal deductibleAmount;
    @Column(name = "currency_type")
    private String currencyType;
    @Column(name = "exchange_rate")
    private BigDecimal exchangeRate;
    @Column(name = "local_amount_of_insurance")
    private BigDecimal localAmountOfInsurance;
    @Column(name = "origin_country_code")
    private String originCountryCode;
    @Column(name = "origin_state_prov")
    private String originStateProv;
    @Lob
    @Column(name = "origin_description")
    private String originDescription;
    @Column(name = "transshipment_port")
    private String transshipmentPort;
    @Column(name = "destination_country_code")
    private String destinationCountryCode;
    @Column(name = "destination_state_prov")
    private String destinationStateProv;
    @Lob
    @Column(name = "destination_description")
    private String destinationDescription;
    @Column(name = "conveyance_name")
    private String conveyanceName;
    @Column(name = "transshipment_vessel")
    private String transshipmentVessel;
    @Column(name = "voyage_flight_number")
    private String voyageFlightNumber;
    @Column(name = "method_of_transport_id")
    private Integer methodOfTransportId;
    @Column(name = "invoice_number")
    private String invoiceNumber;
    @Column(name = "bill_of_lading_number")
    private String billOfLadingNumber;
    @Column(name = "shipment_date")
    @Temporal(TemporalType.DATE)
    private Date shipmentDate;
    @Column(name = "issue_date")
    @Temporal(TemporalType.DATE)
    private Date issueDate;
    @Column(name = "commodity_type_code")
    private String commodityTypeCode;
    @Column(name = "commodity_type_name")
    private String commodityTypeName;
    @Lob
    @Column(name = "commodity_description")
    private String commodityDescription;
    @Column(name = "marks_and_numbers")
    private String marksAndNumbers;
    @Column(name = "coverage_type_id")
    private Integer coverageTypeId;
    @Column(name = "valuation")
    private String valuation;
    @Column(name = "insuring_terms_id")
    private Integer insuringTermsId;
    @Lob
    @Column(name = "additional_infomation")
    private String additionalInfomation;
    @Column(name = "claim_surveyor_id")
    private Integer claimSurveyorId;
    @Column(name = "claim_payable_at")
    private String claimPayableAt;
    @Column(name = "created_date_time", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDateTime;
    @Column(name = "updated_date_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDateTime;
    @Column(name = "created_by", updatable = false)
    private Integer createdBy;
    @Column(name = "updated_by")
    private Integer updatedBy;
    @Column(name = "trx_id", updatable = false)
    private String trxId;
    @Column(name = "rate")
    private BigDecimal rate;
    @Column(name = "premium_rate")
    private BigDecimal premiumRate;
    @Column(name = "minimum_premium_rate")
    private BigDecimal minimumPremiumRate;

    @Column(name = "stamp")
    private BigDecimal stamp;
    @Column(name = "vat")
    private BigDecimal vat;
    @Column(name = "total")
    private BigDecimal total;
    @Column(name = "claim_status_name")
    private String claimStatusName;
    @Column(name = "transport_method")
    private String transportMethod;
    @Column(name = "origin_country_name")
    private String originCountryName;
    @Column(name = "destination_country_name")
    private String destinationCountryName;
    
    public Long getClaimId() {
        return claimId;
    }

    public void setClaimId(Long claimId) {
        this.claimId = claimId;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public String getCertificationNumber() {
        return certificationNumber;
    }

    public void setCertificationNumber(String certificationNumber) {
        this.certificationNumber = certificationNumber;
    }

    public Integer getClaimStatusId() {
        return claimStatusId;
    }

    public void setClaimStatusId(Integer claimStatusId) {
        this.claimStatusId = claimStatusId;
    }

    public Integer getInsuredId() {
        return insuredId;
    }

    public void setInsuredId(Integer insuredId) {
        this.insuredId = insuredId;
    }

    public String getInsuredName() {
        return insuredName;
    }

    public void setInsuredName(String insuredName) {
        this.insuredName = insuredName;
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }

    public BigDecimal getInvoiceValue() {
        return invoiceValue;
    }

    public void setInvoiceValue(BigDecimal invoiceValue) {
        this.invoiceValue = invoiceValue;
    }

    public BigDecimal getInsuredValue() {
        return insuredValue;
    }

    public void setInsuredValue(BigDecimal insuredValue) {
        this.insuredValue = insuredValue;
    }

    public BigDecimal getAmountOfInsurance() {
        return amountOfInsurance;
    }

    public void setAmountOfInsurance(BigDecimal amountOfInsurance) {
        this.amountOfInsurance = amountOfInsurance;
    }

    public BigDecimal getDeductibleAmount() {
        return deductibleAmount;
    }

    public void setDeductibleAmount(BigDecimal deductibleAmount) {
        this.deductibleAmount = deductibleAmount;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public BigDecimal getLocalAmountOfInsurance() {
        return localAmountOfInsurance;
    }

    public void setLocalAmountOfInsurance(BigDecimal localAmountOfInsurance) {
        this.localAmountOfInsurance = localAmountOfInsurance;
    }

    public String getOriginCountryCode() {
        return originCountryCode;
    }

    public void setOriginCountryCode(String originCountryCode) {
        this.originCountryCode = originCountryCode;
    }

    public String getOriginStateProv() {
        return originStateProv;
    }

    public void setOriginStateProv(String originStateProv) {
        this.originStateProv = originStateProv;
    }

    public String getOriginDescription() {
        return originDescription;
    }

    public void setOriginDescription(String originDescription) {
        this.originDescription = originDescription;
    }

    public String getTransshipmentPort() {
        return transshipmentPort;
    }

    public void setTransshipmentPort(String transshipmentPort) {
        this.transshipmentPort = transshipmentPort;
    }

    public String getDestinationCountryCode() {
        return destinationCountryCode;
    }

    public void setDestinationCountryCode(String destinationCountryCode) {
        this.destinationCountryCode = destinationCountryCode;
    }

    public String getDestinationStateProv() {
        return destinationStateProv;
    }

    public void setDestinationStateProv(String destinationStateProv) {
        this.destinationStateProv = destinationStateProv;
    }

    public String getDestinationDescription() {
        return destinationDescription;
    }

    public void setDestinationDescription(String destinationDescription) {
        this.destinationDescription = destinationDescription;
    }

    public String getConveyanceName() {
        return conveyanceName;
    }

    public void setConveyanceName(String conveyanceName) {
        this.conveyanceName = conveyanceName;
    }

    public String getTransshipmentVessel() {
        return transshipmentVessel;
    }

    public void setTransshipmentVessel(String transshipmentVessel) {
        this.transshipmentVessel = transshipmentVessel;
    }

    public String getVoyageFlightNumber() {
        return voyageFlightNumber;
    }

    public void setVoyageFlightNumber(String voyageFlightNumber) {
        this.voyageFlightNumber = voyageFlightNumber;
    }

    public Integer getMethodOfTransportId() {
        return methodOfTransportId;
    }

    public void setMethodOfTransportId(Integer methodOfTransportId) {
        this.methodOfTransportId = methodOfTransportId;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getBillOfLadingNumber() {
        return billOfLadingNumber;
    }

    public void setBillOfLadingNumber(String billOfLadingNumber) {
        this.billOfLadingNumber = billOfLadingNumber;
    }

    public Date getShipmentDate() {
        return shipmentDate;
    }

    public void setShipmentDate(Date shipmentDate) {
        this.shipmentDate = shipmentDate;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public String getCommodityTypeCode() {
        return commodityTypeCode;
    }

    public void setCommodityTypeCode(String commodityTypeCode) {
        this.commodityTypeCode = commodityTypeCode;
    }

    public String getCommodityTypeName() {
        return commodityTypeName;
    }

    public void setCommodityTypeName(String commodityTypeName) {
        this.commodityTypeName = commodityTypeName;
    }

    public String getCommodityDescription() {
        return commodityDescription;
    }

    public void setCommodityDescription(String commodityDescription) {
        this.commodityDescription = commodityDescription;
    }

    public String getMarksAndNumbers() {
        return marksAndNumbers;
    }

    public void setMarksAndNumbers(String marksAndNumbers) {
        this.marksAndNumbers = marksAndNumbers;
    }

    public Integer getCoverageTypeId() {
        return coverageTypeId;
    }

    public void setCoverageTypeId(Integer coverageTypeId) {
        this.coverageTypeId = coverageTypeId;
    }

    public String getValuation() {
        return valuation;
    }

    public void setValuation(String valuation) {
        this.valuation = valuation;
    }

    public Integer getInsuringTermsId() {
        return insuringTermsId;
    }

    public void setInsuringTermsId(Integer insuringTermsId) {
        this.insuringTermsId = insuringTermsId;
    }

    public String getAdditionalInfomation() {
        return additionalInfomation;
    }

    public void setAdditionalInfomation(String additionalInfomation) {
        this.additionalInfomation = additionalInfomation;
    }

    public Integer getClaimSurveyorId() {
        return claimSurveyorId;
    }

    public void setClaimSurveyorId(Integer claimSurveyorId) {
        this.claimSurveyorId = claimSurveyorId;
    }

    public String getClaimPayableAt() {
        return claimPayableAt;
    }

    public void setClaimPayableAt(String claimPayableAt) {
        this.claimPayableAt = claimPayableAt;
    }

    public Date getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(Date createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public Date getUpdatedDateTime() {
        return updatedDateTime;
    }

    public void setUpdatedDateTime(Date updatedDateTime) {
        this.updatedDateTime = updatedDateTime;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getTrxId() {
        return trxId;
    }

    public void setTrxId(String trxId) {
        this.trxId = trxId;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getPremiumRate() {
        return premiumRate;
    }

    public void setPremiumRate(BigDecimal premiumRate) {
        this.premiumRate = premiumRate;
    }

    public BigDecimal getMinimumPremiumRate() {
        return minimumPremiumRate;
    }

    public void setMinimumPremiumRate(BigDecimal minimumPremiumRate) {
        this.minimumPremiumRate = minimumPremiumRate;
    }

    public BigDecimal getStamp() {
        return stamp;
    }

    public void setStamp(BigDecimal stamp) {
        this.stamp = stamp;
    }

    public BigDecimal getVat() {
        return vat;
    }

    public void setVat(BigDecimal vat) {
        this.vat = vat;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getClaimStatusName() {
        return claimStatusName;
    }

    public void setClaimStatusName(String claimStatusName) {
        this.claimStatusName = claimStatusName;
    }

    public String getTransportMethod() {
        return transportMethod;
    }

    public String getOriginCountryName() {
        return originCountryName;
    }

    public void setOriginCountryName(String originCountryName) {
        this.originCountryName = originCountryName;
    }

    public String getDestinationCountryName() {
        return destinationCountryName;
    }

    public void setDestinationCountryName(String destinationCountryName) {
        this.destinationCountryName = destinationCountryName;
    }

    @Override
    public String toString() {
        return "ViewCertificate{" + "claimId=" + claimId + ", policyNumber=" + policyNumber + ", certificationNumber=" + certificationNumber + ", claimStatusId=" + claimStatusId + ", insuredId=" + insuredId + ", insuredName=" + insuredName + ", consigneeName=" + consigneeName + ", invoiceValue=" + invoiceValue + ", insuredValue=" + insuredValue + ", amountOfInsurance=" + amountOfInsurance + ", deductibleAmount=" + deductibleAmount + ", currencyType=" + currencyType + ", exchangeRate=" + exchangeRate + ", localAmountOfInsurance=" + localAmountOfInsurance + ", originCountryCode=" + originCountryCode + ", originStateProv=" + originStateProv + ", originDescription=" + originDescription + ", transshipmentPort=" + transshipmentPort + ", destinationCountryCode=" + destinationCountryCode + ", destinationStateProv=" + destinationStateProv + ", destinationDescription=" + destinationDescription + ", conveyanceName=" + conveyanceName + ", transshipmentVessel=" + transshipmentVessel + ", voyageFlightNumber=" + voyageFlightNumber + ", methodOfTransportId=" + methodOfTransportId + ", invoiceNumber=" + invoiceNumber + ", billOfLadingNumber=" + billOfLadingNumber + ", shipmentDate=" + shipmentDate + ", issueDate=" + issueDate + ", commodityTypeCode=" + commodityTypeCode + ", commodityTypeName=" + commodityTypeName + ", commodityDescription=" + commodityDescription + ", marksAndNumbers=" + marksAndNumbers + ", coverageTypeId=" + coverageTypeId + ", valuation=" + valuation + ", insuringTermsId=" + insuringTermsId + ", additionalInfomation=" + additionalInfomation + ", claimSurveyorId=" + claimSurveyorId + ", claimPayableAt=" + claimPayableAt + ", createdDateTime=" + createdDateTime + ", updatedDateTime=" + updatedDateTime + ", createdBy=" + createdBy + ", updatedBy=" + updatedBy + ", trxId=" + trxId + ", rate=" + rate + ", premiumRate=" + premiumRate + ", minimumPremiumRate=" + minimumPremiumRate + ", stamp=" + stamp + ", vat=" + vat + ", total=" + total + ", claimStatusName=" + claimStatusName + ", transportMethod=" + transportMethod + '}';
    }
}
