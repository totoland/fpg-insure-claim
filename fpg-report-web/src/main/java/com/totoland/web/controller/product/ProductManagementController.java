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
package com.totoland.web.controller.product;

import com.totoland.db.bean.ProductRateCriteria;
import com.totoland.db.common.entity.DropDownList;
import com.totoland.db.entity.Product;
import com.totoland.web.controller.BaseController;
import com.totoland.web.factory.DropdownFactory;
import com.totoland.web.service.ProductService;
import com.totoland.web.utils.JsfUtil;
import com.totoland.web.utils.MessageUtils;
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
@ViewScoped
@ManagedBean
public class ProductManagementController extends BaseController {

    private static final long serialVersionUID = 1774382748975652840L;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductManagementController.class);

    @ManagedProperty("#{dropdownFactory}")
    private DropdownFactory dropdownFactory;
    @ManagedProperty("#{productService}")
    private ProductService service;

    private ProductRateCriteria criteria;
    private List<Product> dataSource;

    private List<DropDownList> ddlProduct;
    private Product selectedItem;

    @PostConstruct
    public void init() {
        LOGGER.debug("init...");
        this.ddlProduct = dropdownFactory.ddlProduct();
        this.criteria = new ProductRateCriteria(null, null);
        this.selectedItem = new Product();
        this.dataSource = null;
    }

    public void search() {
        dataSource = service.findById(getCriteria().getProductId());
    }

    public void initCreate() {
        LOGGER.debug("initCreate");
        this.selectedItem = new Product();
    }

    public void initEdit(Product selectedItem) {
        this.selectedItem = selectedItem;
        LOGGER.debug("initEdit");
    }

    public void save() {
        try {
            //Check dupp
            Map<String,Object>paramsMap = new HashMap<>();
            paramsMap.put("productName", this.selectedItem.getProductName());
            
            List<Product>listProd = service.findByDynamicField(Product.class, paramsMap);
            if(listProd !=null && !listProd.isEmpty()){
                LOGGER.debug("Dupplicate product name");
                addError("Dupplicate product name "+this.selectedItem.getProductName());
                return;
            }
            
            paramsMap.clear();
            paramsMap.put("productCode", this.selectedItem.getProductCode());
            
            List<Product>listProds = service.findByDynamicField(Product.class, paramsMap);
            if(listProds !=null && !listProds.isEmpty()){
                LOGGER.debug("Dupplicate product code");
                addError("Dupplicate product code "+this.selectedItem.getProductCode());
                return;
            }
            
            service.create(selectedItem);
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
            service.edit(selectedItem);
            LOGGER.debug("edit : {}", this.selectedItem);
            addInfo(MessageUtils.SAVE_SUCCESS());
            JsfUtil.hidePopup("dlgEditRateMnm");
            search();
        } catch (Exception ex) {
            LOGGER.error("", ex);
            addError(MessageUtils.SAVE_NOT_SUCCESS());
        }
    }
    
    public void delete(Product selectedItem){
        try {
            service.remove(selectedItem);
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
    
    public List<DropDownList> getDdlProduct() {
        return ddlProduct;
    }

    public void setDdlProduct(List<DropDownList> ddlProduct) {
        this.ddlProduct = ddlProduct;
    }

    public ProductService getService() {
        return service;
    }

    public void setService(ProductService service) {
        this.service = service;
    }

    public ProductRateCriteria getCriteria() {
        return criteria;
    }

    public void setCriteria(ProductRateCriteria criteria) {
        this.criteria = criteria;
    }

    public List<Product> getDataSource() {
        return dataSource;
    }

    public void setDataSource(List<Product> dataSource) {
        this.dataSource = dataSource;
    }

    public Product getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(Product selectedItem) {
        this.selectedItem = selectedItem;
    }

}
