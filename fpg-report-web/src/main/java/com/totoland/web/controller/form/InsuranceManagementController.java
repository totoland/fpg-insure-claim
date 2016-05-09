package com.totoland.web.controller.form;

import com.totoland.db.bean.CertifaicationCriteria;
import com.totoland.db.bean.ViewCertificate;
import com.totoland.db.common.entity.DropDownList;
import com.totoland.web.controller.BaseController;
import com.totoland.web.factory.DropdownFactory;
import com.totoland.web.service.CertificateService;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author totoland
 */
@ViewScoped
@ManagedBean
public class InsuranceManagementController extends BaseController{

    private static final Logger LOGGER = LoggerFactory.getLogger(InsuranceManagementController.class);
    
    @ManagedProperty(value = "#{dropdownFactory}")
    private DropdownFactory dropdownFactory;
    private CertifaicationCriteria criteria;
    @ManagedProperty(value = "#{certificateService}")
    private CertificateService certificateService;
    
    private List<ViewCertificate>listViewCertificate;
    
    @PostConstruct
    public void init(){
        this.criteria = new CertifaicationCriteria();
        this.listViewCertificate = null;
    }
    
    public void search(){
        LOGGER.debug("search with : {}",getCriteria());
        listViewCertificate = certificateService.searchCertificate(getCriteria());
    }
    
    public String findMethodTransport(String methodType){
        List<DropDownList>dropDownLists = dropdownFactory.ddlInsureTypesList();
        for(DropDownList ddl : dropDownLists){
            if(ddl.getValue().equals(methodType)){
                return ddl.getName();
            }
        }
        
        return null;
    }
    
    public void prepareView(){
    
    }
    
    public void prepareEdit(){
    
    }
    
    public void edit(){
    
    }
    
    public void close(){
    
    }
    
    @Override
    public void resetForm() {
        
    }

    public List<DropDownList> getStatusTypeList() {
        return dropdownFactory.ddlStatus();
    }
    
    public CertifaicationCriteria getCriteria() {
        return criteria;
    }

    public void setCriteria(CertifaicationCriteria criteria) {
        this.criteria = criteria;
    }

    public DropdownFactory getDropdownFactory() {
        return dropdownFactory;
    }

    public void setDropdownFactory(DropdownFactory dropdownFactory) {
        this.dropdownFactory = dropdownFactory;
    }

    public CertificateService getCertificateService() {
        return certificateService;
    }

    public void setCertificateService(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    public List<ViewCertificate> getListViewCertificate() {
        return listViewCertificate;
    }

    public void setListViewCertificate(List<ViewCertificate> listViewCertificate) {
        this.listViewCertificate = listViewCertificate;
    }
}
