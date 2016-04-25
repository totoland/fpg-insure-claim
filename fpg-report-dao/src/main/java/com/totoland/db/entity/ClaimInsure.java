package com.totoland.db.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author totoland
 */
@Entity
@Table(name = "claim_insure")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClaimInsure.findAll", query = "SELECT c FROM ClaimInsure c")})
public class ClaimInsure implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "claim_id")
    private Integer claimId;
    @Column(name = "policy_number")
    private String policyNumber;
    @Column(name = "certification_number")
    private String certificationNumber;
    @Column(name = "claim_status_id")
    private Integer claimStatusId;
    @Column(name = "insured_id")
    private Integer insuredId;
    @Column(name = "consignee_name")
    private String consigneeName;
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
    private BigDecimal voyageFlightNumber;
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
    @Column(name = "commodity_type_id")
    private Integer commodityTypeId;
    @Lob
    @Column(name = "commodity_description")
    private String commodityDescription;
    @Column(name = "marks_and_numbers")
    private String marksAndNumbers;
    @Column(name = "coverage_type_id")
    private Integer coverageTypeId;
    @Column(name = "valuation_id")
    private Integer valuationId;
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

    public ClaimInsure() {
    }

    public ClaimInsure(Integer claimId) {
        this.claimId = claimId;
    }

    public Integer getClaimId() {
        return claimId;
    }

    public void setClaimId(Integer claimId) {
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

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
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

    public BigDecimal getVoyageFlightNumber() {
        return voyageFlightNumber;
    }

    public void setVoyageFlightNumber(BigDecimal voyageFlightNumber) {
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

    public Integer getCommodityTypeId() {
        return commodityTypeId;
    }

    public void setCommodityTypeId(Integer commodityTypeId) {
        this.commodityTypeId = commodityTypeId;
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

    public Integer getValuationId() {
        return valuationId;
    }

    public void setValuationId(Integer valuationId) {
        this.valuationId = valuationId;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (claimId != null ? claimId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClaimInsure)) {
            return false;
        }
        ClaimInsure other = (ClaimInsure) object;
        if ((this.claimId == null && other.claimId != null) || (this.claimId != null && !this.claimId.equals(other.claimId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ClaimInsure{" + "claimId=" + claimId + ", policyNumber=" + policyNumber + ", certificationNumber=" + certificationNumber + ", claimStatusId=" + claimStatusId + ", insuredId=" + insuredId + ", consigneeName=" + consigneeName + ", amountOfInsurance=" + amountOfInsurance + ", deductibleAmount=" + deductibleAmount + ", currencyType=" + currencyType + ", exchangeRate=" + exchangeRate + ", localAmountOfInsurance=" + localAmountOfInsurance + ", originCountryCode=" + originCountryCode + ", originStateProv=" + originStateProv + ", originDescription=" + originDescription + ", transshipmentPort=" + transshipmentPort + ", destinationCountryCode=" + destinationCountryCode + ", destinationStateProv=" + destinationStateProv + ", destinationDescription=" + destinationDescription + ", conveyanceName=" + conveyanceName + ", transshipmentVessel=" + transshipmentVessel + ", voyageFlightNumber=" + voyageFlightNumber + ", methodOfTransportId=" + methodOfTransportId + ", invoiceNumber=" + invoiceNumber + ", billOfLadingNumber=" + billOfLadingNumber + ", shipmentDate=" + shipmentDate + ", issueDate=" + issueDate + ", commodityTypeId=" + commodityTypeId + ", commodityDescription=" + commodityDescription + ", marksAndNumbers=" + marksAndNumbers + ", coverageTypeId=" + coverageTypeId + ", valuationId=" + valuationId + ", insuringTermsId=" + insuringTermsId + ", additionalInfomation=" + additionalInfomation + ", claimSurveyorId=" + claimSurveyorId + ", claimPayableAt=" + claimPayableAt + ", createdDateTime=" + createdDateTime + ", updatedDateTime=" + updatedDateTime + ", createdBy=" + createdBy + ", updatedBy=" + updatedBy + '}';
    }
}
