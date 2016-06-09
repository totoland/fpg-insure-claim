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
import com.totoland.db.bean.Valuation;
import com.totoland.db.common.entity.DropDownList;
import com.totoland.db.entity.ProductRate;
import com.totoland.db.entity.ViewProductRate;
import com.totoland.web.controller.BaseController;
import com.totoland.web.factory.DropdownFactory;
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

    private ProductRateCriteria criteria;
    private List<ProductRate> dataSource;

    private List<DropDownList> ddlCustomer;
    private List<DropDownList> ddlProduct;
    private ProductRate selectedItem;

    private List<Valuation> valuations;
    private String valueationShortName;
    private String valueationPerCen;

    @PostConstruct
    public void init() {
        LOGGER.debug("init...");
        this.ddlCustomer = dropdownFactory.ddlCustomer();
        this.ddlProduct = dropdownFactory.ddlProduct();
        this.criteria = new ProductRateCriteria(null, null);
        this.selectedItem = new ProductRate();
        this.dataSource = null;
        this.valuations = null;
    }

    public void search() {
        LOGGER.debug("search with : {}", getCriteria());
        dataSource = rateManagementService.findByCriteria(getCriteria());
    }

    public void initCreate() {
        LOGGER.debug("initCreate");
        this.selectedItem = new ProductRate();
        this.valuations = null;
    }

    public void initEdit(ProductRate viewItem) {
        this.selectedItem = viewItem;

        valuations = toListValuation(viewItem.getValuation());

        LOGGER.debug("initEdit");
    }

    private List<Valuation> toListValuation(String valuation) {
        if (valuation == null) {
            return null;
        }

        try {
            return new Gson().fromJson(valuation, new TypeToken<List<Valuation>>() {
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

    public void deleteValuationItem(Valuation selectedItem) {
        LOGGER.debug("selectedItem : {}", selectedItem);
        if (valuations == null) {
            return;
        }

        valuations.remove(selectedItem);
    }

    public void addNewValuation() {
        
        if((valueationShortName == null || valueationShortName.trim().isEmpty())
                || (valueationPerCen == null || valueationPerCen.trim().isEmpty())){
            addError(":form:msgNewValuation",MessageUtils.REQUIRE_GENERIC());
            return;
        }
        
        if (valuations == null) {
            valuations = new ArrayList<>();
        }

        Valuation valuation = new Valuation(valueationShortName, valueationPerCen);

        if (valuations.contains(valuation)) {
            addError(":form:msgNewValuation","Found " + valueationShortName + " in list.");
            return;
        }

        valuations.add(valuation);

        JsfUtil.closeDialog("dlgNewValuation");
    }

    public void addEditValuation() {
        if((valueationShortName == null || valueationShortName.trim().isEmpty())
                || (valueationPerCen == null || valueationPerCen.trim().isEmpty())){
            addError(":form:msgEditValuation",MessageUtils.REQUIRE_GENERIC());
            return;
        }

        if (valuations == null) {
            valuations = new ArrayList<>();
        }

        Valuation valuation = new Valuation(valueationShortName, valueationPerCen);

        if (valuations.contains(valuation)) {
            addError(":form:msgEditValuation","Found " + valueationShortName + " in list.");
            return;
        }

        valuations.add(valuation);

        JsfUtil.closeDialog("dlgEditValuation");
    }
    
    public void save() {
        try {
            if(valuations == null || valuations.isEmpty()){
                addError(":form:gwValuationNew","Please add Valuation");
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

            rateManagementService.create(selectedItem);
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
            if(valuations == null || valuations.isEmpty()){
                addError(":form:gwValuationEdit","Please add Valuation");
                return;
            }
            selectedItem.setValuation(new Gson().toJson(valuations));
            rateManagementService.edit(selectedItem);
            LOGGER.debug("edit : {}", this.selectedItem);
            addInfo(MessageUtils.SAVE_SUCCESS());
            JsfUtil.hidePopup("dlgEditRateMnm");
            search();
        } catch (Exception ex) {
            LOGGER.error("", ex);
            addError(MessageUtils.SAVE_NOT_SUCCESS());
        }
    }

    public void delete(ViewProductRate viewItem) {
        try {
            this.selectedItem = new ProductRate(viewItem.getProductRateId(),
                    viewItem.getProductRate(), viewItem.getCustomerId(), viewItem.getProductId());
            rateManagementService.remove(selectedItem);
            addInfo(MessageUtils.DELETE_SUCCESS());
            search();
        } catch (Exception ex) {
            LOGGER.error("", ex);
            addError(MessageUtils.DELETE_NOT_SUCCESS());
        }
    }

    public void close() {
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

    public List<Valuation> getValuations() {
        return valuations;
    }

    public void setValuations(List<Valuation> valuations) {
        this.valuations = valuations;
    }
}
