/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.totoland.web.controller.login;

import com.totoland.db.authen.dao.AuthenDao;
import com.totoland.db.entity.ViewUser;
import com.totoland.db.enums.UserType;
import com.totoland.web.controller.BaseController;
import com.totoland.web.controller.LocaleBean;
import com.totoland.web.utils.WebUtils;
import com.totoland.web.utils.JsfUtil;
import com.totoland.web.utils.MessageUtils;
import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/**
 *
 * @author Totoland
 */
@ManagedBean
@SessionScoped
public class LoginController extends BaseController {

    private static final long serialVersionUID = 3291979904925054393L;
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
    private String userName;
    private String passWord;
    @ManagedProperty(value = "#{authenDao}")
    private AuthenDao authenDao;
    private ViewUser loginUser;
    private boolean loggedIn = false;
    @ManagedProperty("#{localeBean}")
    private LocaleBean localeBean;

    public String sessionId;

    @PostConstruct
    @Override
    public void init() {
        LOGGER.info("init");
    }

    public void loginProcess() throws IOException {

        sessionId = WebUtils.generateToken();

        MDC.put("reqId", sessionId);

        LOGGER.debug("loginProcess...");

        LOGGER.debug("userName : {} , passWord : {}", userName, "********");

        if (!validateLongin()) {
            addError("loggin fial!!");
            return;
        }

        /**
         * *
         * Authen Login
         */
        try {

            String nPassWord = WebUtils.encrypt(passWord);

            loginUser = getAuthenDao().loginUser(userName, nPassWord);

            localeBean.setLanguage("en", "US");

        } catch (Exception ex) {

            LOGGER.error("Cannot Authen : ", ex);

            addError(ex.getMessage());

            return;
        }

        if (loginUser == null) {

            addError(MessageUtils.getResourceBundleString("login.authen.fail"));

            LOGGER.warn("Login {} fail!!", loginUser);

            return;

        }

        if (!loginUser.getIsActive()) {

            addError(MessageUtils.getResourceBundleString("login.authen.disabled"));

            LOGGER.warn("Login {} block user was disbled!!", loginUser);

            return;

        }

        loggedIn = true;

        //Session Id for tag UNIQ key for each user login
        loginUser.setSessionId(sessionId);
        super.getRequest().getSession().setAttribute("userAuthen", loginUser);

        addInfo(MessageUtils.getResourceBundleString("login.loginprocess"));

        String path = JsfUtil.getContextPath();

        LOGGER.trace("path : {}", path);
        LOGGER.trace("getUserGroupLvl : {}", loginUser.getUserGroupLvl());

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        
        
        if (UserType.ADMIN.getId() == loginUser.getUserGroupLvl()) {
            super.redirectPage(path+"/pages/user/userManagement.xhtml?firstLogin=true",1000L);
            //externalContext.redirect(path+"/pages/user/userManagement.xhtml?firstLogin=true");
        } else if (UserType.OFFICIAL_USER.getId() == loginUser.getUserGroupLvl()) {
            super.redirectPage(path+"/pages/user/userManagement.xhtml?firstLogin=true",1000L);
            //externalContext.redirect(path+"/pages/user/userManagement.xhtml?firstLogin=true");
        } else if (UserType.CUSTOMER.getId() == loginUser.getUserGroupLvl()) {
            super.redirectPage(path+"/pages/form/insuranceManagement.xhtml?firstLogin=true",1000L);
            //externalContext.redirect(path+"/pages/form/insuranceManagement.xhtml?firstLogin=true");
        }

    }

    public void logout() {

        LOGGER.trace("logout!!");

        redirectPage(JsfUtil.getContextPath() + "/LogoutController");

    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the passWord
     */
    public String getPassWord() {
        return passWord;
    }

    /**
     * @param passWord the passWord to set
     */
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    private boolean validateLongin() {

        if (userName.isEmpty() || passWord.isEmpty()) {
            return false;
        }

        return true;

    }

    @Override
    public void resetForm() {
    }

    /**
     * @return the authenDao
     */
    public AuthenDao getAuthenDao() {
        return authenDao;
    }

    /**
     * @param authenDao the authenDao to set
     */
    public void setAuthenDao(AuthenDao authenDao) {
        this.authenDao = authenDao;
    }

    /**
     * @return the loginUser
     */
    public ViewUser getLoginUser() {
        return loginUser;
    }

    /**
     * @param loginUser the loginUser to set
     */
    public void setLoginUser(ViewUser loginUser) {
        this.loginUser = loginUser;
    }

    /**
     * @return the loggedIn
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }

    /**
     * @param loggedIn the loggedIn to set
     */
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    /**
     * @return the localeBean
     */
    public LocaleBean getLocaleBean() {
        return localeBean;
    }

    /**
     * @param localeBean the localeBean to set
     */
    public void setLocaleBean(LocaleBean localeBean) {
        this.localeBean = localeBean;
    }
}
