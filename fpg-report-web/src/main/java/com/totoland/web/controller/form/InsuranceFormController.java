package com.totoland.web.controller.form;

import com.chetty.reporting.beans.CertificationBean;
import com.totoland.db.bean.ConditionsOfCoverCriteria;
import com.totoland.db.common.entity.DropDownList;
import com.totoland.db.entity.ClaimInsure;
import com.totoland.db.entity.ImageCertExport;
import com.totoland.db.entity.KeyMatch;
import com.totoland.db.entity.ViewConditionsOfCover;
import com.totoland.db.entity.ViewUser;
import com.totoland.db.enums.CertificateType;
import com.totoland.db.enums.InsureState;
import com.totoland.reporting.bean.DebitNote;
import com.totoland.reporting.service.impl.PDFReportExporter;
import com.totoland.web.controller.BaseController;
import com.totoland.web.factory.DropdownFactory;
import com.totoland.web.service.CertificateService;
import com.totoland.web.service.ConditionsOfCoverService;
import com.totoland.web.service.KeyMatchService;
import com.totoland.web.service.UserService;
import com.totoland.web.servlet.ImageServlet;
import com.totoland.web.utils.JsfUtil;
import com.totoland.web.utils.MessageUtils;
import com.totoland.web.utils.StringUtils;
import com.totoland.web.utils.WebUtils;
import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
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
    private static final String MARINE_PDF_TEMPLATE = "/resources/jasper/reportFPG.jrxml";
    private static final String DEBIT_NOTE_PDF_TEMPLATE = "/resources/jasper/reportDebitNote.jrxml";
    private static final String RESOURCES_LOGO = "/resources/images/logo.png";
    private static final String RESOURCES_SIGNATURE1 = "/resources/images/signature/1.png";
    private static final String RESOURCES_SIGNATURE2 = "/resources/images/signature/2.png";
    private static final String NO_IMAGE = "/resources/images/no_image.jpg";

    private static final int AIR = 1;
    private static final int VESSEL = 2;
    private static final int TRUCK = 3;

    private String insureType = "Air";
    private List<DropDownList> insureTypeList;
    private List<DropDownList> currencyTypeList;
    private List<DropDownList> countriesList;
    private List<DropDownList> commodityTypeList;
    private List<DropDownList> coverageTypeList;
    private List<DropDownList> insuringTermsTypeList;
    private List<DropDownList> claimSurveyorsList;
    private List<DropDownList> rateScheduleList;
    private ClaimInsure claimInsure;

    private String certNumber;

    @ManagedProperty("#{dropdownFactory}")
    private DropdownFactory dropdownFactory;
    @ManagedProperty("#{certificateService}")
    private CertificateService certificateService;
    @ManagedProperty("#{keyMatchService}")
    private KeyMatchService keyMatchService;
    @ManagedProperty("#{userService}")
    private UserService userService;
    @ManagedProperty("#{conditionsOfCoverService}")
    private ConditionsOfCoverService conditionsOfCoverService;

    private Date issueDate;
    private boolean readOnly;
    private String imageUploadURL;

    private double invoiceValue;

    public double getInvoiceValue() {
        return invoiceValue;
    }

    public void setInvoiceValue(double invoiceValue) {
        this.invoiceValue = invoiceValue;
    }

    @PostConstruct
    public void init() {
        LOGGER.debug("init...");

        String trxId = super.getParameter("id");

        if (trxId == null) {
            initCreateMode();
        } else {
            initUpdateMode(trxId);
        }

        initData();
    }

    private void initCreateMode() {
        LOGGER.debug("initCreateMode...");
        this.certNumber = null;
        this.claimInsure = new ClaimInsure(0);
        this.claimInsure.setClaimStatusId(InsureState.NEW.getState());
        this.claimInsure.setExchangeRate(new BigDecimal(dropdownFactory.getCurrentExchangeRate()));
        this.claimInsure.setIssueDate(new Date());
        this.claimInsure.setInsuredId(getUserAuthen().getUserId());
        this.claimInsure.setInsuredName(getUserAuthen().getCompanyName());
        this.claimInsure.setMinimumPremiumRate(new BigDecimal(dropdownFactory.getMinimumPremium()));

        //Init image
        this.imageUploadURL = NO_IMAGE;
        KeyMatch keyMatch = keyMatchService.findByCustomerId(String.valueOf(getUserAuthen().getUserId()));
        this.claimInsure.setPolicyNumber(keyMatch != null ? keyMatch.getOpenPolicyNo() : "");
    }

    private void initUpdateMode(String trxId) {
        LOGGER.debug("initUpdateMode... with : {}", trxId);
        this.claimInsure = certificateService.findByTrxId(trxId);

        LOGGER.debug("claimInsure : {}", claimInsure);

        if (claimInsure == null) {
            LOGGER.warn("TrxId is gone, redirect to create page...");
            super.redirectPage("insuranceForm.xhtml");
            return;
        }

        this.imageUploadURL = String.format("/certImage?c=%s&n=%s", this.claimInsure.getClaimId(), trxId);
        LOGGER.debug("imageUploadURL : {}",this.imageUploadURL);
        this.certNumber = this.claimInsure.getCertificationNumber();
//        this.claimInsure.setExchangeRate(new BigDecimal(dropdownFactory.getCurrentExchangeRate()));

        if (this.claimInsure.getMinimumPremiumRate() == null) {
            this.claimInsure.setMinimumPremiumRate(new BigDecimal(dropdownFactory.getMinimumPremium()));
        }

        //Should not has this case
        if (StringUtils.isBlank(this.claimInsure.getPolicyNumber())) {
            LOGGER.warn("Open policyNumber is null try to set default by find customerId...");
            KeyMatch keyMatch = keyMatchService.findByCustomerId(String.valueOf(getUserAuthen().getUserId()));
            this.claimInsure.setPolicyNumber(keyMatch != null ? keyMatch.getOpenPolicyNo() : "");
        }

        if (this.claimInsure.getClaimStatusId() == InsureState.PRINT_CERT.getState()) {
            LOGGER.debug("Transaction has been printed set page to mode readOnly...");
            readOnly = true;
        }
    }

    public boolean renderSelectInsureType(int type) {
        if (!readOnly) {
            return true;
        }

        return this.claimInsure != null && this.claimInsure.getMethodOfTransportId() != null
                && this.claimInsure.getMethodOfTransportId() == type;
    }

    private void initData() {
        this.insureTypeList = getInsureTypeList();
        this.currencyTypeList = dropdownFactory.ddlCurrencyType();
        this.countriesList = dropdownFactory.ddlCountries();
        this.commodityTypeList = dropdownFactory.ddlCommodityType();
        this.coverageTypeList = dropdownFactory.ddlCoverageType();
        this.insuringTermsTypeList = dropdownFactory.ddlInsuringTermsType();
        this.claimSurveyorsList = dropdownFactory.ddlClaimSurveyors();
        if (isAdmin()) {
            this.rateScheduleList = dropdownFactory.ddlRateSchedule();
        } else {
            this.rateScheduleList = dropdownFactory.ddlRateSchedule(this.claimInsure.getPolicyNumber());
        }
        this.readOnly = false;
        this.clearImgSession();
    }

    @Override
    public void resetForm() {
        init();
    }

    public void save(int state) {
        Date sysdate = new Date();
        this.claimInsure.setCreatedBy(getUserAuthen().getUserId());
        this.claimInsure.setUpdatedBy(getUserAuthen().getUserId());
        this.claimInsure.setCreatedDateTime(sysdate);
        this.claimInsure.setUpdatedDateTime(sysdate);
        this.claimInsure.setClaimStatusId(state);
        this.claimInsure.setTrxId(WebUtils.generateToken());
        LOGGER.debug("save : {}", this.claimInsure);

        try {
            //save image
            ImageCertExport certExport = new ImageCertExport();

            try {
                byte[] imageUploaded = (byte[]) super.getRequest().getSession().getAttribute(ImageServlet.FILE_UPLOADED);
                String contentType = (String) super.getRequest().getSession().getAttribute(ImageServlet.FILE_CONTENT_TYPE);

                certExport.setImageContent(imageUploaded);
                certExport.setImageType(contentType);
                certExport.setImageName(new Date().getTime() + "");

            } catch (Exception ex) {
                LOGGER.warn("Cannot get image from session!!", ex.getMessage());
            }

            certificateService.save(this.claimInsure, certExport);
            addInfo(MessageUtils.SAVE_SUCCESS());
            JsfUtil.openDialog("dlgSave");
        } catch (Exception e) {
            LOGGER.error("ERROR WITH TRX_ID " + this.claimInsure.getTrxId() + " ", e);
            addError(MessageUtils.SAVE_NOT_SUCCESS());
        }

    }

    public void edit(int state) {
        Date sysdate = new Date();
        this.claimInsure.setCreatedBy(getUserAuthen().getUserId());
        this.claimInsure.setUpdatedBy(getUserAuthen().getUserId());
        this.claimInsure.setCreatedDateTime(sysdate);
        this.claimInsure.setUpdatedDateTime(sysdate);
        this.claimInsure.setClaimStatusId(state);
        LOGGER.debug("edit : {}", this.claimInsure);
        try {
            //save image
            ImageCertExport certExport = new ImageCertExport();

            try {
                byte[] imageUploaded = (byte[]) super.getRequest().getSession().getAttribute(ImageServlet.FILE_UPLOADED);
                String contentType = (String) super.getRequest().getSession().getAttribute(ImageServlet.FILE_CONTENT_TYPE);

                certExport.setImageContent(imageUploaded);
                certExport.setImageType(contentType);
                certExport.setImageName(new Date().getTime() + "");

            } catch (Exception ex) {
                LOGGER.warn("Cannot get image from session!!", ex.getMessage());
            }

            certificateService.save(this.claimInsure, certExport);
            addInfo(MessageUtils.SAVE_SUCCESS());
            JsfUtil.openDialog("dlgSave");
        } catch (Exception e) {
            LOGGER.error("ERROR WITH TRX_ID " + this.claimInsure.getTrxId() + " ", e);
            addError(MessageUtils.SAVE_NOT_SUCCESS());
        }
    }

    public void cancel(String trxId) {
        //Discard transaction
        try {
            certificateService.remove(claimInsure);
            addInfo(MessageUtils.DISCARD_SUCCESS());
        } catch (Exception e) {
            LOGGER.error("ERROR for discard with trxId " + trxId, e);
            addError(MessageUtils.DISCARD_FAIL());
        }
    }

    private void createCertificateNumber() {
        LOGGER.debug("createCertificateNumber...");
        this.certNumber = certificateService.getCertificateNO(insureType);
        this.claimInsure.setCertificationNumber(certNumber);
        this.claimInsure.setClaimStatusId(InsureState.PRINT_CERT.getState());
        readOnly = true;
        LOGGER.info("Print Certificate number : {} by user {}", certNumber, getUserAuthen().getUserId());
    }

    private String findCountryName(String code) {
        LOGGER.debug("findCountryName with : {}", code);
        if (this.countriesList == null) {
            this.countriesList = dropdownFactory.ddlCountries();
        }

        for (DropDownList ddl : this.countriesList) {
            if (code.equals(ddl.getValue())) {
                return ddl.getName();
            }
        }

        return null;
    }

    public StreamedContent getCertificateCopyFile(int certificateType) {
        LOGGER.debug("export CertificateType : {}", CertificateType.valueOf(certificateType));

        //If first time export must be export with ORIGINAL and need to create new Certificate number
        if (CertificateType.ORIGINAL.getValue() == certificateType) {
            try {
                LOGGER.debug("createCertificateNumber...");

                if (this.claimInsure.getCertificationNumber() == null) {
                    createCertificateNumber();
                }

            } catch (Exception ex) {
                LOGGER.error("Fail when create createCertificateNumber : ", ex);
                return null;
            }
        }

        try {

            CertificationBean certRpt = new CertificationBean();
            //Init data for print Certificate
            ViewUser userData = userService.findByUserId(this.claimInsure.getInsuredId());
            certRpt.setNameOfAssured(userData.getCompanyName());
            certRpt.setVesselAirline(this.claimInsure.getConveyanceName());
            certRpt.setReferenceNumber(this.claimInsure.getInvoiceNumber());
            certRpt.setAmountInsuredHereunder(String.valueOf(this.claimInsure.getAmountOfInsurance()));
            certRpt.setPolicyNumber(this.claimInsure.getPolicyNumber());
            certRpt.setVoyageFlightNo("");
            certRpt.setSailingFlightDate(this.claimInsure.getShipmentDate());
            certRpt.setCertificationNumber(this.claimInsure.getCertificationNumber());
            certRpt.setIssueOn(new Date());
            certRpt.setCopyType(CertificateType.valueOf(certificateType));
            certRpt.setFrom(findCountryName(this.claimInsure.getOriginCountryCode()));
            certRpt.setTo(findCountryName(this.claimInsure.getDestinationCountryCode()));

            certRpt.setCompanyLogoURL(JsfUtil.getFullURI() + RESOURCES_LOGO);
            certRpt.setSignature1URL(JsfUtil.getFullURI() + RESOURCES_SIGNATURE1);
            certRpt.setSignature2URL(JsfUtil.getFullURI() + RESOURCES_SIGNATURE2);

            //Init ConditionOfCover for print Certificate
            List<ViewConditionsOfCover> listCondition = conditionsOfCoverService.findByCriteria(new ConditionsOfCoverCriteria(this.claimInsure.getInsuredId()));
            if (listCondition != null && !listCondition.isEmpty()) {

                String subject = "";
                String detail = "";

                if (AIR == this.claimInsure.getMethodOfTransportId()) {
                    subject = listCondition.get(0).getAirSubject();
                    detail = listCondition.get(0).getAirConditions();
                } else if (VESSEL == this.claimInsure.getMethodOfTransportId()) {
                    subject = listCondition.get(0).getVesselSubject();
                    detail = listCondition.get(0).getVesselConditions();
                } else if (TRUCK == this.claimInsure.getMethodOfTransportId()) {
                    subject = listCondition.get(0).getTruckSubject();
                    detail = listCondition.get(0).getTruckConditions();
                }

                certRpt.setSubject(subject);
                certRpt.setDetail(detail);
            }

            LOGGER.debug("certRpt : {}", certRpt);

            String jrxmlPath = JsfUtil.getRealPath(MARINE_PDF_TEMPLATE);
            byte[] data = new PDFReportExporter().exporterToByte(jrxmlPath, Arrays.asList(certRpt));
            certificateService.edit(this.claimInsure);
            SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyyhhmm");
            return new DefaultStreamedContent(new ByteArrayInputStream(data),
                    "application/pdf", "CERTIFICATE_" + this.claimInsure.getCertificationNumber() + "_" + dateFormat.format(new Date()) + ".pdf");
        } catch (Exception ex) {
            LOGGER.error("download error ", ex);
            JsfUtil.alertJavaScript(ex.getMessage());
        }

        return null;
    }

    public StreamedContent getDebitNote() {
        LOGGER.debug("export Debit Note...");

        try {
            DebitNote debitNote = new DebitNote();
            //Init data for print Debit note
            ViewUser userData = userService.findByUserId(this.claimInsure.getInsuredId());
            debitNote.setTaxNo(dropdownFactory.ddlConf().get("tax_no"));
            debitNote.setInsuredName(userData.getCompanyName());
            debitNote.setInsuredAddress(userData.getAddress());
            debitNote.setCertificationNumber(this.claimInsure.getCertificationNumber());
            debitNote.setIssueDate(this.claimInsure.getIssueDate());
            debitNote.setPremium(this.claimInsure.getPremiumRate());
            //Premium rate * 0.4%
            debitNote.setStampDuty(WebUtils.divideRoundUp(WebUtils.mutilplyRoundUp(this.claimInsure.getPremiumRate(),
                    new BigDecimal(0.4)), new BigDecimal(100)));
            //Premium + StampDuty
            debitNote.setTotal(this.claimInsure.getPremiumRate().add(debitNote.getStampDuty()));
            //Vat = Premium + Stamp * 7%
            debitNote.setVat(WebUtils.divideRoundUp(WebUtils.mutilplyRoundUp(this.claimInsure.getPremiumRate().add(debitNote.getStampDuty()),
                    new BigDecimal(7)), new BigDecimal(100)));
            debitNote.setGrandTotal(debitNote.getTotal().add(debitNote.getVat()));
            debitNote.setTypeOfPolicy(dropdownFactory.ddlConf().get("type_of_policy"));
            debitNote.setPolicyNo(this.claimInsure.getPolicyNumber());
            debitNote.setWarrantyFrom(this.claimInsure.getShipmentDate());

            String jrxmlPath = JsfUtil.getRealPath(DEBIT_NOTE_PDF_TEMPLATE);
            byte[] data = new PDFReportExporter().exporterToByte(jrxmlPath, Arrays.asList(debitNote));
            certificateService.edit(this.claimInsure);
            SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyyhhmm");
            return new DefaultStreamedContent(new ByteArrayInputStream(data),
                    "application/pdf", "DEBIT_NOTE_" + this.claimInsure.getCertificationNumber() + "_" + dateFormat.format(new Date()) + ".pdf");
        } catch (Exception ex) {
            LOGGER.error("download error ", ex);
        }

        return null;
    }

    public void handleFileUpload(FileUploadEvent event) {
        addInfo(event.getFile().getFileName() + " is uploaded.");
        super.getRequest().getSession().setAttribute(ImageServlet.FILE_UPLOADED, event.getFile().getContents());
        super.getRequest().getSession().setAttribute(ImageServlet.FILE_CONTENT_TYPE, event.getFile().getContentType());
        this.imageUploadURL = "/image?r=" + new Date().getTime();
    }

    public void clearImgSession() {
        super.getRequest().getSession().setAttribute(ImageServlet.FILE_UPLOADED, null);
        super.getRequest().getSession().setAttribute(ImageServlet.FILE_CONTENT_TYPE, null);
    }

    public String getImageUploadURL() {
        return imageUploadURL;
    }

    public String getCurrentStatus(int claimStatusId) {
        return InsureState.toStateName(claimStatusId);
    }

    public void selectInsureType(String selectedInsureType) {
        this.insureType = findInsureType(selectedInsureType);
        this.claimInsure.setMethodOfTransportId(Integer.parseInt(selectedInsureType));
    }

    public void onRateScheduleChange() {
        //Change from local amount of insurance
        LOGGER.debug("InsuredValue : {}", this.claimInsure.getInsuredValue());
        LOGGER.debug("Rate  : {}", this.claimInsure.getRate());
        LOGGER.debug("amountOfInsurance  : {}", this.claimInsure.getAmountOfInsurance());

        if (this.claimInsure.getInsuredValue() == null || this.claimInsure.getRate() == null || this.claimInsure.getRate().intValue() == 0) {
            this.claimInsure.setPremiumRate(null);
            return;
        }
        BigDecimal mul = this.claimInsure.getInsuredValue().multiply(this.claimInsure.getRate());
        this.claimInsure.setPremiumRate(mul);
    }

    public void calAmountOfInsurance() {
        //var amountOfIns = ((inv) * valuat) / 100;
        try {
            if (this.claimInsure.getInvoiceValue() != null && this.claimInsure.getValuation() != null) {
                this.claimInsure.setAmountOfInsurance((this.claimInsure.getInvoiceValue().multiply(new BigDecimal(this.claimInsure.getValuation()))).divide(new BigDecimal(100)));
            }
        } catch (Exception ex) {
        }

        try {
            if (this.claimInsure.getAmountOfInsurance() != null && this.claimInsure.getExchangeRate() != null) {
                this.claimInsure.setInsuredValue(this.claimInsure.getAmountOfInsurance().multiply(this.claimInsure.getExchangeRate()));
            }
        } catch (Exception ex) {
        }

        try {
            this.onRateScheduleChange();
        } catch (Exception ex) {
        }
    }

    private DropDownList selectedCommodityType;

    public DropDownList getSelectedCommodityType() {
        return selectedCommodityType;
    }

    public void setSelectedCommodityType(DropDownList selectedCommodityType) {
        this.selectedCommodityType = selectedCommodityType;
    }

    public void onRowselectedCommodityType(SelectEvent event) {
        //((Car) event.getObject()).getId()
        LOGGER.debug("selected : {}", selectedCommodityType);
        this.claimInsure.setCommodityTypeCode(selectedCommodityType.getValue());
        this.claimInsure.setCommodityTypeName(selectedCommodityType.getName());
    }

    private String findInsureType(String selectedInsureType) {
        for (DropDownList ddl : dropdownFactory.ddlMethodOfTransport()) {
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
        return dropdownFactory.ddlMethodOfTransport();
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

    public List<DropDownList> getRateScheduleList() {
        return rateScheduleList;
    }

    public void setRateScheduleList(List<DropDownList> rateScheduleList) {
        this.rateScheduleList = rateScheduleList;
    }

    public CertificateService getCertificateService() {
        return certificateService;
    }

    public void setCertificateService(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    public KeyMatchService getKeyMatchService() {
        return keyMatchService;
    }

    public void setKeyMatchService(KeyMatchService keyMatchService) {
        this.keyMatchService = keyMatchService;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public ConditionsOfCoverService getConditionsOfCoverService() {
        return conditionsOfCoverService;
    }

    public void setConditionsOfCoverService(ConditionsOfCoverService conditionsOfCoverService) {
        this.conditionsOfCoverService = conditionsOfCoverService;
    }
}
