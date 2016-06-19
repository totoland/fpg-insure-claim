package com.totoland.web.controller.form;

import com.totoland.reporting.bean.CertificationBean;
import com.totoland.db.bean.OpenPolicyCriteria;
import com.totoland.db.common.entity.DropDownList;
import com.totoland.db.entity.ClaimInsure;
import com.totoland.db.entity.ImageCertExport;
import com.totoland.db.entity.Surveyors;
import com.totoland.db.entity.OpenPolicy;
import com.totoland.db.entity.ViewUser;
import com.totoland.db.enums.CertificateType;
import com.totoland.db.enums.DebitNoteType;
import com.totoland.db.enums.InsureState;
import com.totoland.reporting.bean.ArrayCollectionBean;
import com.totoland.reporting.bean.DebitNote;
import com.totoland.reporting.service.impl.PDFReportExporter;
import com.totoland.rss.kbank.Rss;
import com.totoland.web.controller.BaseController;
import com.totoland.web.factory.DropdownFactory;
import com.totoland.web.service.CertificateService;
import com.totoland.web.service.ConditionsOfCoverService;
import com.totoland.web.service.GennericService;
import com.totoland.web.service.OpenPolicyService;
import com.totoland.web.service.SurveyorService;
import com.totoland.web.service.UserService;
import com.totoland.web.service.impl.XMLService;
import com.totoland.web.servlet.ImageServlet;
import com.totoland.web.utils.DateTimeUtils;
import com.totoland.web.utils.JsfUtil;
import com.totoland.web.utils.MessageUtils;
import com.totoland.web.utils.NumberToWord;
import com.totoland.web.utils.WebUtils;
import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import org.apache.commons.lang3.StringEscapeUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

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
    private static final String RESOURCES_LOGO = "/resources/images/fpg/logo.png";
    private static final String RESOURCES_SIGNATURE1 = "/resources/images/signature/1.png";
    private static final String RESOURCES_SIGNATURE2 = "/resources/images/signature/2.png";
    private static final String RESOURCES_SIGNATURE3 = "/resources/images/signature/sign_authorized_signature.jpg";
    private static final String RESOURCES_PREVIEW = "/resources/images/preview.png";
    private static final String RESOURCES_CHECKED = "/resources/images/checked.png";
    private static final String RESOURCES_UNCHECKED = "/resources/images/unchecked.png";
    private static final String NO_IMAGE = "/resources/images/no_image.jpg";

    private static final BigDecimal DEFAULT_MAX_INSURE_VALUE = new BigDecimal(10000000);

    private static final int AIR = 1;
    private static final int VESSEL = 2;
    private static final int TRUCK = 3;

    private static final int SHIPMENT_AIR_BEFORE_DATE = MessageUtils.getConfAsInt("shipment.air.before.date", 1);
    private static final int SHIPMENT_VESSEL_BEFORE_DATE = MessageUtils.getConfAsInt("shipment.vessel.before.date", 4);
    private static final int SHIPMENT_TRUCK_BEFORE_DATE = MessageUtils.getConfAsInt("shipment.truck.before.date", 0);

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
    @ManagedProperty("#{userService}")
    private UserService userService;
    @ManagedProperty("#{conditionsOfCoverService}")
    private ConditionsOfCoverService conditionsOfCoverService;
    @ManagedProperty("#{surveyorService}")
    private SurveyorService surveyorService;
    @ManagedProperty("#{gennericService}")
    private GennericService<OpenPolicy> valuationService;
    @ManagedProperty("#{openPolicyService}")
    private OpenPolicyService openPolicyService;

    private XMLService<Rss> xMLService;

    private Date issueDate;
    private boolean readOnly;
    private String imageUploadURL;

    private double invoiceValue;

    private BigDecimal maxOfInsureValue;
    private DropDownList selectedCommodityType;
    private Date minShipmentDate = new Date();

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
        this.claimInsure = new ClaimInsure(0L);
        this.claimInsure.setClaimStatusId(InsureState.NEW.getState());
        this.claimInsure.setIssueDate(new Date());
        this.claimInsure.setInsuredId(getUserAuthen().getUserId());
        this.claimInsure.setInsuredName(getUserAuthen().getCompanyName());
        this.claimInsure.setMinimumPremiumRate(new BigDecimal(dropdownFactory.getMinimumPremium()));

        //Init image
        this.imageUploadURL = NO_IMAGE;
        this.claimInsure.setPolicyNumber(getUserAuthen().getPolicyNo());
        this.readOnly = false;
    }

    private void initUpdateMode(String trxId) {
        LOGGER.debug("initUpdateMode... with : {}", trxId);
        this.claimInsure = certificateService.findByTrxId(trxId);

        if (claimInsure == null) {
            LOGGER.warn("TrxId is gone, redirect to create page...");
            super.redirectPage("insuranceForm.xhtml");
            return;
        }

        if (!Objects.equals(this.claimInsure.getCreatedBy(), getUserAuthen().getUserId()) && !isAdmin()) {
            super.redirectPage(JsfUtil.getContextPath() + "/errors/access_denie.xhtml");
            return;
        }

        this.imageUploadURL = String.format("/certImage?c=%s&n=%s", this.claimInsure.getClaimId(), trxId);

        this.certNumber = this.claimInsure.getCertificationNumber();

        if (this.claimInsure.getMinimumPremiumRate() == null) {
            this.claimInsure.setMinimumPremiumRate(new BigDecimal(dropdownFactory.getMinimumPremium()));
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
        try {
            this.currencyTypeList = dropdownFactory.getRssExchangeRate();
            LOGGER.debug("currencyTypeList for user [{}] : {}", getUserAuthen().getUsername(), currencyTypeList);
        } catch (Exception ex) {
            LOGGER.error("Cannot fetch RSS feed : ", ex);
            super.redirectPage(JsfUtil.getContextPath() + "/errors/505.xhtml");
        }
        this.countriesList = dropdownFactory.ddlCountries();
        this.commodityTypeList = dropdownFactory.ddlCommodityType();
        this.coverageTypeList = dropdownFactory.ddlCoverageType();
        this.insuringTermsTypeList = dropdownFactory.ddlInsuringTermsType();
        this.claimSurveyorsList = dropdownFactory.ddlClaimSurveyors();

        this.rateScheduleList = dropdownFactory.ddlRateSchedule(this.claimInsure.getPolicyNumber());

        List<OpenPolicy> openpolicys = openPolicyService.findByCriteria(new OpenPolicyCriteria(null, getUserAuthen().getPolicyNo()));
        if (openpolicys != null && !openpolicys.isEmpty()) {
            this.maxOfInsureValue = openpolicys.get(0).getMaxInsureValue() != null ? openpolicys.get(0).getMaxInsureValue() : DEFAULT_MAX_INSURE_VALUE;
        }
        LOGGER.debug("maxOfInsureValue : {}", maxOfInsureValue);

        this.clearImgSession();
        this.onMethodOfTransportChange();
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
            //disable this feature
//            ImageCertExport certExport = new ImageCertExport();
//
//            try {
//                byte[] imageUploaded = (byte[]) super.getRequest().getSession().getAttribute(ImageServlet.FILE_UPLOADED);
//                String contentType = (String) super.getRequest().getSession().getAttribute(ImageServlet.FILE_CONTENT_TYPE);
//
//                certExport.setImageContent(imageUploaded);
//                certExport.setImageType(contentType);
//                certExport.setImageName(new Date().getTime() + "");
//                certExport.setClaimInsureId(this.claimInsure.getClaimId());
//
//            } catch (Exception ex) {
//                LOGGER.warn("Cannot get image from session!!", ex.getMessage());
//            }

            certificateService.create(this.claimInsure);

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
        this.claimInsure.setClaimStatusId(InsureState.PRINT_CERT.getState());
        this.claimInsure.setCreatedBy(getUserAuthen().getUserId());
        this.claimInsure.setCreatedDateTime(new Date());
        if (this.claimInsure.getTrxId() == null) {
            this.claimInsure.setTrxId(WebUtils.generateToken());
        }

        if (this.claimInsure.getClaimId() == null || this.claimInsure.getClaimId() == 0) {
            certificateService.create(this.claimInsure);
        } else {
            certificateService.edit(this.claimInsure);
        }
        this.certNumber = certificateService.getCertificateNO(this.claimInsure);
        LOGGER.debug("createCertificateNumber... {}", this.claimInsure);
        this.claimInsure.setCertificationNumber(certNumber);
        certificateService.updateStateCertNo(this.claimInsure);
        readOnly = true;
        LOGGER.info("Print Certificate number : {} by user {}", certNumber, getUserAuthen().getUserId());
    }

    private String findCountryName(String code) {
        LOGGER.debug("findCountryName with : {}", code);
        if (this.countriesList == null) {
            this.countriesList = dropdownFactory.ddlCountries();
        }

        try {
            for (DropDownList ddl : this.countriesList) {
                if (code.equals(ddl.getValue())) {
                    return ddl.getName();
                }
            }
        } catch (Exception ex) {
        }

        return null;
    }

    public StreamedContent getCertificateCopyFile(int certificateType) {

        //Validate insure value
        if (this.claimInsure.getInsuredValue() != null && this.claimInsure.getInsuredValue().compareTo(maxOfInsureValue) > 0) {
            LOGGER.warn("Insure value < {}", maxOfInsureValue);
            super.updateCliend(":form:genericDialog");
            super.openDialog("genericDialog");
            return null;
        }

        //If valid shipment date return only case hack from javascript client!!
        if (this.claimInsure.getShipmentDate() != null && this.claimInsure.getShipmentDate().before(this.minShipmentDate)) {
            return null;
        }

        LOGGER.debug("export CertificateType : {}", CertificateType.valueOf(certificateType));

        //If first time export must be export with ORIGINAL and need to create new Certificate number
        if (CertificateType.ORIGINAL.getValue() == certificateType) {
            try {
                LOGGER.debug("createCertificateNumber...");

                createCertificateNumber();

            } catch (Exception ex) {
                LOGGER.error("Fail when create createCertificateNumber : ", ex);
                return null;
            }
        }

        try {
            //Init ConditionOfCover for print Certificate
            Map<String, Object> params = new HashMap<>();
            params.put("openPolicyNo", this.claimInsure.getPolicyNumber());

            List<OpenPolicy> listPolicy = openPolicyService.findByDynamicField(OpenPolicy.class, params);

            CertificationBean certRpt = new CertificationBean();
            //Init data for print Certificate
            certRpt.setNameOfAssured(this.claimInsure.getInsuredName());
            certRpt.setVesselAirline(this.claimInsure.getConveyanceName());
            certRpt.setReferenceNumber(this.claimInsure.getInvoiceNumber());
            certRpt.setAmountInsuredHereunder(String.valueOf(this.claimInsure.getAmountOfInsurance()));
            certRpt.setAmountInsuredHereunderWord(new NumberToWord.DefaultProcessor().getName(String.valueOf(this.claimInsure.getAmountOfInsurance())).toUpperCase());
            certRpt.setPolicyNumber(this.claimInsure.getPolicyNumber());
            certRpt.setCertificationNumber(this.claimInsure.getCertificationNumber());
            certRpt.setVoyageFlightNo("");
            certRpt.setSailingFlightDate(this.claimInsure.getShipmentDate());
            certRpt.setIssueOn(this.claimInsure.getIssueDate());
            certRpt.setCopyType(CertificateType.valueOf(certificateType));
            certRpt.setFrom(findCountryName(this.claimInsure.getOriginCountryCode()));
            certRpt.setFromDesc(this.claimInsure.getOriginDescription());
            certRpt.setTo(findCountryName(this.claimInsure.getDestinationCountryCode()));
            certRpt.setToDesc(this.claimInsure.getDestinationDescription());
            certRpt.setCurrencyType(this.claimInsure.getCurrencyType() != null
                    && this.claimInsure.getCurrencyType().contains("USD") ? "USD" : this.claimInsure.getCurrencyType());
            certRpt.setShipmentDate(DateTimeUtils.getInstance().add(this.claimInsure.getShipmentDate(), -1));
            certRpt.setSubject(this.claimInsure.getMarksAndNumbers());
            certRpt.setCommodityDescription(this.claimInsure.getCommodityDescription());

            if (this.claimInsure.getBillOfLadingNumber() != null
                    && !this.claimInsure.getBillOfLadingNumber().trim().isEmpty()) {
                String newLine = this.claimInsure.getCommodityDescription() != null
                        && !this.claimInsure.getCommodityDescription().trim().isEmpty() ? "\n" : "";
                certRpt.setCommodityDescription(this.claimInsure.getCommodityDescription() + newLine + "B/L no : "
                        + this.claimInsure.getBillOfLadingNumber().trim());
            }

            certRpt.setAdditionalInfomation(this.claimInsure.getAdditionalInfomation());

            certRpt.setCompanyLogoURL(JsfUtil.getFullURI() + RESOURCES_LOGO);
            certRpt.setSignature1URL(JsfUtil.getFullURI() + RESOURCES_SIGNATURE1);
            certRpt.setSignature2URL(JsfUtil.getFullURI() + RESOURCES_SIGNATURE2);
            certRpt.setSignature3URL(JsfUtil.getFullURI() + RESOURCES_SIGNATURE3);
            if (certificateType == 0) {
                certRpt.setPreviewURL(JsfUtil.getFullURI() + RESOURCES_PREVIEW);
            }

            Surveyors surveyor = surveyorService.findById(this.claimInsure.getClaimSurveyorId());

            if (surveyor != null) {
                certRpt.setSurveyorCompany(surveyor.getCompany());
                certRpt.setSurveyorAddress(surveyor.getAddress());
                certRpt.setSurveyorTel(surveyor.getTel1());
                certRpt.setSurveyorFax(surveyor.getFax1());
                certRpt.setContactName(surveyor.getContactName());
            }

            if (listPolicy != null && !listPolicy.isEmpty()) {

                String detail = "";

                if (AIR == this.claimInsure.getMethodOfTransportId()) {
                    detail = listPolicy.get(0).getClausesAir();
                } else if (VESSEL == this.claimInsure.getMethodOfTransportId()) {
                    detail = listPolicy.get(0).getClausesVessel();
                } else if (TRUCK == this.claimInsure.getMethodOfTransportId()) {
                    detail = listPolicy.get(0).getClausesTruck();
                }

                certRpt.setDetail(detail);
                certRpt.setBrokerName(listPolicy.get(0).getBrokerName());
                certRpt.setBrokerLicense(listPolicy.get(0).getBrokerLicense());
                certRpt.setFullCurrencyType("(" + certRpt.getCurrencyType() + "$ 1 = BATH " + this.claimInsure.getExchangeRate().doubleValue() + ")");
            }

            LOGGER.debug("certRpt : {}", certRpt);

            String jrxmlPath = JsfUtil.getRealPath(MARINE_PDF_TEMPLATE);
            List<ArrayCollectionBean> collectionBeans = new ArrayList<>();

            if (CertificateType.COMPANY_COPY.getValue() == certificateType) {
                certRpt.setCopyType(CertificateType.ORIGINAL.name());
                collectionBeans.add(new ArrayCollectionBean(jrxmlPath, Arrays.asList(certRpt)));

                CertificationBean bean1 = new CertificationBean();
                BeanUtils.copyProperties(certRpt, bean1);
                bean1.setCopyType(CertificateType.DUPPICATE.name());
                collectionBeans.add(new ArrayCollectionBean(jrxmlPath, Arrays.asList(bean1)));

                CertificationBean bean2 = new CertificationBean();
                BeanUtils.copyProperties(certRpt, bean2);
                bean2.setCopyType(CertificateType.INSURED_COPY.name());
                collectionBeans.add(new ArrayCollectionBean(jrxmlPath, Arrays.asList(bean2)));
                
                //For Debit note
                String jrxmlPathDebit = JsfUtil.getRealPath(DEBIT_NOTE_PDF_TEMPLATE);
                DebitNote debitNoteOri = getDebitNoteBean();
                debitNoteOri.setCopyType(DebitNoteType.ORIGINAL.getName());
                collectionBeans.add(new ArrayCollectionBean(jrxmlPathDebit, Arrays.asList(debitNoteOri)));
                
                DebitNote debitNoteCopy = new DebitNote();
                BeanUtils.copyProperties(debitNoteOri, debitNoteCopy);
                debitNoteCopy.setCopyType(DebitNoteType.COPY.getName());
                collectionBeans.add(new ArrayCollectionBean(jrxmlPathDebit, Arrays.asList(debitNoteCopy)));
            } else {
                collectionBeans.add(new ArrayCollectionBean(jrxmlPath, Arrays.asList(certRpt)));
            }
            
            byte[] data = new PDFReportExporter().exporterToByteList(collectionBeans);
            SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyyhhmm");
            return new DefaultStreamedContent(new ByteArrayInputStream(data),
                    "application/pdf", "CERTIFICATE_" + this.claimInsure.getCertificationNumber() + "_" + dateFormat.format(new Date()) + ".pdf");
        } catch (Exception ex) {
            LOGGER.error("download error ", ex);
            JsfUtil.alertJavaScript(ex.getMessage());
        }

        return null;
    }

    private DebitNote getDebitNoteBean(){
        DebitNote debitNote = new DebitNote();
            //Init data for Open Policy
            Map<String, Object> params = new HashMap<>();
            params.put("openPolicyNo", this.claimInsure.getPolicyNumber());
            List<OpenPolicy> listPolicy = openPolicyService.findByDynamicField(OpenPolicy.class, params);
            if (listPolicy != null && !listPolicy.isEmpty()) {
                debitNote.setBrokerName(listPolicy.get(0).getBrokerName());
            }

            //Init data for print Debit note
            ViewUser userData = userService.findByUserId(this.claimInsure.getInsuredId());
            LOGGER.debug("userData : {}", userData);
            debitNote.setTaxNo(dropdownFactory.ddlConf().get("tax_no"));
            debitNote.setInsuredName(userData.getCompanyName());
            debitNote.setInsuredAddress(userData.getAddress());
            debitNote.setCertificationNumber(this.claimInsure.getCertificationNumber());
            debitNote.setIssueDate(this.claimInsure.getIssueDate());
            debitNote.setTaxId(userData.getTaxId());

            if (userData.getCompanyType() != null && userData.getCompanyType().equals("1")) {
                debitNote.setBranchNo(null);
                debitNote.setBranchCheckBoxURL(JsfUtil.getFullURI() + RESOURCES_UNCHECKED);
                debitNote.setHomeOfficeLogoURL(JsfUtil.getFullURI() + RESOURCES_CHECKED);
            } else if (userData.getCompanyType() != null && userData.getCompanyType().equals("2")) {
                debitNote.setBranchNo(userData.getBranchDesc());
                debitNote.setBranchCheckBoxURL(JsfUtil.getFullURI() + RESOURCES_CHECKED);
                debitNote.setHomeOfficeLogoURL(JsfUtil.getFullURI() + RESOURCES_UNCHECKED);
            }

            debitNote.setAuthorizedSignatureURL(JsfUtil.getFullURI() + RESOURCES_SIGNATURE3);

            if (this.claimInsure.getPremiumRate().intValue() < 500) {
                LOGGER.debug("Premium rate lower than 500 use Minimum Premium = 500");
                this.claimInsure.setPremiumRate(new BigDecimal(500));
            }

            debitNote.setPremium(this.claimInsure.getPremiumRate());
            debitNote.setInsuredValue(this.claimInsure.getInsuredValue() + "");
            //Premium rate * 0.4%
            debitNote.setStampDuty(WebUtils.divideRoundUp(WebUtils.mutilplyRoundUp(this.claimInsure.getPremiumRate(),
                    new BigDecimal(0.4)), new BigDecimal(100)));

            if (this.claimInsure.getOriginCountryCode().equals("TH")) {
                //ถ้าเป็น export premium * 0.04 ถ้า 1-5 บวก x+1 ถ้านอกเหนื่อ +6
                if (debitNote.getStampDuty().doubleValue() >= 0 && debitNote.getStampDuty().doubleValue() <= 5) {
                    LOGGER.debug("Added 1 to StampDuty");
                    debitNote.setStampDuty(debitNote.getStampDuty().add(new BigDecimal(BigInteger.ONE)));
                } else {
                    LOGGER.debug("Added 6 to StampDuty");
                    debitNote.setStampDuty(debitNote.getStampDuty().add(new BigDecimal(5)));
                }
            }
            //Premium + StampDuty
            debitNote.setTotal(this.claimInsure.getPremiumRate().add(debitNote.getStampDuty()));

            //Vat = Premium + Stamp * 7%
            debitNote.setVat(WebUtils.divideRoundUp(WebUtils.mutilplyRoundUp(this.claimInsure.getPremiumRate().add(debitNote.getStampDuty()),
                    new BigDecimal(7)), new BigDecimal(100)));

            if (this.claimInsure.getOriginCountryCode().equals("TH")) {
                debitNote.setVat(BigDecimal.ZERO);
            }

            debitNote.setGrandTotal(debitNote.getTotal().add(debitNote.getVat()));
            debitNote.setTypeOfPolicy(dropdownFactory.ddlConf().get("type_of_policy"));
            debitNote.setPolicyNo(this.claimInsure.getPolicyNumber());
            debitNote.setWarrantyFrom(this.claimInsure.getShipmentDate());
            debitNote.setCompanyLogoURL(JsfUtil.getFullURI() + RESOURCES_LOGO);
            
            return debitNote;
    }
    
    public StreamedContent getDebitNote() {
        LOGGER.debug("export Debit Note...");

        try {
            DebitNote debitNote = getDebitNoteBean();

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

    public DropDownList findDropDown(String key) {
        if (this.getCurrencyTypeList() == null) {
            return null;
        }
        for (DropDownList ddl : this.getCurrencyTypeList()) {
            if (ddl.getValue().equals(key)) {
                return ddl;
            }
        }
        return null;
    }

    public void onCurrencyChange() {
        LOGGER.debug("this.claimInsure.getCurrencyType() : {}", this.claimInsure.getCurrencyType());
        DropDownList ddl = findDropDown(this.claimInsure.getCurrencyType());
        if (ddl != null) {
            this.claimInsure.setExchangeRate(new BigDecimal(ddl.getValue2()));
        }
        calAmountOfInsurance();
        onRateScheduleChange();
    }

    public void onRateScheduleChange() {
        //Change from local amount of insurance
        LOGGER.debug("InsuredValue : {}", this.claimInsure.getInsuredValue());
        LOGGER.debug("Rate  : {}", this.claimInsure.getRate());
        LOGGER.debug("amountOfInsurance  : {}", this.claimInsure.getAmountOfInsurance());
        LOGGER.debug("valuation  : {}", this.claimInsure.getValuation());

        if (this.claimInsure.getValuation() == null || this.claimInsure.getValuation().isEmpty()) {
            this.claimInsure.setAmountOfInsurance(null);
            this.claimInsure.setInsuredValue(null);
        }

        if (this.claimInsure.getInsuredValue() == null || this.claimInsure.getRate() == null || this.claimInsure.getRate() == null) {
            this.claimInsure.setPremiumRate(null);
            return;
        }
        BigDecimal mul = this.claimInsure.getInsuredValue().multiply(this.claimInsure.getRate());
        this.claimInsure.setPremiumRate(mul.divide(new BigDecimal("100")));
        this.claimInsure.setPremiumRate(this.claimInsure.getPremiumRate().setScale(0, BigDecimal.ROUND_HALF_UP));
        LOGGER.debug("this.claimInsure.setPremiumRate : " + this.claimInsure.getPremiumRate());
    }

    public void calAmountOfInsurance() {
        //var amountOfIns = ((inv) * valuat) / 100;
        try {
            if (this.claimInsure.getInvoiceValue() != null && this.claimInsure.getValuation() != null) {
                this.claimInsure.setAmountOfInsurance((this.claimInsure.getInvoiceValue().multiply(
                        new BigDecimal(this.claimInsure.getValuation()).add(new BigDecimal(100)))).divide(new BigDecimal(100)));
            }
        } catch (Exception ex) {
        }

        try {
            if (this.claimInsure.getAmountOfInsurance() != null && this.claimInsure.getExchangeRate() != null) {
                this.claimInsure.setInsuredValue(this.claimInsure.getAmountOfInsurance().multiply(this.claimInsure.getExchangeRate()).setScale(0, BigDecimal.ROUND_HALF_UP));
            }
        } catch (Exception ex) {
        }

        try {
            this.onRateScheduleChange();
        } catch (Exception ex) {
        }
    }

    public void onMethodOfTransportChange() {
        if (this.claimInsure.getMethodOfTransportId() == null) {
            return;
        }
        if (AIR == this.claimInsure.getMethodOfTransportId()) {
            this.minShipmentDate = DateTimeUtils.getInstance().add(this.claimInsure.getIssueDate(), -SHIPMENT_AIR_BEFORE_DATE);
        } else if (VESSEL == this.claimInsure.getMethodOfTransportId()) {
            this.minShipmentDate = DateTimeUtils.getInstance().add(this.claimInsure.getIssueDate(), -SHIPMENT_VESSEL_BEFORE_DATE);
        } else if (TRUCK == this.claimInsure.getMethodOfTransportId()) {
            this.minShipmentDate = DateTimeUtils.getInstance().add(this.claimInsure.getIssueDate(), -SHIPMENT_TRUCK_BEFORE_DATE);
        }

        if (this.claimInsure.getShipmentDate() != null && this.claimInsure.getShipmentDate().before(this.minShipmentDate)) {
            this.claimInsure.setShipmentDate(null);
        }
        LOGGER.debug("maxShipmentDate : {}", this.minShipmentDate);
    }

    public void onClaimSurveyorChange(AjaxBehaviorEvent actionEvent) {
        LOGGER.debug("onClaimSurveyorChange : {}", this.claimInsure.getClaimSurveyorId());
        Integer surveyorId = this.claimInsure.getClaimSurveyorId();
        if (surveyorId == null) {
            this.claimInsure.setClaimPayableAt(null);
            this.claimInsure.setClaimPayableAtCompany(null);
            this.claimInsure.setClaimPayableAtAddress(null);
            this.claimInsure.setClaimPayableAtTel(null);
            this.claimInsure.setClaimPayableAtFax(null);
            return;
        }
        //Find syrveyor detail
        Surveyors surveyors = surveyorService.findById(surveyorId);
        if (surveyors == null) {
            LOGGER.warn("surveyors is null should not be is null!!");
            this.claimInsure.setClaimPayableAt(null);
            this.claimInsure.setClaimPayableAtCompany(null);
            this.claimInsure.setClaimPayableAtAddress(null);
            this.claimInsure.setClaimPayableAtTel(null);
            this.claimInsure.setClaimPayableAtFax(null);
            return;
        }
        this.claimInsure.setClaimPayableAt(surveyors.getCompany().trim() + "\n " + surveyors.getAddress().trim());
        this.claimInsure.setClaimPayableAtCompany(StringEscapeUtils.unescapeEcmaScript(surveyors.getCompany().trim()));
        this.claimInsure.setClaimPayableAtAddress(StringEscapeUtils.unescapeEcmaScript(surveyors.getAddress().trim()));
        if (surveyors.getTel1() != null && !surveyors.getTel1().trim().isEmpty()) {
            this.claimInsure.setClaimPayableAtTel("Tel " + StringEscapeUtils.unescapeEcmaScript(surveyors.getTel1().trim()));
        }
        if (surveyors.getFax1() != null && !surveyors.getFax1().trim().isEmpty()) {
            this.claimInsure.setClaimPayableAtFax("Fax " + StringEscapeUtils.unescapeEcmaScript(surveyors.getFax1().trim()));
        }
    }

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

    public SurveyorService getSurveyorService() {
        return surveyorService;
    }

    public void setSurveyorService(SurveyorService surveyorService) {
        this.surveyorService = surveyorService;
    }

    public GennericService<OpenPolicy> getValuationService() {
        return valuationService;
    }

    public void setValuationService(GennericService<OpenPolicy> valuationService) {
        this.valuationService = valuationService;
    }

    public XMLService<Rss> getxMLService() {
        return xMLService;
    }

    public void setxMLService(XMLService<Rss> xMLService) {
        this.xMLService = xMLService;
    }

    public BigDecimal getMaxOfInsureValue() {
        return maxOfInsureValue;
    }

    public void setMaxOfInsureValue(BigDecimal maxOfInsureValue) {
        this.maxOfInsureValue = maxOfInsureValue;
    }

    public float getDefaultMaxInsureValue() {
        return DEFAULT_MAX_INSURE_VALUE.floatValue();
    }

    public OpenPolicyService getOpenPolicyService() {
        return openPolicyService;
    }

    public void setOpenPolicyService(OpenPolicyService openPolicyService) {
        this.openPolicyService = openPolicyService;
    }

    public Date getMinShipmentDate() {
        return minShipmentDate;
    }

    public void setMinShipmentDate(Date minShipmentDate) {
        this.minShipmentDate = minShipmentDate;
    }
}
