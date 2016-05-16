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
package com.totoland.web.controller.user;

import com.totoland.db.entity.SvUser;
import com.totoland.web.controller.BaseController;
import com.totoland.web.service.UserService;
import com.totoland.web.utils.MessageUtils;
import com.totoland.web.utils.WebUtils;
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
public class ChangePasswordController extends BaseController {
    
    private static final long serialVersionUID = 6937574482438261553L;
    private static final Logger LOGGER = LoggerFactory.getLogger(ChangePasswordController.class);
    
    private String oldPassword;
    private String newPassword;
    private String confirmNewPassword;
    
    @ManagedProperty("#{userService}")
    private UserService userService;
    
    @PostConstruct
    public void init() {
        this.oldPassword = null;
        this.newPassword = null;
        this.confirmNewPassword = null;
    }
    
    public void changePassword() {
        
        if(!validatePassword()){
            addError(MessageUtils.getResourceBundleString("change_password_newpass_not_match_confirm"));
        }
        
        //Prepare entity
        SvUser user = userService.find(getUserAuthen().getUserId(), SvUser.class);
        //user should not null
        if (user == null) {
            addError(MessageUtils.getResourceBundleString("change_password_no_data_found"));
            return;
        }
        try {
            user.setPassword(WebUtils.encrypt(newPassword));
            userService.edit(user);
            addInfo(MessageUtils.getResourceBundleString("change_password_success"));
        } catch (Exception ex) {
            LOGGER.error("ERROR : When change password ",ex);
            addError(MessageUtils.getResourceBundleString("change_password_fail"));
        }
    }
    
    private boolean validatePassword(){
        return this.newPassword.equals(confirmNewPassword);
    }
    
    @Override
    public void resetForm() {
        init();
    }
    
    public String getOldPassword() {
        return oldPassword;
    }
    
    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
    
    public String getNewPassword() {
        return newPassword;
    }
    
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
    
    public String getConfirmNewPassword() {
        return confirmNewPassword;
    }
    
    public void setConfirmNewPassword(String confirmNewPassword) {
        this.confirmNewPassword = confirmNewPassword;
    }
    
    public UserService getUserService() {
        return userService;
    }
    
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
