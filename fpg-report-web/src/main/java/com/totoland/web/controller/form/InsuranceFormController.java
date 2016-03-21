/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.totoland.web.controller.form;

import com.totoland.db.common.entity.DropDownList;
import com.totoland.web.controller.BaseController;
import com.totoland.web.factory.DropdownFactory;
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
public class InsuranceFormController extends BaseController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(InsuranceFormController.class);

    private String insureType = "";
    private List<DropDownList> insureTypeList;

    @ManagedProperty("#{dropdownFactory}")
    private DropdownFactory dropdownFactory;

    @PostConstruct
    public void init(){
        this.insureTypeList = getInsureTypeList();
    }
    
    @Override
    public void resetForm() {

    }
    
    public void selectInsureType(String selectedInsureType){
        LOGGER.debug("selectInsureType : {}",selectedInsureType);
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

}