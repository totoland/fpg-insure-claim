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

import com.totoland.db.common.entity.DropDownList;
import com.totoland.db.entity.KeyMatch;
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
@ManagedBean
@ViewScoped
public class RateManagementController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RateManagementController.class);

    @ManagedProperty("#{dropdownFactory}")
    private DropdownFactory dropdownFactory;

    private KeyMatch keyMatch;

    private List<DropDownList> ddlCustomer;
    private List<DropDownList> ddlProduct;

    @PostConstruct
    public void init() {
        LOGGER.debug("init...");
        this.ddlCustomer = dropdownFactory.ddlCustomer();
        this.ddlProduct = dropdownFactory.ddlProduct();
    }

    public void search() {
    }

    public void prepareEdit() {
    }

    public void save() {
    }

    public void edit() {
    }

    public void close() {
    }

    @Override
    public void resetForm() {

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
    
    public KeyMatch getKeyMatch() {
        return keyMatch;
    }

    public void setKeyMatch(KeyMatch keyMatch) {
        this.keyMatch = keyMatch;
    }

    public List<DropDownList> getDdlProduct() {
        return ddlProduct;
    }

    public void setDdlProduct(List<DropDownList> ddlProduct) {
        this.ddlProduct = ddlProduct;
    }
}
