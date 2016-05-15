/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.totoland.reporting.service.impl;

import com.totoland.reporting.service.ReportExporter;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

/**
 *
 * @author totoland
 */
public class PDFReportExporter implements ReportExporter {

    /**
     *
     * @param jrxml
     * @param beanCollection
     * @return
     * @throws Exception
     */
    @Override
    public byte[] exporterToByte(String jrxml, Collection<?> beanCollection) throws Exception{
        InputStream inputStream;
        inputStream = new FileInputStream(jrxml);

        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(beanCollection);

        Map parameters = new HashMap();

        JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
        return JasperExportManager.exportReportToPdf(jasperPrint);

    }

}
