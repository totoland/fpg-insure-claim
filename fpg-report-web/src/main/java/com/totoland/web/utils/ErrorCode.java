/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.totoland.web.utils;

/**
 *
 * @author Totoland
 */
public enum ErrorCode {
    DUPP("9999","DUPP"), DATA_NOT_FOUND("0000","DATA_NOT_FOUND"),REQUIRE("-1","REQUIRE"),INSERT_FAIL("0001","INSERT_FAIL"),IO_EXCEPTION("1000","IO_EXCEPTION"),XLS_EXCEPTION("1100","XLS_EXCEPTION"),SUCCESS("1","SUCCESS");
    private final String id;
    private final String detail;
    ErrorCode(String id,String detail) {
        this.id = id;
        this.detail = detail;
    }

    public String getValue() {
        return id;
    }

    /**
     * @return the detail
     */
    public String getDetail() {
        return detail;
    }
    
}
