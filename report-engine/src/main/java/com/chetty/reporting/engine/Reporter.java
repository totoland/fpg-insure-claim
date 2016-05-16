package com.chetty.reporting.engine;

import com.chetty.reporting.bean.DebitNote;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.chetty.reporting.beans.DataBean;
import com.chetty.reporting.business.DataBeanMaker;
import java.util.Arrays;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class Reporter {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        InputStream inputStream = new FileInputStream("reports/reportDebitNote.jrxml");

        DebitNote debitNote = new DebitNote();

        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(Arrays.asList(debitNote));

        Map parameters = new HashMap();

        JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, "d:/test_jasper.pdf");
    }
}
