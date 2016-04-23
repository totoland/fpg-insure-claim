/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.totoland.web.controller.form;

import com.totoland.db.common.entity.DropDownList;
import com.totoland.db.entity.ClaimInsure;
import com.totoland.web.controller.BaseController;
import com.totoland.web.factory.DropdownFactory;
import com.totoland.web.service.GennericService;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author totoland
 */
@ViewScoped
@ManagedBean
public class InsuranceFormController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(InsuranceFormController.class);
    private static final long serialVersionUID = -4658297318038575831L;

    private String insureType = "Air";
    private List<DropDownList> insureTypeList;
    private List<DropDownList> insureNameList;
    private List<DropDownList> currencyTypeList;
    private List<DropDownList> countriesList;
    private List<DropDownList> commodityTypeList;
    private List<DropDownList> coverageTypeList;
    private List<DropDownList> insuringTermsTypeList;
    private List<DropDownList> claimSurveyorsList;
    private ClaimInsure claimInsure;

    private String certNumber;

    @ManagedProperty("#{dropdownFactory}")
    private DropdownFactory dropdownFactory;
    @ManagedProperty("#{gennericService}")
    private GennericService<ClaimInsure> gennericService;
    
    private Date issueDate;

    @PostConstruct
    public void init() {
        LOGGER.debug("init...");
        this.insureTypeList = getInsureTypeList();
        this.insureNameList = dropdownFactory.ddlInsureName();
        this.currencyTypeList = dropdownFactory.ddlCurrencyType();
        this.countriesList = dropdownFactory.ddlCountries();
        this.commodityTypeList = dropdownFactory.ddlCommodityType();
        this.coverageTypeList = dropdownFactory.ddlCoverageType();
        this.insuringTermsTypeList = dropdownFactory.ddlInsuringTermsType();
        this.claimSurveyorsList = dropdownFactory.ddlClaimSurveyors();
        this.claimInsure = new ClaimInsure();
        this.claimInsure.setExchangeRate(new BigDecimal("35.64"));
        this.claimInsure.setIssueDate(new Date());
    }

    @Override
    public void resetForm() {
        init();
    }
    
    public void save(){
        LOGGER.debug("save : {}",this.claimInsure);
        gennericService.create(claimInsure);
    }

    public void selectInsureType(String selectedInsureType) {
        LOGGER.debug("selectInsureType : {}", selectedInsureType);
        this.insureType = selectedInsureType;
    }

    /**
     * @return the insureTypeList
     */
    public List<DropDownList> getInsureTypeList() {
        return dropdownFactory.ddlInsureTypesList();
    }

    /**
     * @return the dropdownFactory
     */
    public DropdownFactory getDropdownFactory() {
        return dropdownFactory;
    }

    /**
     * @param dropdownFactory the dropdownFactory to set
     */
    public void setDropdownFactory(DropdownFactory dropdownFactory) {
        this.dropdownFactory = dropdownFactory;
    }

    /**
     * @return the insureType
     */
    public String getInsureType() {
        return insureType;
    }

    /**
     * @param insureType the insureType to set
     */
    public void setInsureType(String insureType) {
        this.insureType = insureType;
    }

    /**
     * @param insureTypeList the insureTypeList to set
     */
    public void setInsureTypeList(List<DropDownList> insureTypeList) {
        this.insureTypeList = insureTypeList;
    }

    /**
     * @return the certNumber
     */
    public String getCertNumber() {
        certNumber = "";
        if (insureType != null || !insureType.trim().isEmpty() && insureType.length()>1) {
            try{
                certNumber = insureType.substring(0,1).toUpperCase();
            }catch(Exception ex){}
        }

        certNumber += RandomStringUtils.randomNumeric(7);

        return certNumber;
    }

    /**
     * @param certNumber the certNumber to set
     */
    public void setCertNumber(String certNumber) {
        this.certNumber = certNumber;
    }

    /**
     * @return the issueDate
     */
    public Date getIssueDate() {
        return issueDate;
    }

    /**
     * @param issueDate the issueDate to set
     */
    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    /**
     * @return the insureNameList
     */
    public List<DropDownList> getInsureNameList() {
        return insureNameList;
    }

    /**
     * @param insureNameList the insureNameList to set
     */
    public void setInsureNameList(List<DropDownList> insureNameList) {
        this.insureNameList = insureNameList;
    }

    /**
     * @return the claimInsure
     */
    public ClaimInsure getClaimInsure() {
        return claimInsure;
    }

    /**
     * @param claimInsure the claimInsure to set
     */
    public void setClaimInsure(ClaimInsure claimInsure) {
        this.claimInsure = claimInsure;
    }

    /**
     * @return the currencyTypeList
     */
    public List<DropDownList> getCurrencyTypeList() {
        return currencyTypeList;
    }

    /**
     * @return the countriesList
     */
    public List<DropDownList> getCountriesList() {
        return countriesList;
    }

    /**
     * @return the commodityTypeList
     */
    public List<DropDownList> getCommodityTypeList() {
        return commodityTypeList;
    }

    /**
     * @return the coverageTypeList
     */
    public List<DropDownList> getCoverageTypeList() {
        return coverageTypeList;
    }

    /**
     * @return the insuringTermsType
     */
    public List<DropDownList> getInsuringTermsTypeList() {
        return insuringTermsTypeList;
    }

    /**
     * @return the claimSurveyorsList
     */
    public List<DropDownList> getClaimSurveyorsList() {
        return claimSurveyorsList;
    }

    /**
     * @return the gennericService
     */
    public GennericService<ClaimInsure> getGennericService() {
        return gennericService;
    }

    /**
     * @param gennericService the gennericService to set
     */
    public void setGennericService(GennericService<ClaimInsure> gennericService) {
        this.gennericService = gennericService;
    }

}
