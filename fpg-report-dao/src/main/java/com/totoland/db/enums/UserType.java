/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.totoland.db.enums;

/**
 *
 * @author totoland
 */
public enum UserType {
    ADMIN(1),
    OFFICIAL_USER(2),
    CUSTOMER(3);
    
    private int value;
    
    UserType(int value){
        this.value = value;
    }
    
    public int getId(){
        return this.value;
    }
}
