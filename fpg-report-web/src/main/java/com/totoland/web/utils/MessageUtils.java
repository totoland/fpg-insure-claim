/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.totoland.web.utils;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.faces.context.FacesContext;

/**
 *
 * @author Totoland
 */
public class MessageUtils {

    public static String showStringFlag(String flag) {
        if (flag == null || flag.length() == 0) {
            return "";
        }
        if (flag.equalsIgnoreCase("y")) {
            return getResourceBundleString(WebConstant.MESSAGES_PROP, "flag.active");
        } else if (flag.equalsIgnoreCase("n")) {
            return getResourceBundleString(WebConstant.MESSAGES_PROP, "flalg.notactive");
        } else {
            return "";
        }
    }

    public static String getConf(String key) {
        return ResourceBundle.getBundle(WebConstant.CONF_PROP).getString(key);
    }

    public static String getString(String key) {
        return getResourceBundleString(key);
    }

    public static String getString(String key, String... values) {
        MessageFormat messageFormat = new MessageFormat(key);
        return messageFormat.format(values);
    }

    public static String getResourceBundleString(String resourceBundleKey, String... values) throws MissingResourceException {
        FacesContext fc = FacesContext.getCurrentInstance();
        ResourceBundle bundle = fc.getApplication().getResourceBundle(fc, "msg");
        return getString(bundle.getString(resourceBundleKey), values);
    }

    public static String getResourceBundleString(String resourceBundleKey) throws MissingResourceException {
        FacesContext fc = FacesContext.getCurrentInstance();
        ResourceBundle bundle = fc.getApplication().getResourceBundle(fc, "msg");
        return bundle.getString(resourceBundleKey);
    }

    public static String getResourceBundleString(String resourceBundleKey, String values) throws MissingResourceException {
        FacesContext fc = FacesContext.getCurrentInstance();
        ResourceBundle bundle = fc.getApplication().getResourceBundle(fc, "msg");
        MessageFormat formatter = new MessageFormat("{0} {1,number,###0}");
        formatter.applyPattern(bundle.getString(resourceBundleKey));

        return formatter.format(new Object[]{values});

    }

    public static String SAVE_SUCCESS() {
        return getResourceBundleString("save_success");
    }

    public static String SAVE_NOT_SUCCESS() {
        return getResourceBundleString("save_not_success");
    }
    
    public static String DISCARD_SUCCESS() {
        return getResourceBundleString("discard_success");
    }
    
    public static String DISCARD_FAIL() {
        return getResourceBundleString("discard_not_success");
    }

    public static String PRINT_LINE_STAR() {
        return getResourceBundleString("print_line_star");
    }

    public static String REQUIRE_SELECT_STRATEGICID() {
        return getResourceBundleString("require_select_strategicId");
    }

    public static String REQUIRE_SELECT_SUBSTRATEGICID() {
        return getResourceBundleString("require_select_substrategicId");
    }

    public static String REQUIRE_SELECT_PLAN() {
        return getResourceBundleString("require_select_plan");
    }

    public static String REQUIRE_SELECT_PROJECT() {
        return getResourceBundleString("require_select_project");
    }

    public static String REQUIRE_SELECT_ACTIVITY() {
        return getResourceBundleString("require_select_activity");
    }

    public static String REQUIRQ_ENTER_ACTIVITY() {
        return getResourceBundleString("require_enter_activity");
    }

    public static String REQUIRE_ADD_REPORT_DETAIL() {
        return getResourceBundleString("require_add_report_detail");
    }

}
