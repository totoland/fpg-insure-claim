/*
 * Copyright (C) 2016 totoland
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA.
 */
package com.totoland.web.controller.rate;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.totoland.db.bean.OpenPolicyCriteria;
import com.totoland.db.bean.ValuationBean;
import com.totoland.db.common.entity.DropDownList;
import com.totoland.db.entity.ConditionsOfCover;
import com.totoland.db.entity.ProductRate;
import com.totoland.db.entity.OpenPolicy;
import com.totoland.web.controller.BaseController;
import com.totoland.web.factory.DropdownFactory;
import com.totoland.web.service.ConditionsOfCoverService;
import com.totoland.web.service.GennericService;
import com.totoland.web.service.OpenPolicyService;
import com.totoland.web.utils.JsfUtil;
import com.totoland.web.utils.MessageUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
@ManagedBean
@ViewScoped
public class OpenPolicyManagementController extends BaseController {

    private static final long serialVersionUID = 4696958820488830897L;
    private static final Logger LOGGER =
            LoggerFactory.getLogger(OpenPolicyManagementController.class);

    @ManagedProperty("#{dropdownFactory}")
    private DropdownFactory dropdownFactory;
    @ManagedProperty("#{gennericService}")
    private GennericService<OpenPolicy> valuationService;
    @ManagedProperty("#{conditionsOfCoverService}")
    private ConditionsOfCoverService conditionsOfCoverService;
    @ManagedProperty("#{openPolicyService}")
    private OpenPolicyService openPolicyService;

    private OpenPolicyCriteria criteria;
    private List<OpenPolicy> dataSource;

    private List<DropDownList> ddlCustomer;
    private List<DropDownList> ddlProduct;
    private ProductRate selectedItem;

    private List<ValuationBean> valuations;
    private String valueationShortName;
    private String valueationPerCen;

    private ConditionsOfCover conditionsOfCover;
    private String brokerName;

    @PostConstruct
    public void init() {
        LOGGER.debug("init...");
        this.ddlCustomer = dropdownFactory.ddlCustomer();
        this.ddlProduct = dropdownFactory.ddlProduct();
        this.criteria = new OpenPolicyCriteria(null, null);
        this.selectedItem = new ProductRate();
        this.dataSource = null;
        this.valuations = null;
        this.conditionsOfCover = null;
        this.brokerName = null;
    }

    public void search() {
        LOGGER.debug("search with : {}", getCriteria());
        dataSource = openPolicyService.findByCriteria(getCriteria());
    }

    public void initCreate() {
        LOGGER.debug("initCreate");
        this.selectedItem = new ProductRate();
        this.valuations = null;
        this.conditionsOfCover = null;
        this.brokerName = null;
    }

    public void initEdit(OpenPolicy viewItem) {
        this.selectedItem.setOpenPolicyNo(viewItem.getOpenPolicyNo());

        Map<String, Object> params = new HashMap<>();
        params.put("openPolicyNo", viewItem.getOpenPolicyNo());

        List<OpenPolicy> list = valuationService.findByDynamicField(OpenPolicy.class, params);

        if (list != null && !list.isEmpty()) {
            valuations = toListValuation(list.get(0).getValuationData());
            this.selectedItem.setBrokerName(list.get(0).getBrokerName());
            this.selectedItem.setClausesAir(list.get(0).getClausesAir());
            this.selectedItem.setClausesTruck(list.get(0).getClausesTruck());
            this.selectedItem.setClausesVessel(list.get(0).getClausesVessel());
        } else {
            valuations = null;
        }
        LOGGER.debug("initEdit");
    }

    private List<ValuationBean> toListValuation(String valuation) {
        if (valuation == null) {
            return null;
        }

        try {
            return new Gson().fromJson(valuation,
                    new TypeToken<List<ValuationBean>>() {}.getType());
        } catch (Exception ex) {
            LOGGER.error("Cannot parse to json", ex);
            return null;
        }
    }

    public void initValuation() {
        this.valueationPerCen = null;
        this.valueationShortName = null;
    }

    public void deleteValuationItem(ValuationBean selectedItem) {
        LOGGER.debug("selectedItem : {}", selectedItem);
        if (valuations == null) {
            return;
        }

        valuations.remove(selectedItem);
    }

    public void addNewValuation() {

        if ((valueationShortName == null || valueationShortName.trim().isEmpty())
                || (valueationPerCen == null || valueationPerCen.trim().isEmpty())) {
            addError(":form:msgNewValuation", MessageUtils.REQUIRE_GENERIC());
            return;
        }

        if (valuations == null) {
            valuations = new ArrayList<>();
        }

        ValuationBean valuation = new ValuationBean(valueationShortName, valueationPerCen);

        if (valuations.contains(valuation)) {
            addError(":form:msgNewValuation", "Found " + valueationShortName + " in list.");
            return;
        }

        valuations.add(valuation);

        JsfUtil.closeDialog("dlgNewValuation");
    }

    public void addEditValuation() {
        if ((valueationShortName == null || valueationShortName.trim().isEmpty())
                || (valueationPerCen == null || valueationPerCen.trim().isEmpty())) {
            addError(":form:msgEditValuation", MessageUtils.REQUIRE_GENERIC());
            return;
        }

        if (valuations == null) {
            valuations = new ArrayList<>();
        }

        ValuationBean valuation = new ValuationBean(valueationShortName, valueationPerCen);

        if (valuations.contains(valuation)) {
            addError(":form:msgEditValuation", "Found " + valueationShortName + " in list.");
            return;
        }

        valuations.add(valuation);

        JsfUtil.closeDialog("dlgEditValuation");
    }

    public void save() {
        try {
            if (valuations == null || valuations.isEmpty()) {
                addError(":form:gwValuationNew", "Please add Valuation");
                return;
            }

            List<OpenPolicy> listProdcutRate =
                    openPolicyService.findByCriteria(new OpenPolicyCriteria(
                            selectedItem.getProductId(), selectedItem.getOpenPolicyNo()));

            if (listProdcutRate != null && !listProdcutRate.isEmpty()) {
                addError("newProductRateMsg",
                        MessageUtils.getResourceBundleString("product_rate_ins_dupp"));
                return;
            }

            selectedItem.setValuation(new Gson().toJson(valuations));

            OpenPolicy open = new OpenPolicy();
            open.setValuationData(selectedItem.getValuation());
            open.setOpenPolicyNo(selectedItem.getOpenPolicyNo());
            open.setBrokerName(selectedItem.getBrokerName());
            open.setClausesAir(selectedItem.getClausesAir());
            open.setClausesTruck(selectedItem.getClausesTruck());
            open.setClausesVessel(selectedItem.getClausesVessel());

            openPolicyService.edit(open);

            LOGGER.debug("save : {}", this.selectedItem);
            addInfo(MessageUtils.SAVE_SUCCESS());
            JsfUtil.hidePopup("dlgNewRateMnm");
            search();
        } catch (Exception ex) {
            LOGGER.error("", ex);
            addError(MessageUtils.SAVE_NOT_SUCCESS());
        }
    }

    public void edit() {
        try {
            if (valuations == null || valuations.isEmpty()) {
                addError(":form:gwValuationEdit", "Please add Valuation");
                return;
            }
            selectedItem.setValuation(new Gson().toJson(valuations));

            OpenPolicy open = new OpenPolicy();
            open.setValuationData(selectedItem.getValuation());
            open.setOpenPolicyNo(selectedItem.getOpenPolicyNo());
            open.setBrokerName(selectedItem.getBrokerName());
            open.setClausesAir(selectedItem.getClausesAir());
            open.setClausesTruck(selectedItem.getClausesTruck());
            open.setClausesVessel(selectedItem.getClausesVessel());

            openPolicyService.edit(open);

            LOGGER.debug("edit : {}", this.selectedItem);
            addInfo(MessageUtils.SAVE_SUCCESS());
            JsfUtil.hidePopup("dlgEditRateMnm");
            search();
        } catch (Exception ex) {
            LOGGER.error("", ex);
            addError(MessageUtils.SAVE_NOT_SUCCESS());
        }
    }

    public void delete(OpenPolicy viewItem) {
        try {

            openPolicyService.remove(viewItem);

            OpenPolicy open = new OpenPolicy();
            open.setValuationData(selectedItem.getValuation());
            open.setOpenPolicyNo(selectedItem.getOpenPolicyNo());
            open.setBrokerName(selectedItem.getBrokerName());
            open.setClausesAir(selectedItem.getClausesAir());
            open.setClausesTruck(selectedItem.getClausesTruck());
            open.setClausesVessel(selectedItem.getClausesVessel());

            valuationService.remove(open);

            addInfo(MessageUtils.DELETE_SUCCESS());
            search();
        } catch (Exception ex) {
            LOGGER.error("", ex);
            addError(MessageUtils.DELETE_NOT_SUCCESS());
        }
    }

    public void refreshValuation() {
        this.selectedItem.getOpenPolicyNo();

        // Find valuation
        List<OpenPolicy> listProdcutRate = openPolicyService
                .findByCriteria(new OpenPolicyCriteria(null, selectedItem.getOpenPolicyNo()));

        if (listProdcutRate == null || listProdcutRate.isEmpty()) {
            valuations = null;
            conditionsOfCover = new ConditionsOfCover();
        } else {
            valuations = toListValuation(listProdcutRate.get(0).getValuationData());
            this.selectedItem.setBrokerName(listProdcutRate.get(0).getBrokerName());
            this.selectedItem.setClausesAir(listProdcutRate.get(0).getClausesAir());
            this.selectedItem.setClausesTruck(listProdcutRate.get(0).getClausesTruck());
            this.selectedItem.setClausesVessel(listProdcutRate.get(0).getClausesVessel());
        }
    }

    @Override
    public void resetForm() {
        LOGGER.debug("resetForm...");
        init();
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

    public List<DropDownList> getDdlProduct() {
        return ddlProduct;
    }

    public void setDdlProduct(List<DropDownList> ddlProduct) {
        this.ddlProduct = ddlProduct;
    }

    public OpenPolicyCriteria getCriteria() {
        return criteria;
    }

    public void setCriteria(OpenPolicyCriteria criteria) {
        this.criteria = criteria;
    }

    public List<OpenPolicy> getDataSource() {
        return dataSource;
    }

    public void setDataSource(List<OpenPolicy> dataSource) {
        this.dataSource = dataSource;
    }

    public ProductRate getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(ProductRate selectedItem) {
        this.selectedItem = selectedItem;
    }

    public String getValueationShortName() {
        return valueationShortName;
    }

    public void setValueationShortName(String valueationShortName) {
        this.valueationShortName = valueationShortName;
    }

    public String getValueationPerCen() {
        return valueationPerCen;
    }

    public void setValueationPerCen(String valueationPerCen) {
        this.valueationPerCen = valueationPerCen;
    }

    public List<ValuationBean> getValuations() {
        return valuations;
    }

    public void setValuations(List<ValuationBean> valuations) {
        this.valuations = valuations;
    }

    public GennericService<OpenPolicy> getValuationService() {
        return valuationService;
    }

    public void setValuationService(GennericService<OpenPolicy> valuationService) {
        this.valuationService = valuationService;
    }

    public ConditionsOfCoverService getConditionsOfCoverService() {
        return conditionsOfCoverService;
    }

    public void setConditionsOfCoverService(ConditionsOfCoverService conditionsOfCoverService) {
        this.conditionsOfCoverService = conditionsOfCoverService;
    }

    public ConditionsOfCover getConditionsOfCover() {
        return conditionsOfCover;
    }

    public void setConditionsOfCover(ConditionsOfCover conditionsOfCover) {
        this.conditionsOfCover = conditionsOfCover;
    }

    public String getBrokerName() {
        return brokerName;
    }

    public void setBrokerName(String brokerName) {
        this.brokerName = brokerName;
    }

    public OpenPolicyService getOpenPolicyService() {
        return openPolicyService;
    }

    public void setOpenPolicyService(OpenPolicyService openPolicyService) {
        this.openPolicyService = openPolicyService;
    }
}
