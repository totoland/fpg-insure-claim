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
package com.totoland.web.controller.admin;

import com.totoland.db.bean.ConditionsOfCoverCriteria;
import com.totoland.db.common.entity.DropDownList;
import com.totoland.db.entity.ConditionsOfCover;
import com.totoland.db.entity.ViewConditionsOfCover;
import com.totoland.web.controller.BaseController;
import com.totoland.web.factory.DropdownFactory;
import com.totoland.web.service.ConditionsOfCoverService;
import com.totoland.web.utils.JsfUtil;
import com.totoland.web.utils.MessageUtils;
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
public class ConditionsOfCoverController extends BaseController {

    private static final long serialVersionUID = 7966791459090504794L;
    private static final Logger LOGGER = LoggerFactory.getLogger(ConditionsOfCoverController.class);

    @ManagedProperty("#{dropdownFactory}")
    private DropdownFactory dropdownFactory;
    @ManagedProperty(value = "#{conditionsOfCoverService}")
    private ConditionsOfCoverService conditionsOfCoverService;

    private List<DropDownList> ddlCustomer;
    private ConditionsOfCover selectedItem;
    private ConditionsOfCoverCriteria criteria;
    private List<ViewConditionsOfCover> dataSource;

    @PostConstruct
    public void init() {
        LOGGER.debug("init...");
        this.ddlCustomer = dropdownFactory.ddlCustomer();
        this.criteria = new ConditionsOfCoverCriteria();
        this.dataSource = null;
        this.selectedItem = new ConditionsOfCover();
    }

    public void search() {
        LOGGER.debug("search with : {}", getCriteria());
        this.dataSource = conditionsOfCoverService.findByCriteria(getCriteria());
    }

    public void initCreate() {
        this.selectedItem = new ConditionsOfCover();
    }

    public void initEdit(ViewConditionsOfCover viewItem) {
        this.selectedItem = new ConditionsOfCover();
        this.selectedItem.setConditionsCoverId(viewItem.getConditionsCoverId());
        this.selectedItem.setAirConditions(viewItem.getAirConditions());
        this.selectedItem.setConditionsCoverId(viewItem.getConditionsCoverId());
        this.selectedItem.setVesselConditions(viewItem.getVesselConditions());
        this.selectedItem.setTruckConditions(viewItem.getTruckConditions());
        this.selectedItem.setCustomerId(viewItem.getCustomerId());
    }

    public void save() {
        try {
            conditionsOfCoverService.create(this.selectedItem);
            LOGGER.debug("save : {}", this.selectedItem);
            addInfo(MessageUtils.SAVE_SUCCESS());
            JsfUtil.hidePopup("dlgNewConditionOfConver");
            search();
        } catch (Exception ex) {
            LOGGER.error("", ex);
            addError(MessageUtils.SAVE_NOT_SUCCESS());
        }
    }

    public void edit() {
        try {
            conditionsOfCoverService.edit(this.selectedItem);
            LOGGER.debug("save : {}", this.selectedItem);
            addInfo(MessageUtils.SAVE_SUCCESS());
            JsfUtil.hidePopup("dlgEditConditionOfConver");
            search();
        } catch (Exception ex) {
            LOGGER.error("", ex);
            addError(MessageUtils.SAVE_NOT_SUCCESS());
        }
    }

    public void delete(ViewConditionsOfCover viewItem) {
        try {
            this.selectedItem = new ConditionsOfCover();
            this.selectedItem.setAirConditions(viewItem.getAirConditions());
            this.selectedItem.setConditionsCoverId(viewItem.getConditionsCoverId());
            this.selectedItem.setVesselConditions(viewItem.getVesselConditions());
            this.selectedItem.setTruckConditions(viewItem.getTruckConditions());
            this.selectedItem.setCustomerId(viewItem.getCustomerId());
            this.selectedItem.setConditionsCoverId(viewItem.getConditionsCoverId());
            
            LOGGER.debug("delete : {}",this.selectedItem);
            
            conditionsOfCoverService.remove(this.selectedItem);
            addInfo(MessageUtils.DELETE_SUCCESS());
            search();
        } catch (Exception ex) {
            LOGGER.error("", ex);
            addError(MessageUtils.DELETE_NOT_SUCCESS());
        }
    }

    @Override
    public void resetForm() {
        LOGGER.debug("reset...");
        init();
    }

    public ConditionsOfCover getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(ConditionsOfCover selectedItem) {
        this.selectedItem = selectedItem;
    }

    public ConditionsOfCoverService getConditionsOfCoverService() {
        return conditionsOfCoverService;
    }

    public void setConditionsOfCoverService(ConditionsOfCoverService conditionsOfCoverService) {
        this.conditionsOfCoverService = conditionsOfCoverService;
    }

    public DropdownFactory getDropdownFactory() {
        return dropdownFactory;
    }

    public void setDropdownFactory(DropdownFactory dropdownFactory) {
        this.dropdownFactory = dropdownFactory;
    }

    public List<DropDownList> getDdlCustomer() {
        return ddlCustomer;
    }

    public void setDdlCustomer(List<DropDownList> ddlCustomer) {
        this.ddlCustomer = ddlCustomer;
    }

    public ConditionsOfCoverCriteria getCriteria() {
        return criteria;
    }

    public void setCriteria(ConditionsOfCoverCriteria criteria) {
        this.criteria = criteria;
    }

    public List<ViewConditionsOfCover> getDataSource() {
        return dataSource;
    }

    public void setDataSource(List<ViewConditionsOfCover> dataSource) {
        this.dataSource = dataSource;
    }
}
