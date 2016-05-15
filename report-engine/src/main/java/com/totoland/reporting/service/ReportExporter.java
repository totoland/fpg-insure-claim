/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.totoland.reporting.service;

import java.util.Collection;

/**
 *
 * @author totoland
 */
public interface ReportExporter {
    
    byte[] exporterToByte(String jrxml,Collection<?> beanCollection) throws Exception;
    
}
