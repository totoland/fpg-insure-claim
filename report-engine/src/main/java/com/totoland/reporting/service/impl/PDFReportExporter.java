/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.totoland.reporting.service.impl;

import com.totoland.reporting.bean.ArrayCollectionBean;
import com.totoland.reporting.service.ReportExporter;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

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
    public byte[] exporterToByte(String jrxml, Collection<?> beanCollection) throws Exception {
        InputStream inputStream;
        inputStream = new FileInputStream(jrxml);

        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(beanCollection);

        Map parameters = new HashMap();

        JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);

        return JasperExportManager.exportReportToPdf(jasperPrint);

    }

    @Override
    public byte[] exporterToByteList(List<ArrayCollectionBean> beanCollection) throws Exception {
        if (beanCollection == null || beanCollection.isEmpty()) {
            throw new IllegalArgumentException("beanCollection should not be empty");
        }

        List<JasperPrint> jasperPrints = new ArrayList<>();

        for (ArrayCollectionBean collection : beanCollection) {
            System.out.println("collection.getBeanCollection() "+collection.getBeanCollection());
            InputStream inputStream;
            inputStream = new FileInputStream(collection.getJrxml());
            JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(collection.getBeanCollection());

            Map parameters = new HashMap();

            JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);

            jasperPrints.add(jasperPrint);
        }

        System.out.println("jasperPrints : "+jasperPrints.size());
        
        JasperPrint firstPage = jasperPrints.get(0);
        

        for (int i = 1; i < jasperPrints.size(); i++) {
            List pages = jasperPrints.get(i).getPages();
            JRPrintPage object = (JRPrintPage) pages.get(0);
            firstPage.addPage(object);
        }

        JasperViewer.viewReport(firstPage, false);
        return JasperExportManager.exportReportToPdf(firstPage);

    }

}
