/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.totoland.web.utils;

import java.math.BigDecimal;

/**
 *
 * @author Totoland
 */
public class NumberUtils {
    
    public static Long toLong(Object object){
        try{
            if(object!=null){
                return Long.parseLong(object.toString().trim());
            }
        }catch(Exception ex){
        }
        return null;
    }
    public static Integer toInteger(Object object){
        try{
            if(object!=null){
                return Integer.parseInt(object.toString().trim());
            }
        }catch(Exception ex){
        }
        return null;
    }
    public static Long bigDecimalToLong(BigDecimal b){
        if(b!=null){
            return new Long(b.longValue());
        }
        return null;
    }
    public static boolean isNumber(Object object){
        try{
            Integer.parseInt(object.toString());
            return true;
        }catch(Exception ex){
            return false;
        }
    }
    public static BigDecimal toBigDecimal(Object object){
        try{
            if(object!=null){
                return new BigDecimal(object.toString());
            }
        }catch(Exception ex){
        }
        return null;
    }
    public static Integer convertNUllToZero(Integer nvl){
        if(nvl==null){
            return 0;
        }
        
        return nvl;
    }
}
