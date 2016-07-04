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
package com.totoland.web.controller.report;

import com.totoland.db.bean.CertifaicationCriteria;
import com.totoland.db.bean.ViewCertificate;
import com.totoland.db.common.entity.DropDownList;
import com.totoland.db.enums.InsureState;
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
public class CertificateActiveReport extends BaseController {

    private static final long serialVersionUID = 6876855853371600082L;

    private static final Logger LOGGER = LoggerFactory.getLogger(CertificateActiveReport.class);
    
    @ManagedProperty(value = "#{certificateService}")
    private CertificateService certificateService;
    @ManagedProperty(value = "#{dropdownFactory}")
    private DropdownFactory dropdownFactory;

    private List<ViewCertificate> listViewCertificate;
    private CertifaicationCriteria criteria;
    private List<DropDownList>insureNameList;
    
    @PostConstruct
    @Override
    public void init(){
        this.criteria = new CertifaicationCriteria();
        this.insureNameList = dropdownFactory.ddlInsureName();
        this.listViewCertificate = null;
    }
    
    public void search(){
        this.criteria.setStatus(String.valueOf(InsureState.PRINT_CERT.getState()));
        LOGGER.debug("search with : {}", getCriteria());
        listViewCertificate = certificateService.searchCertificate(getCriteria());
        LOGGER.debug("listViewCertificate {}",this.listViewCertificate);
    }
    
    public String findMethodTransport(String methodType) {
        List<DropDownList> dropDownLists = dropdownFactory.ddlMethodOfTransport();
        for (DropDownList ddl : dropDownLists) {
            if (ddl.getValue().equals(methodType)) {
                return ddl.getName();
            }
        }

        return null;
    }
    
    @Override
    public void resetForm() {
        init();
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

    public List<DropDownList> getInsureNameList() {
        return insureNameList;
    }

    public void setInsureNameList(List<DropDownList> insureNameList) {
        this.insureNameList = insureNameList;
    }
}
