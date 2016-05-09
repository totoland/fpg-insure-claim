/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.totoland.web.controller.form;

import com.totoland.db.common.entity.DropDownList;
import com.totoland.db.entity.ClaimInsure;
import com.totoland.db.enums.CertificateType;
import com.totoland.db.enums.InsureState;
import com.totoland.web.controller.BaseController;
import com.totoland.web.factory.DropdownFactory;
import com.totoland.web.service.CertificateService;
import com.totoland.web.service.GennericService;
import com.totoland.web.utils.MessageUtils;
import com.totoland.web.utils.WebUtils;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
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
    @ManagedProperty("#{certificateService}")
    private CertificateService certificateService;

    private Date issueDate;
    private StreamedContent certificateFile;
    private boolean readOnly;
    
    @PostConstruct
    public void init() {
        LOGGER.debug("init...");
        initData();

        String trxId = super.getParameter("id");

        if (trxId == null) {
            initCreateMode();
        } else {
            initUpdateMode(trxId);
        }
    }

    private void initCreateMode() {
        this.certNumber = null;
        this.claimInsure = new ClaimInsure(0);
        this.claimInsure.setClaimStatusId(InsureState.NEW.getState());
        this.claimInsure.setExchangeRate(new BigDecimal("35.64"));
        this.claimInsure.setIssueDate(new Date());
    }

    private void initUpdateMode(String trxId) {
        this.claimInsure = certificateService.findByTrxId(trxId);
        
        if(claimInsure == null){
            LOGGER.warn("TrxId is gone, redirect to create page...");
            super.redirectPage("insuranceForm.xhtml");
            return;
        }
        
        this.certNumber = this.claimInsure.getCertificationNumber();
        this.claimInsure.setClaimStatusId(this.claimInsure.getClaimStatusId());
        this.claimInsure.setExchangeRate(new BigDecimal("35.64"));

        if(this.claimInsure.getClaimStatusId() == InsureState.PRINT_CERT.getState()){
            LOGGER.debug("Transaction has been printed set page to mode readOnly...");
            readOnly = true;
        }
        
        LOGGER.debug("initUpdateMode with : {}", claimInsure);
    }

    private void initData() {
        this.insureTypeList = getInsureTypeList();
        this.insureNameList = dropdownFactory.ddlInsureName();
        this.currencyTypeList = dropdownFactory.ddlCurrencyType();
        this.countriesList = dropdownFactory.ddlCountries();
        this.commodityTypeList = dropdownFactory.ddlCommodityType();
        this.coverageTypeList = dropdownFactory.ddlCoverageType();
        this.insuringTermsTypeList = dropdownFactory.ddlInsuringTermsType();
        this.claimSurveyorsList = dropdownFactory.ddlClaimSurveyors();
        this.readOnly = false;
    }

    @Override
    public void resetForm() {
        init();
    }

    public void save(int state) {
        this.claimInsure.setCreatedBy(1);
        this.claimInsure.setCreatedDateTime(new Date());
        this.claimInsure.setClaimStatusId(state);
        this.claimInsure.setTrxId(WebUtils.generateToken());
        LOGGER.debug("save : {}", this.claimInsure);

        try {
            gennericService.edit(claimInsure);
            addInfo(MessageUtils.SAVE_SUCCESS());
            initCreateMode();
        } catch (Exception e) {
            LOGGER.error("ERROR WITH TRX_ID " + this.claimInsure.getTrxId() + " ", e);
            addError(MessageUtils.SAVE_NOT_SUCCESS());
        }

    }

    public void edit(int state) {
        this.claimInsure.setUpdatedBy(1);
        this.claimInsure.setUpdatedDateTime(new Date());
        this.claimInsure.setClaimStatusId(state);
        LOGGER.debug("edit : {}", this.claimInsure);
        try {
            gennericService.edit(claimInsure);
            addInfo(MessageUtils.SAVE_SUCCESS());
        } catch (Exception e) {
            LOGGER.error("ERROR WITH TRX_ID " + this.claimInsure.getTrxId() + " ", e);
            addError(MessageUtils.SAVE_NOT_SUCCESS());
        }
    }

    public void cancel(String trxId) {
        //Discard transaction
        try {
            gennericService.remove(claimInsure);
            addInfo(MessageUtils.DISCARD_SUCCESS());
        } catch (Exception e) {
            LOGGER.error("ERROR for discard with trxId "+trxId,e);
            addError(MessageUtils.DISCARD_FAIL());
        }
    }

    public void printCert() {
        //TODO: Save and Call export pdf
        this.certNumber = certificateService.getCertificateNO(insureType);
        this.claimInsure.setCertificationNumber(certNumber);
        //gennericService.edit(this.claimInsure);
        LOGGER.info("Print Certificate number : {} by user {}", certNumber, getUserAuthen().getUserId());

    }

    public void exportCert(CertificateType type) {

    }

    public String getCurrentStatus(int claimStatusId) {
        return InsureState.toStateName(claimStatusId);
    }

    public void selectInsureType(String selectedInsureType) {
        this.insureType = findInsureType(selectedInsureType);
        this.claimInsure.setMethodOfTransportId(Integer.parseInt(selectedInsureType));
    }

    private String findInsureType(String selectedInsureType) {
        for (DropDownList ddl : dropdownFactory.ddlInsureTypesList()) {
            if (ddl.getValue().equals(selectedInsureType)) {
                return ddl.getName();
            }
        }

        return null;
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

    public CertificateService getCertificateService() {
        return certificateService;
    }

    public void setCertificateService(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    /**
     * @return the certificateFile
     */
    public StreamedContent getCertificateFile() {
        this.claimInsure.setUpdatedBy(1);
        this.claimInsure.setUpdatedDateTime(new Date());
        this.claimInsure.setClaimStatusId(InsureState.PRINT_CERT.getState());
        LOGGER.debug("edit : {}", this.claimInsure);
        try {
            gennericService.edit(claimInsure);
            addInfo(MessageUtils.SAVE_SUCCESS());
        } catch (Exception e) {
            LOGGER.error("ERROR WITH TRX_ID " + this.claimInsure.getTrxId() + " ", e);
            addError(MessageUtils.SAVE_NOT_SUCCESS());
        }
//        printCert();
        LOGGER.debug("export CertificateType : {}", CertificateType.ORIGINAL);

        //TODO:Download PDF
        try {
            InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/template/OPEN_COVER_BY_AIR1.pdf");
            return new DefaultStreamedContent(stream, "application/pdf", "OPEN_COVER_BY_AIR1.pdf");
        } catch (Exception ex) {
            LOGGER.error("download error ", ex);
            return null;
        }
    }

    /**
     * @param certificateFile the certificateFile to set
     */
    public void setCertificateFile(StreamedContent certificateFile) {
        this.certificateFile = certificateFile;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }
}
