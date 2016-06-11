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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package com.totoland.web.controller.rate;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.totoland.db.bean.ProductRateCriteria;
import com.totoland.db.bean.ValuationBean;
import com.totoland.db.common.entity.DropDownList;
import com.totoland.db.entity.ConditionsOfCover;
import com.totoland.db.entity.ProductRate;
import com.totoland.db.entity.Valuation;
import com.totoland.web.controller.BaseController;
import com.totoland.web.factory.DropdownFactory;
import com.totoland.web.service.ConditionsOfCoverService;
import com.totoland.web.service.GennericService;
import com.totoland.web.service.RateManagementService;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(OpenPolicyManagementController.class);

    @ManagedProperty("#{dropdownFactory}")
    private DropdownFactory dropdownFactory;
    @ManagedProperty("#{rateManagementService}")
    private RateManagementService rateManagementService;
    @ManagedProperty("#{gennericService}")
    private GennericService<Valuation> valuationService;
    @ManagedProperty("#{conditionsOfCoverService}")
    private ConditionsOfCoverService conditionsOfCoverService;

    private ProductRateCriteria criteria;
    private List<ProductRate> dataSource;

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
        this.criteria = new ProductRateCriteria(null, null);
        this.selectedItem = new ProductRate();
        this.dataSource = null;
        this.valuations = null;
        this.conditionsOfCover = null;
        this.brokerName = null;
    }

    public void search() {
        LOGGER.debug("search with : {}", getCriteria());
        dataSource = rateManagementService.findByCriteria(getCriteria());
    }

    public void initCreate() {
        LOGGER.debug("initCreate");
        this.selectedItem = new ProductRate();
        this.valuations = null;
        this.conditionsOfCover = null;
        this.brokerName = null;
    }

    public void initEdit(ProductRate viewItem) {
        this.selectedItem = viewItem;

        Map<String, Object> params = new HashMap<>();
        params.put("openPolicyNo", viewItem.getOpenPolicyNo());

        List<Valuation> list = valuationService.findByDynamicField(Valuation.class, params);

        if (list != null && !list.isEmpty()) {
            valuations = toListValuation(list.get(0).getValuationData());
            this.selectedItem.setBrokerName(list.get(0).getBrokerName());
            this.selectedItem.setClausesAir(list.get(0).getClausesAir());
            this.selectedItem.setClausesTruck(list.get(0).getClausesTruck());
            this.selectedItem.setClausesVessel(list.get(0).getClausesVessel());
            this.selectedItem.setSubjectMatterInsured(list.get(0).getSbujectMatterInsered());
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
            return new Gson().fromJson(valuation, new TypeToken<List<ValuationBean>>() {
            }.getType());
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

            Map<String, Object> paramsMap = new HashMap<>();
            paramsMap.put("openPolicyNo", selectedItem.getOpenPolicyNo());
            paramsMap.put("productId", selectedItem.getProductId());
            List<ProductRate> listProdcutRate = rateManagementService.findByDynamicField(ProductRate.class, paramsMap);

            if (listProdcutRate != null && !listProdcutRate.isEmpty()) {
                addError("newProductRateMsg", MessageUtils.getResourceBundleString("product_rate_ins_dupp"));
                return;
            }

            selectedItem.setValuation(new Gson().toJson(valuations));

            Valuation valuation = new Valuation();
            valuation.setValuationData(selectedItem.getValuation());
            valuation.setOpenPolicyNo(selectedItem.getOpenPolicyNo());
            valuation.setBrokerName(selectedItem.getBrokerName());
            valuation.setClausesAir(selectedItem.getClausesAir());
            valuation.setClausesTruck(selectedItem.getClausesTruck());
            valuation.setClausesVessel(selectedItem.getClausesVessel());
            valuation.setSbujectMatterInsered(selectedItem.getSubjectMatterInsured());

            rateManagementService.createWithValuation(selectedItem, valuation);

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

            Valuation valuation = new Valuation();
            valuation.setValuationData(selectedItem.getValuation());
            valuation.setOpenPolicyNo(selectedItem.getOpenPolicyNo());
            valuation.setBrokerName(selectedItem.getBrokerName());
            valuation.setClausesAir(selectedItem.getClausesAir());
            valuation.setClausesTruck(selectedItem.getClausesTruck());
            valuation.setClausesVessel(selectedItem.getClausesVessel());
            valuation.setSbujectMatterInsered(selectedItem.getSubjectMatterInsured());

            rateManagementService.updateWithValuation(selectedItem, valuation);

            LOGGER.debug("edit : {}", this.selectedItem);
            addInfo(MessageUtils.SAVE_SUCCESS());
            JsfUtil.hidePopup("dlgEditRateMnm");
            search();
        } catch (Exception ex) {
            LOGGER.error("", ex);
            addError(MessageUtils.SAVE_NOT_SUCCESS());
        }
    }

    public void delete(ProductRate viewItem) {
        try {
            this.selectedItem = new ProductRate(viewItem.getProductRateId(),
                    viewItem.getProductRate(), viewItem.getCustomerId(), viewItem.getProductId());
            rateManagementService.remove(selectedItem);

            Valuation valuation = new Valuation();
            valuation.setValuationData(selectedItem.getValuation());
            valuation.setOpenPolicyNo(selectedItem.getOpenPolicyNo());
            valuation.setBrokerName(selectedItem.getBrokerName());
            valuation.setClausesAir(selectedItem.getClausesAir());
            valuation.setClausesTruck(selectedItem.getClausesTruck());
            valuation.setClausesVessel(selectedItem.getClausesVessel());
            valuation.setSbujectMatterInsered(selectedItem.getSubjectMatterInsured());

            valuationService.remove(valuation);

            addInfo(MessageUtils.DELETE_SUCCESS());
            search();
        } catch (Exception ex) {
            LOGGER.error("", ex);
            addError(MessageUtils.DELETE_NOT_SUCCESS());
        }
    }

    public void refreshValuation() {
        this.selectedItem.getOpenPolicyNo();
        //Find valuation
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("openPolicyNo", selectedItem.getOpenPolicyNo());
        List<ProductRate> listProdcutRate = rateManagementService.findByDynamicField(ProductRate.class, paramsMap);

        if (listProdcutRate == null || listProdcutRate.isEmpty()) {
            valuations = null;
        } else {
            valuations = toListValuation(listProdcutRate.get(0).getValuation());
        }

        //Condition of cover
        Map<String, Object> params = new HashMap<>();
        params.put("openPolicyNo", selectedItem.getOpenPolicyNo());

        List<Valuation> listCondition = valuationService.findByDynamicField(Valuation.class, params);
        if (listCondition == null || listCondition.isEmpty()) {
            conditionsOfCover = new ConditionsOfCover();
        } else {
            this.selectedItem.setBrokerName(listCondition.get(0).getBrokerName());
            this.selectedItem.setClausesAir(listCondition.get(0).getClausesAir());
            this.selectedItem.setClausesTruck(listCondition.get(0).getClausesTruck());
            this.selectedItem.setClausesVessel(listCondition.get(0).getClausesVessel());
            this.selectedItem.setSubjectMatterInsured(listCondition.get(0).getSbujectMatterInsered());
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

    public RateManagementService getRateManagementService() {
        return rateManagementService;
    }

    public void setRateManagementService(RateManagementService rateManagementService) {
        this.rateManagementService = rateManagementService;
    }

    public ProductRateCriteria getCriteria() {
        return criteria;
    }

    public void setCriteria(ProductRateCriteria criteria) {
        this.criteria = criteria;
    }

    public List<ProductRate> getDataSource() {
        return dataSource;
    }

    public void setDataSource(List<ProductRate> dataSource) {
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

    public GennericService<Valuation> getValuationService() {
        return valuationService;
    }

    public void setValuationService(GennericService<Valuation> valuationService) {
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
}
