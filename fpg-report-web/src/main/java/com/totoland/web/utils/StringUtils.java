/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.totoland.web.utils;

/**
 *
 * @author Totoland
 */
public class StringUtils {
    
    public static String trim(String pid){
        if(pid==null)
            return null;
        return pid.trim();
    }
    public static String replaceDatAndTrim(String pid){
        if(pid==null)
            return null;
        return pid.replaceAll("-", "").trim();
    }
    public static boolean isBlank(String s){
        return s==null || s.trim().equals("");
    }
    public static String[] split(String s,String rex){
        String[] arr = s.split(rex);
        return arr;
    }
}
