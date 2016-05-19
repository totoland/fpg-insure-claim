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
package com.totoland.web.controller.permission;

import com.totoland.db.enums.UserType;
import com.totoland.web.controller.BaseController;
import java.util.Arrays;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author totoland
 */
@ManagedBean
@SessionScoped
public class PermissionController extends BaseController {

    private static final long serialVersionUID = -6716974253898704825L;

    private static final Logger LOGGER = LoggerFactory.getLogger(PermissionController.class);

    public static final String[] CUSTOMER_PAGES = {
        "/pages/form/insuranceForm.xhtml",
        "/pages/form/insuranceManagement.xhtml"
    };

    public static final String[] OFFICIAL_PAGES = {
        "/pages/user/insuredManagement.xhtml",
        "/pages/officail/matchingKey.xhtml",
        "/pages/rate/rateManagement.xhtml",
        "/pages/admin/conditionsOfCover.xhtml",
        "/pages/report/certificateActiveReport.xhtml"
    };

    public static final String[] ADMIN_PAGES = {
        "/pages/form/insuranceForm.xhtml",
        "/pages/form/insuranceManagement.xhtml",
        "/pages/user/userManagement.xhtml",
        "/pages/user/insuredManagement.xhtml",
        "/pages/officail/matchingKey.xhtml",
        "/pages/rate/rateManagement.xhtml",
        "/pages/admin/conditionsOfCover.xhtml",
        "/pages/report/certificateActiveReport.xhtml"
    };

    public static final String[] ADMIN_MODULE = {
        "ADMIN", "CERTIFICATE_ISSUE", "REPORT"
    };

    public static final String[] OFFICIAL_MODULE = {
        "ADMIN", "REPORT"
    };

    public static final String[] CUSTOMER_MODULE = {
        "CERTIFICATE_ISSUE"
    };

    @PostConstruct
    public void init() {

    }

    @Override
    public void resetForm() {

    }

    public boolean isCanAccessModule(String module) {

        if (UserType.ADMIN.getId() == getUserAuthen().getUserGroupLvl()) {
            return Arrays.asList(ADMIN_MODULE).contains(module);
        } else if (UserType.OFFICIAL_USER.getId() == getUserAuthen().getUserGroupLvl()) {
            return Arrays.asList(OFFICIAL_MODULE).contains(module);
        } else if (UserType.CUSTOMER.getId() == getUserAuthen().getUserGroupLvl()) {
            return Arrays.asList(CUSTOMER_MODULE).contains(module);
        }

        return false;
    }

    public boolean isCanAccessPage(String page) {
        if (UserType.ADMIN.getId() == getUserAuthen().getUserGroupLvl()) {
            return Arrays.asList(ADMIN_PAGES).contains(page);
        } else if (UserType.OFFICIAL_USER.getId() == getUserAuthen().getUserGroupLvl()) {
            return Arrays.asList(OFFICIAL_PAGES).contains(page);
        } else if (UserType.CUSTOMER.getId() == getUserAuthen().getUserGroupLvl()) {
            return Arrays.asList(CUSTOMER_PAGES).contains(page);
        }

        return false;
    }

}
