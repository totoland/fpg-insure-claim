/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.totoland.web.controller;

import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author totoland
 */
@ManagedBean
@SessionScoped
public class LocaleBean {

    private Locale locale;

    @PostConstruct
    public void init() {
        locale = new Locale("th","TH");
        System.out.println("locale : "+locale);
    }

    public Locale getLocale() {
        return locale;
    }

    public String getLanguage() {
        return locale.getLanguage();
    }

    public void setLanguage(String language,String country ) {
        locale = new Locale(language,country);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
    }

}
