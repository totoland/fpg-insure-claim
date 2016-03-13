package com.totoland.web.utils;

import com.sun.faces.component.visit.FullVisitContext;
import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.UISelectItem;
import javax.faces.component.UIViewRoot;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.visit.VisitCallback;
import javax.faces.component.visit.VisitContext;
import javax.faces.component.visit.VisitResult;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.dialog.Dialog;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsfUtil implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(JsfUtil.class);
    private static final long serialVersionUID = 5017019437928010910L;

    public static void windowReload() {
        JsfUtil.executeJavaScript("window.location = '';");
    }

    public static DataTable getDataTable(String name) {
        final DataTable d = (DataTable) FacesContext.getCurrentInstance().getViewRoot()
                .findComponent(name);
        return d;
    }

    public UIComponent findComponent(final String id) {
        FacesContext context = FacesContext.getCurrentInstance();
        UIViewRoot root = context.getViewRoot();
        final UIComponent[] found = new UIComponent[1];
        root.visitTree(new FullVisitContext(context), new VisitCallback() {
            @Override
            public VisitResult visit(VisitContext context, UIComponent component) {
                if (component.getId().equals(id)) {
                    found[0] = component;
                    return VisitResult.COMPLETE;
                }
                return VisitResult.ACCEPT;
            }
        });
        return found[0];
    }
    private RequestContext context = RequestContext.getCurrentInstance();

    public static void addErrorMessage(Exception ex, String defaultMsg) {
        String msg = ex.getLocalizedMessage();
        if (msg != null && msg.length() > 0) {
            addErrorMessage(msg);
        } else {
            addErrorMessage(defaultMsg);
        }
    }

    public static void addErrorMessages(List<String> messages) {
        for (String message : messages) {
            addErrorMessage(message);
        }
    }

    public static void addErrorMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        FacesContext.getCurrentInstance().validationFailed(); // Invalidate JSF page if we raise an error message

    }

    public static void addSuccessMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
        FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
    }

    public static Throwable getRootCause(Throwable cause) {
        if (cause != null) {
            Throwable source = cause.getCause();
            if (source != null) {
                return getRootCause(source);
            } else {
                return cause;
            }
        }
        return null;
    }

    public static boolean isValidationFailed() {
        return FacesContext.getCurrentInstance().isValidationFailed();
    }

    public static boolean isDummySelectItem(UIComponent component, String value) {
        for (UIComponent children : component.getChildren()) {
            if (children instanceof UISelectItem) {
                UISelectItem item = (UISelectItem) children;
                if (item.getItemValue() == null && item.getItemLabel().equals(value)) {
                    return true;
                }
                break;
            }
        }
        return false;
    }

    public static String getComponentMessages(String clientComponent, String defaultMessage) {
        FacesContext fc = FacesContext.getCurrentInstance();
        UIComponent component = UIComponent.getCurrentComponent(fc).findComponent(clientComponent);
        if (component instanceof UIInput) {
            UIInput inputComponent = (UIInput) component;
            if (inputComponent.isValid()) {
                return defaultMessage;
            } else {
                Iterator<FacesMessage> iter = fc.getMessages(inputComponent.getClientId());
                if (iter.hasNext()) {
                    return iter.next().getDetail();
                }
            }
        }
        return "";
    }

    public static void hidePopup(String dlgAddReportDetail) {
        executeJavaScript(dlgAddReportDetail.concat(".").concat("hide();"));
    }

    public static void hidePopupIframe(String report_MainDialog) {
        executeJavaScript("parent." + report_MainDialog.concat(".").concat("hide();"));
    }

    public static void executeJavaScript(String function) {
        RequestContext.getCurrentInstance().execute(function);
    }

    public static void alertJavaScript(String function) {
        RequestContext.getCurrentInstance().execute("alert(\"" + function + "\");");
    }

    public static String getContextPath() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpServletRequest servletRequest = (HttpServletRequest) ctx.getExternalContext().getRequest();
        return servletRequest.getContextPath();
    }

    public static String getContextURI() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpServletRequest servletRequest = (HttpServletRequest) ctx.getExternalContext().getRequest();
        return servletRequest.getRequestURI();
    }

    /**
     * *
     * Close Popup
     *
     * @param dialogId
     */
    public static void closeDialog(String dialogId) {
        executeJavaScript(dialogId + ".hide()");
    }

    public static void openDialog(String dialogId) {
        executeJavaScript(dialogId + ".show()");
    }

    public static void consoleLog(String log) {
        executeJavaScript("logger('" + log + "');");
    }

    public static void redirectPage(String url) {
        ExternalContext _context = FacesContext.getCurrentInstance().getExternalContext();
        try {
            _context.redirect(_context.getRequestContextPath() + url);
        } catch (IOException ex) {
            logger.error("Cannot Redirect page : {} ", url, ex);
        }
    }

    public void clearAllMessage() {

        if (FacesContext.getCurrentInstance().getMessages().hasNext()) {
            FacesContext.getCurrentInstance().getMessages().remove();
        }

    }

    public static void updateCliend(String updateId) {
        RequestContext.getCurrentInstance().update(updateId);
    }

    public void disablePopup(String id) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        Dialog panel = (Dialog) ctx.getViewRoot().findComponent(id);

        if (panel == null) {
            return;
        }

        disableAll(panel.getChildren());
    }

    public void disableAll(List<UIComponent> components) {

        for (UIComponent component : components) {

            if (component instanceof HtmlInputText) {
                ((HtmlInputText) component).setDisabled(true);
            }
            if (component instanceof InputText) {
                ((InputText) component).setDisabled(true);
            }
            if (component instanceof SelectOneMenu) {
                ((SelectOneMenu) component).setDisabled(true);
            }

            disableAll(component.getChildren());
        }
    }

    public void openIframe(String url) {

        if (url.indexOf("?") == -1) {
            url += "?q=q";
        }

        executeJavaScript("dialogEdit.show();");
        executeJavaScript("$(\"#divFrmEdit\").html(\"<iframe src='" + url + "&random=\" + Math.random() + \"'  scrolling='yes' style='border: none;width: 100%;height:500px'></iframe>\");");
    }
}
