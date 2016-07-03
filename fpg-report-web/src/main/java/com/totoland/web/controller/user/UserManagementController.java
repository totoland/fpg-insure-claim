/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.totoland.web.controller.user;

import com.totoland.db.bean.UserCriteria;
import com.totoland.db.entity.KeyMatch;
import com.totoland.db.entity.SvUser;
import com.totoland.db.entity.ViewUser;
import com.totoland.web.controller.BaseController;
import com.totoland.web.factory.DropdownFactory;
import com.totoland.web.service.CustomerService;
import com.totoland.web.service.KeyMatchService;
import com.totoland.web.service.UserService;
import com.totoland.web.utils.JsfUtil;
import com.totoland.web.utils.MessageUtils;
import com.totoland.web.utils.StringUtils;
import com.totoland.web.utils.WebUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/**
 *
 * @author totoland
 */
@ViewScoped
@ManagedBean
public class UserManagementController extends BaseController {

    private static final long serialVersionUID = 4889668000891738625L;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserManagementController.class);
    private List<ViewUser> svUsers;
    private UserCriteria userCriteria = new UserCriteria();
    
    @ManagedProperty("#{userService}")
    private UserService userService;
    @ManagedProperty("#{dropdownFactory}")
    private DropdownFactory dropdownFactory;
    @ManagedProperty("#{customerService}")
    private CustomerService customerService;
    @ManagedProperty("#{keyMatchService}")
    private KeyMatchService keyMatchService;
    
    private SvUser svUser = new SvUser();
    private KeyMatch keyMatch = new KeyMatch();
    private long countAllCustomer;
    private long countAllUser;
    private long countAllAdmin;
    private String rePassword;

    @PostConstruct
    public void init() {
        LOGGER.trace("initPage");
        initForm();
    }

    public void search() {
        LOGGER.trace("search userCriteria : {}", getUserCriteria());
        svUsers = getUserService().findByUserCriteria(getUserCriteria());
        LOGGER.trace("svUsers size : {}", svUsers);
    }

    public List<ViewUser> completeUsername(String query) {
        LOGGER.trace("autoComplete find Username text search : {}", query);
        return svUsers;
    }

    public void initCreateUser() {
        LOGGER.trace("create new User");
        svUser = new SvUser();
        svUser.setUserGroupId(1);
        svUser.setSex(0);
        svUser.setIsActive(Boolean.TRUE);
        keyMatch = new KeyMatch();
        openDialog("modalDialogCreate");
    }

    public void initEditUser(ViewUser selsvUser) {
        LOGGER.trace("edit User");
        
        try {
            this.svUser = new SvUser();
            this.svUser.setUserId(selsvUser.getUserId());
            this.svUser.setUsername(selsvUser.getUsername());
            this.svUser.setPassword(WebUtils.decrypt(selsvUser.getPassword()));
            this.rePassword = WebUtils.decrypt(selsvUser.getPassword());
            this.svUser.setIsActive(selsvUser.getIsActive());
            this.svUser.setFname(selsvUser.getFname());
            this.svUser.setLname(selsvUser.getLname());
            this.svUser.setSex(selsvUser.getSex());
            this.svUser.setUserGroupId(selsvUser.getUserGroupId());
            this.svUser.setUserGroupLvl(selsvUser.getUserGroupLvl());
            
            //Find KeyMatching
            keyMatch = keyMatchService.findByCustomerId(String.valueOf(selsvUser.getUserId()));
            
            if(keyMatch == null){
                keyMatch = new KeyMatch();
            }
            
        } catch (Exception ex) {
            LOGGER.error("cannot initEdit :", ex);
        }
        openDialog("modalDialogEdit");
    }

    public void save() {
        
        LOGGER.trace("Save... {} ", svUser);

        if (!validateBeforeSave()) {
            return;
        }
        
        try {
            svUser.setPassword(WebUtils.encrypt(svUser.getPassword()));
            userService.create(svUser);
            
            LOGGER.trace("Save Success !! ");

            addInfo(MessageUtils.SAVE_SUCCESS());
            JsfUtil.hidePopup("modalDialogCreate");
            
            search();
        } catch (Exception ex) {
            JsfUtil.alertJavaScript(MessageUtils.SAVE_NOT_SUCCESS()+" ข้อผิดพลาด :"+ MDC.get("reqId"));
            LOGGER.error("Cannot Save Data : ", ex);
        }
    }

    public void edit() {
        LOGGER.trace("edit...");

        if (!validateBeforeEdit()) {
            return;
        }

        try {
            
            svUser.setPassword(WebUtils.encrypt(svUser.getPassword()));
            
            userService.edit(svUser);

            LOGGER.trace("Edit Success !! ");

            addInfo(MessageUtils.SAVE_SUCCESS());
            search();

            JsfUtil.hidePopup("modalDialogEdit");


        } catch (Exception ex) {

            JsfUtil.alertJavaScript(MessageUtils.SAVE_NOT_SUCCESS()+" ข้อผิดพลาด :"+ MDC.get("reqId"));

            LOGGER.error("Cannot Save Data : ", ex);
            
            svUser.setPassword(rePassword);
            
        } finally {

            LOGGER.trace("Save... {} ", svUser);

        }
    }

    @Override
    public void resetForm() {
        initForm();
    }

    public void initForm() {
        userCriteria = new UserCriteria();
        svUsers = new ArrayList<>();
        countAllCustomer = customerService.countAllCustomer();
        countAllAdmin = userService.countAllAdmin();
        countAllUser = userService.countAllUser();
    }

    /**
     * @return the svUsers
     */
    public List<ViewUser> getSvUsers() {
        return svUsers;
    }

    /**
     * @param svUsers the svUsers to set
     */
    public void setSvUsers(List<ViewUser> svUsers) {
        this.svUsers = svUsers;
    }

    /**
     * @return the userCriteria
     */
    public UserCriteria getUserCriteria() {
        return userCriteria;
    }

    /**
     * @param userCriteria the userCriteria to set
     */
    public void setUserCriteria(UserCriteria userCriteria) {
        this.userCriteria = userCriteria;
    }

    /**
     * @return the userService
     */
    public UserService getUserService() {
        return userService;
    }

    /**
     * @param userService the userService to set
     */
    public void setUserService(UserService userService) {
        this.userService = userService;
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
     * @return the svUser
     */
    public SvUser getSvUser() {
        return svUser;
    }

    /**
     * @param svUser the svUser to set
     */
    public void setSvUser(SvUser svUser) {
        this.svUser = svUser;
    }

    private boolean validateBeforeEdit() {
        String msg = "";
                
        if (StringUtils.isBlank(svUser.getUsername())) {
            msg += (MessageUtils.getResourceBundleString("require_message", "Username")) + ("\\n");
        }
        if (StringUtils.isBlank(svUser.getPassword())) {
            msg += (MessageUtils.getResourceBundleString("require_message", "Password")) + ("\\n");
        }
        if (StringUtils.isBlank(svUser.getFname())) {
            msg += (MessageUtils.getResourceBundleString("require_message", "Fist name")) + ("\\n");
        }
        if (StringUtils.isBlank(svUser.getLname())) {
            msg += (MessageUtils.getResourceBundleString("require_message", "Last name")) + ("\\n");
        }
        if (svUser.getSex() == null) {
            msg += (MessageUtils.getResourceBundleString("require_message", "Gender")) + ("\\n");
        }
        if (svUser.getUserGroupLvl() == null || svUser.getUserGroupLvl() == -1) {
            msg += (MessageUtils.getResourceBundleString("require_message", "Group Level")) + ("\\n");
        }
        if (svUser.getUserGroupId() == null || svUser.getUserGroupId() == -1) {
            msg += (MessageUtils.getResourceBundleString("require_message", "User Group")) + ("\\n");
        }
        if (svUser.getIsActive() == null) {
            msg += (MessageUtils.getResourceBundleString("require_message", "Status")) + ("\\n");
        }

        if (!StringUtils.isBlank(msg)) {
            JsfUtil.alertJavaScript(msg.trim());
            return false;
        }

        if (!svUser.getPassword().equals(rePassword)) {
            JsfUtil.alertJavaScript(MessageUtils.getResourceBundleString("password_not_same"));
            return false;
        }

        return true;
    }
    
    private boolean validateBeforeSave() {
        String msg = "";
        
        if(userService.findByUserName(svUser.getUsername())!=null){
            
            JsfUtil.alertJavaScript(MessageUtils.getResourceBundleString("dupp_username",svUser.getUsername()));
            return false;
        };
        
        if (StringUtils.isBlank(svUser.getUsername())) {
            msg += (MessageUtils.getResourceBundleString("require_message", "Username")) + ("\\n");
        }
        if (StringUtils.isBlank(svUser.getPassword())) {
            msg += (MessageUtils.getResourceBundleString("require_message", "Password")) + ("\\n");
        }
        if (StringUtils.isBlank(svUser.getFname())) {
            msg += (MessageUtils.getResourceBundleString("require_message", "First name")) + ("\\n");
        }
        if (StringUtils.isBlank(svUser.getLname())) {
            msg += (MessageUtils.getResourceBundleString("require_message", "Last name")) + ("\\n");
        }
        if (svUser.getSex() == null) {
            msg += (MessageUtils.getResourceBundleString("require_message", "Gender")) + ("\\n");
        }
        if (svUser.getUserGroupLvl() == null || svUser.getUserGroupLvl().intValue() == -1) {
            msg += (MessageUtils.getResourceBundleString("require_message", "User Type")) + ("\\n");
        }
        if (svUser.getIsActive() == null) {
            msg += (MessageUtils.getResourceBundleString("require_message", "Status")) + ("\\n");
        }

        if (!StringUtils.isBlank(msg.toString())) {
            JsfUtil.alertJavaScript(msg.toString().trim());
            return false;
        }

        if (!svUser.getPassword().equals(rePassword)) {
            JsfUtil.alertJavaScript(MessageUtils.getString("password_not_same"));
            return false;
        }

        return true;
    }

    /**
     * @return the rePassword
     */
    public String getRePassword() {
        return rePassword;
    }

    /**
     * @param rePassword the rePassword to set
     */
    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public CustomerService getCustomerService() {
        return customerService;
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public long getCountAllCustomer() {
        return countAllCustomer;
    }

    public long getCountAllUser() {
        return countAllUser;
    }

    public void setCountAllUser(long countAllUser) {
        this.countAllUser = countAllUser;
    }

    public long getCountAllAdmin() {
        return countAllAdmin;
    }

    public void setCountAllAdmin(long countAllAdmin) {
        this.countAllAdmin = countAllAdmin;
    }

    public KeyMatch getKeyMatch() {
        return keyMatch;
    }

    public void setKeyMatch(KeyMatch keyMatch) {
        this.keyMatch = keyMatch;
    }

    public KeyMatchService getKeyMatchService() {
        return keyMatchService;
    }

    public void setKeyMatchService(KeyMatchService keyMatchService) {
        this.keyMatchService = keyMatchService;
    }
}
