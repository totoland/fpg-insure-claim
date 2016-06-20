/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.totoland.web.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.Key;
import java.util.UUID;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 *
 * @author totoland
 */
public class WebUtils {

    public static String generateToken() {
        return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }

    private static final String ALGO = "AES";
    private static final byte[] keyValue
            = new byte[]{'E', 't', 'c', 'S', 'e', 'c', 'K',
                'e', 'y', '1', '2', '3', '4', '5', '6', '7'};

    public static String encrypt(String Data) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(Data.getBytes());
        String encryptedValue = new BASE64Encoder().encode(encVal);
        return encryptedValue;
    }

    public static String decrypt(String encryptedData) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
        byte[] decValue = c.doFinal(decordedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
    }

    private static Key generateKey() throws Exception {
        Key key = new SecretKeySpec(keyValue, ALGO);
        return key;
    }

    public static BigDecimal mutilplyRoundUp(BigDecimal a, BigDecimal b) {
        if(a == null || b == null){
            return null;
        }
        
        if(a.floatValue() == 0 || b.floatValue() == 0){
            return BigDecimal.ZERO;
        }
        
        return a.multiply(b).setScale(0, RoundingMode.UP);
    }
    
    public static void main(String args[]){
        System.out.println("mutilplyRoundUp "+ WebUtils.mutilplyRoundUp(new BigDecimal(501),new BigDecimal("0.004")));
        System.out.println("mutilplyRoundUp "+ WebUtils.mutilplyRoundUp(new BigDecimal(1200),new BigDecimal(0.004)));
    }
    
    public static BigDecimal divideRoundUp(BigDecimal a, BigDecimal b) {
        if(a == null || b == null){
            return null;
        }
        
        if(a.floatValue() == 0 || b.floatValue() == 0){
            return BigDecimal.ZERO;
        }
        
        return a.divide(b,RoundingMode.UP);
    }
}
