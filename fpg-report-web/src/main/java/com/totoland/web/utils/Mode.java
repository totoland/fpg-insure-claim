/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.totoland.web.utils;

/**
 *
 * @author Totoland
 */
public enum Mode {
    INSERT("1","insert"), UPDATE("2","update");
    private final String id;
    private final String toString;
    Mode(String id,String toString) {
        this.id = id;
        this.toString = toString;
    }

    public String getValue() {
        return id;
    }

    /**
     * @return the toString
     */
    @Override
    public String toString() {
        return toString;
    }
}
