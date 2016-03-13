/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.totoland.web.controller.exception;

/**
 *
 * @author totoland
 */
public class SessionExpireException extends RuntimeException{
    private static final long serialVersionUID = -6040403717679925093L;
 
    public SessionExpireException(String msg){
        super(msg);
    }
}
