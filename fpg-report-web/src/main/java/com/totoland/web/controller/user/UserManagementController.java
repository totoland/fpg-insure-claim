/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.totoland.web.controller.user;

import com.totoland.db.bean.UserCriteria;
import com.totoland.db.entity.SvUser;
import com.totoland.db.entity.ViewUser;
import com.totoland.web.controller.BaseController;
import com.totoland.web.factory.DropdownFactory;
import com.totoland.web.service.GennericService;
import com.totoland.web.service.UserService;
import com.totoland.web.utils.JsfUtil;
import com.totoland.web.utils.MessageUtils;
import com.totoland.web.utils.StringUtils;
import com.totoland.web.utils.WebUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
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
    private static final Logger logger = LoggerFactory.getLogger(UserManagementController.class);
    private List<ViewUser> svUsers;
    private UserCriteria userCriteria = new UserCriteria();
    @ManagedProperty("#{userService}")
    private UserService userService;
    @ManagedProperty("#{dropdownFactory}")
    private DropdownFactory dropdownFactory;
    private SvUser svUser = new SvUser();
    @ManagedProperty("#{gennericService}")
    private GennericService<SvUser> gennericService;
    private String rePassword;
    private static final String URL_PORT = MessageUtils.getConf("openfire.server");
    private static final String SECRET_KEY = MessageUtils.getConf("secret");

    @PostConstruct
    public void init() {
        logger.trace("initPage");
        logger.trace("Locale : {}",FacesContext.getCurrentInstance()
        			.getViewRoot().getLocale());
        //checkRoleAdmin();
        initForm();
    }

    public void search() {
        logger.trace("search userCriteria : {}", getUserCriteria());

        svUsers = getUserService().findByUserCriteria(getUserCriteria());

        logger.trace("svUsers size : {}", svUsers);
    }

    public List<ViewUser> completeUsername(String query) {
        logger.trace("autoComplete find Username text search : {}", query);

        return svUsers;
    }

    public void initCreateUser() {
        logger.trace("create new User");

        svUser = new SvUser();
        svUser.setSex(0);
        svUser.setIsActive(Boolean.TRUE);
        
        openDialog("modalDialogCreate");
    }

    public void initEditUser(ViewUser selsvUser) {
        logger.trace("edit User");

        try {

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

        } catch (Exception ex) {

            logger.error("cannot initEdit :", ex);

        }
        openDialog("modalDialogEdit");
    }

    public void save() {
        
        logger.trace("Save... {} ", svUser);

        if (!validateBeforeSave()) {
            return;
        }

        try {

            svUser.setPassword(WebUtils.encrypt(svUser.getPassword()));

//            createOpenfireUser(svUser);
            
            gennericService.create(svUser);
            
            logger.trace("Save Success !! ");

            JsfUtil.alertJavaScript(MessageUtils.SAVE_SUCCESS());

            JsfUtil.hidePopup("modalDialogCreate");
            
            search();


        } catch (Exception ex) {

            JsfUtil.alertJavaScript(MessageUtils.SAVE_NOT_SUCCESS()+" ข้อผิดพลาด :"+ MDC.get("reqId"));

            logger.error("Cannot Save Data : ", ex);
        } finally {

            logger.trace("Save... {} ", svUser);

        }
    }

    public void edit() {
        logger.trace("edit...");

        if (!validateBeforeEdit()) {
            return;
        }

        try {
            
            svUser.setPassword(WebUtils.encrypt(svUser.getPassword()));
            
            gennericService.edit(svUser);

            logger.trace("Edit Success !! ");

            JsfUtil.alertJavaScript(MessageUtils.SAVE_SUCCESS());

            search();

            JsfUtil.hidePopup("modalDialogEdit");


        } catch (Exception ex) {

            JsfUtil.alertJavaScript(MessageUtils.SAVE_NOT_SUCCESS()+" ข้อผิดพลาด :"+ MDC.get("reqId"));

            logger.error("Cannot Save Data : ", ex);
            
            svUser.setPassword(rePassword);
            
        } finally {

            logger.trace("Save... {} ", svUser);

        }
    }

    @Override
    public void resetForm() {
        initForm();
    }

    public void initForm() {
        userCriteria = new UserCriteria();
        svUsers = new ArrayList<>();
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

    /**
     * @return the gennericService
     */
    public GennericService<SvUser> getGennericService() {
        return gennericService;
    }

    /**
     * @param gennericService the gennericService to set
     */
    public void setGennericService(GennericService<SvUser> gennericService) {
        this.gennericService = gennericService;
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
            msg += (MessageUtils.getResourceBundleString("require_message", "ชื่อผู้ใช้")) + ("\\n");
        }
        if (StringUtils.isBlank(svUser.getPassword())) {
            msg += (MessageUtils.getResourceBundleString("require_message", "รหัสผ่าน")) + ("\\n");
        }
        if (StringUtils.isBlank(svUser.getFname())) {
            msg += (MessageUtils.getResourceBundleString("require_message", "ชื่อ")) + ("\\n");
        }
        if (StringUtils.isBlank(svUser.getLname())) {
            msg += (MessageUtils.getResourceBundleString("require_message", "นามสกุล")) + ("\\n");
        }
        if (svUser.getSex() == null) {
            msg += (MessageUtils.getResourceBundleString("require_message", "เพศ")) + ("\\n");
        }
        if (svUser.getUserGroupLvl() == null || svUser.getUserGroupLvl().intValue() == -1) {
            msg += (MessageUtils.getResourceBundleString("require_message", "ระดับผู้ใช้")) + ("\\n");
        }
        if (svUser.getUserGroupId() == null || svUser.getUserGroupId().intValue() == -1) {
            msg += (MessageUtils.getResourceBundleString("require_message", "กลุ่มผู้ใช้")) + ("\\n");
        }
        if (svUser.getIsActive() == null) {
            msg += (MessageUtils.getResourceBundleString("require_message", "สถานนะ")) + ("\\n");
        }

        if (!StringUtils.isBlank(msg.toString())) {
            JsfUtil.alertJavaScript(msg.toString().trim());
            return false;
        }

        if (!svUser.getPassword().equals(rePassword)) {
            JsfUtil.alertJavaScript(MessageUtils.getResourceBundleString("password_not_same"));
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

    private void createOpenfireUser(SvUser user) throws IOException, Exception {
        HttpClient client = new DefaultHttpClient();
        String url = URL_PORT+"/plugins/userService/userservice?type=add&secret="+SECRET_KEY+"&username="+user.getUsername()+"&password="+WebUtils.decrypt(user.getPassword())+"&name="+user.getUsername()+"&email="+user.getUsername()+"@telepresence.ddns.net";
        
        logger.debug("URL : {}",url);
        
        HttpPost post = new HttpPost(url);
//        StringEntity input = new StringEntity("product");
//        post.setEntity(input);
        HttpResponse response = client.execute(post);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String line = "";
        while ((line = rd.readLine()) != null) {
            logger.debug(line);
        }
    }
    
}
