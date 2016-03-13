/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.totoland.web.utils;

import java.text.SimpleDateFormat;
import java.util.*;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author Totoland
 */
public class DateTimeUtils {
    private static DateTimeUtils instance;

    /**
     * @return the instance
     */
    public static DateTimeUtils getInstance() {
        if(instance==null){
            instance = new DateTimeUtils();
        }
        return instance;
    }
    private Logger log = Logger.getLogger(DateTimeUtils.class);
    private Locale locale = Locale.US;
    private Locale locale_th = new Locale ( "th", "TH" );
    private TimeZone timeZone = TimeZone.getTimeZone("GMT+7:00");
    private String eraType = "1";
    private DatatypeFactory datatypeFactory;
    public static final String DEFAULT_DATE_FORMAT_FROM_WS = "yyyyMMdd";
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DISPLAY_DATE_FORMAT = "dd/MM/yyyy";
    public static final String DISPLAY_TIME_FORMAT = "HH:mm:ss";
    public static final String DISPLAY_DATETIME_FORMAT = "dd/MM/yyyy HH:mm:ss";
    public static final String yyyyMM = "yyyyMM";
    public static final String yyyyMMdd = "yyyyMMdd";
    public static final String monthYear = "MMMM yyyy";
    public static final String ERA_THAI = "1";
    public static final String ddMMyyyy = "ddMMyyyy";
    public static final String DATE_TIME_FORMAT = "ddMMyyyyhhmmss";
    private DateTimeUtils() {
    }
    
//    public static void main(String args[]){
//        System.out.println(new DateTimeUtils().thDate(new Date(), "dd/MM/yyyy"));
//    }
    public String yyyyMMddToddMMyyyy(String date){
        return yyyyMMddToddMMyyyy(date, "");
    }
    public String yyyyMMddToddMMyyyy(String date,String rex){
        if(date.length()==8){
            String yyyy = date.substring(0, 4);
            String MM = date.substring(4, 6);
            String dd = date.substring(6, 8);
            return dd+rex+MM+rex+yyyy;
        }
        return date;
    }
    public String yyyyMMddToMMyyyy(String date,String rex){
        if(date.length()==8){
            String yyyy = date.substring(0, 4);
            String MM = date.substring(4, 6);
            String dd = date.substring(6, 8);
            return MM+rex+yyyy;
        }
        return date;
    }
    public String thDate(Date date,String format){
        if(date==null){
            return null;
        }
        if(format==null){
            format = DISPLAY_DATE_FORMAT;
        }
        return new SimpleDateFormat(format,locale_th).format(date);
    }
    public String thDateNow(String format){
        return new SimpleDateFormat(format,locale_th).format(new Date());
    }
    public String enDateNow(String format){
        return convertddMMyyToMMMMYYYY((new Date()), format);
    }
    public Date currMonth(Date d){
        Calendar cal = getCalendar();
        cal.setTime(d);

        cal.set(Calendar.DAY_OF_MONTH, 1);
        int currentMont = cal.get(Calendar.MONTH);

        cal.set(Calendar.MONTH, currentMont);

        return cal.getTime();
    }

    public Date prevMonth(Date d) {

        Calendar cal = getCalendar();
        cal.setTime(d);      

        cal.set(Calendar.DAY_OF_MONTH, 1);
        int currentMont = cal.get(Calendar.MONTH);
        int currentYear = cal.get(Calendar.YEAR);

        if (currentMont <= 0){
            currentMont = 11;
            currentYear = currentYear-1;
        }else{
            currentMont--;
        }

        cal.set(Calendar.MONTH, currentMont);
        cal.set(Calendar.YEAR,currentYear);
        return cal.getTime();
    }
    
    public Date prevMonthWithNumber(Date d,int number,String include) {

        Calendar cal = Calendar.getInstance(timeZone,locale);
       // System.out.println(" time  "+d.toString());
        cal.setTime(d);

        cal.set(Calendar.DAY_OF_MONTH, 1);
        int currentMonth = cal.get(Calendar.MONTH);
        //System.out.println(" current month "+currentMonth);
        
        int currentYear = cal.get(Calendar.YEAR);
        //System.out.println(" current year "+currentYear);
        
        String flag = "true";

        if (currentMonth < 0){
            currentMonth = 11;
        }else{
                if( currentMonth == 0)
                    currentMonth = 12;
                if(number >= currentMonth){
                    flag = "false";
                    int remain = number-currentMonth;
                     if(include.equals("true")){
                            currentMonth = 12 - remain;
                        }else if(include.equals("false")){
                            currentMonth = 12 -(remain + 1);
                        }
                }else{
//                    if(currentYear == 12){
//                        flag = "false";
//                    }
                    if(include.equals("true")){
                            currentMonth = currentMonth - number;
                        }else if(include.equals("false")){
                            currentMonth = currentMonth -(number+1);
                        }
                }
            }

        cal.set(Calendar.MONTH, currentMonth);

        if(flag.equals("false")){
            currentYear = currentYear-1;
        }
        
        cal.set(Calendar.YEAR,currentYear);

        return cal.getTime();
    }
    
    public Date toLastDate(Date d) {

        Calendar cal = getCalendar();
        cal.setTime(d);
        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DAY_OF_MONTH));

        return cal.getTime();
    }
    private Date convertEraToThai(Date d) {

        Date date = null;

        if (d == null) {
            return null;
        }

        try {

            if (ERA_THAI.equals(this.eraType)) {
                Calendar cal = getCalendar();
                cal.setTime(d);
                cal.add(Calendar.YEAR, 543);

                date = cal.getTime();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return date;
    }
    private Calendar getCalendar() {
        return Calendar.getInstance(timeZone, locale);
    }

    public Date getDate() {
        return getCalendar().getTime();
    }
    
    private String convertEraToEng(String d) {

        String dateStr = null;

        if (StringUtils.isBlank(d)) {
            return null;
        }

        try {

            if (ERA_THAI.equals(this.eraType)) {

                String[] x = StringUtils.split(d, "/");

                int dd = Integer.parseInt(x[0]);
                int mm = Integer.parseInt(x[1]);
                int yyyy = Integer.parseInt(x[2]) - 543;

                dateStr = dd + "/" + mm + "/" + yyyy;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dateStr;
    }
    
    private String convertEraToEngWithoutSlat(String d) {
        try {
            int date = (Integer.parseInt(d)+543);
            if((date+"").length()==7){
                return "0"+date;
            }else{
                return date+"";
            }
        } catch (Exception e) {
            
        }

        return null;
    }
    
    public String formatDate(Date d, String format, TimeZone timeZone, Locale loc) {

        String formatDate = "";

        if (d == null) {
            return formatDate;
        }

        try {
            formatDate = DateFormatUtils.format(d, format, timeZone, loc);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return formatDate;
    }
    public String formatDateNull(Date d) {
        String formatDate = null;

        if (d == null) {
            return formatDate;
        }

        try {
            formatDate = DateFormatUtils.format(d, DEFAULT_DATE_FORMAT, timeZone, locale);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return formatDate;
    }
    public String formatDate(Date d) {
        return formatDate(d, DEFAULT_DATE_FORMAT, timeZone, locale);
    }

    public String formatDate(Date d, String format) {
        return formatDate(d, format, timeZone, locale);
    }
    public String formatDateTH(Date d, String format) {
        return formatDate(d, format, timeZone, locale);
    }

    public String formatDatetime(Date d) {
        return formatDate(d, DEFAULT_DATETIME_FORMAT, timeZone, locale);
    }

    public String formatDateForDisplay(Date d, String format) {
        return formatDate(convertEraToThai(d), format, timeZone, locale);
    }

    public String formatDatetimeForDisplay(Date d, String format) {
        return formatDate(convertEraToThai(d), format, timeZone, locale);
    }

    public String formatDateForDisplay(Date d) {
        return formatDateForDisplay(d, DISPLAY_DATE_FORMAT);
    }

    public String formatTimeForDisplay(Date d) {
        return formatDateForDisplay(d, DISPLAY_TIME_FORMAT);
    }

    public String formatDatetimeForDisplay(Date d) {
        return formatDatetimeForDisplay(d, DISPLAY_DATETIME_FORMAT);
    }

    public Date parseDateYYYYMM(String date) {
        if (date == null) {
            return null;
        }
        return parseDate(date + "01", yyyyMMdd);
    }

    public Date parseDate(String date) {
        return parseDate(date, DEFAULT_DATE_FORMAT);
    }

    public Date parseDate(String date, String format) {
        if(date!=null){
            date = date.trim();
        }
        Date d = null;

        if (StringUtils.isBlank(date)) {
            return null;
        }

        try {
            d = DateUtils.parseDate(date, new String[]{format});

        } catch (Exception ex) {
        }

        return d;
    }

    public Date parseDateFromDisplay(String date) {
        return parseDate(convertEraToEng(date), DISPLAY_DATE_FORMAT);
    }

    public Date parseDatetimeFromDisplay(String date) {
        return parseDate(convertEraToEng(date), DISPLAY_DATETIME_FORMAT);
    }

    public Date parseDatetime(String date, String format) {
        return parseDate(date, format);
    }
    public Date parseStringThDateToDate(String date, String format) {
        if(format.equalsIgnoreCase(ddMMyyyy)){
            return parseDate(convertEraToEngWithoutSlat(date), format);
        }
        return parseDate(convertEraToEng(date), format);
    }
    public Date parseStringTh(String date, String format) {
        return parseDate(convertEraToEngWithoutSlat(date), format);
    }

    public Date parseDatetime(String date) {
        return parseDate(date, DEFAULT_DATETIME_FORMAT);
    }

    public XMLGregorianCalendar createXMLGregorianCalendar(Date date) {

        XMLGregorianCalendar xmlGc = null;

        if (date == null) {
            return null;
        }

        try {

            GregorianCalendar gc = new GregorianCalendar(timeZone, locale);
            gc.setTime(date);

            xmlGc = this.datatypeFactory.newXMLGregorianCalendar(gc);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return xmlGc;

    }

    public Date parseDate(XMLGregorianCalendar gc) {

        Date date = null;

        if (gc == null) {
            return null;
        }

        try {

            Calendar cal = getCalendar();
            cal.setTime(gc.toGregorianCalendar().getTime());
            date = cal.getTime();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return date;
    }

    public String convertYearEngToThai(String year) {
        try {
            return Integer.toString(Integer.parseInt(year)+543);
        }catch(Exception e) {
            return "";
        }
    }

    public String convertYearThaiToEng(String year) {
        try {
            return Integer.toString(Integer.parseInt(year)-543);
        }catch(Exception e) {
            return "";
        }
    }

   public String getYearFromDisplayDate(String ddMMyyyy) {
        try {
            return ddMMyyyy.substring(6);
        }catch(Exception e) {
            return null;
        }
    }

    public String convertddMMyyToMMMMYYYY(Date d) {
        return formatDate(d, "MMMM yyyy", timeZone, locale_th);
    }
    public XMLGregorianCalendar convertStrDateToXmlDate(String strDate,String formate){
        if (!strDate.equals("")){
            return createXMLGregorianCalendar(parseDate(strDate, formate));
        }else{
            return null;
        }
    }
    public String xmlCalendarToString(XMLGregorianCalendar gc,String formate){
        try{
            return convertddMMyyToMMMMYYYY(convertEraToThai(parseDate(gc)),formate);
        }catch(Exception ex){
            return "";
        }
    }
    
    public String convertddMMyyToMMMMYYYY(Date d,String formate) {
        return formatDate(d, formate, timeZone, locale_th);
    }
    public XMLGregorianCalendar convertStrDateToXmlDate(String strDate){
        if (!strDate.equals("")){
            return createXMLGregorianCalendar(parseDate(convertYearThToEng2(strDate), DateTimeUtils.DISPLAY_DATE_FORMAT));
        }else{
            return null;
        }
    }
    public  String convertYYYYmmToDDmmYYYY(String yyyyMM,String defaultDay) {
        try {

             Calendar calendar = Calendar.getInstance();
            int  month= Integer.parseInt(yyyyMM.substring(4))-1;
            int  year = Integer.parseInt(yyyyMM.substring(0,4));
            int  date = 1;
            calendar.set(year,month, date);
            int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            System.out.println("Max Day: " + maxDay);

            return maxDay+"/"+yyyyMM.substring(4)+"/"+(Integer.parseInt(yyyyMM.substring(0,4))+543);

        }catch(Exception e) {
            return "";
        }
    }
    public String convertDDMMYYYYtoYYYYMM(String s){
        try{
            String[] arr = s.split("/");
            return arr[2]+arr[1];
        }catch(Exception ex){

        }
        return null;
    }
    public String convertDDMMYYYYtoYYYYMMEn(String s){
        try{
            String[] arr = s.split("/");
            return (Integer.parseInt(arr[2])-543)+arr[1];
        }catch(Exception ex){

        }
        return null;
    }
    /***
     * Format dd/MM/yyyy
     * @param ddMMyyyy
     * @return
     */
    public String convertYearThToEng(String ddMMyyyy){
        try{
            String[] arr = ddMMyyyy.split("/");
            return arr[0]+"/"+arr[1]+"/"+(Integer.parseInt(arr[2])-543);
        }catch(Exception ex){

        }
        return null;
    }

    public String convertYearThToEng2(String ddMMyyyy){
        try{
            String[] arr  = ddMMyyyy.split("/");
            int      year = Integer.parseInt(arr[2]);
            if (year>2500){
                year = year - 543;
            }
            return arr[0]+"/"+arr[1]+"/"+year;
        }catch(Exception ex){

        }
        return null;
    }
    public String getLastMontTH(String format){
        return getLastMontTH(this.locale_th,format);
    }
    public String getLastMontTH(Locale locale_th,String format){
        SimpleDateFormat simple_date_format = new SimpleDateFormat ( format, locale_th );
        Calendar c1 = Calendar.getInstance();
        c1.add(Calendar.MONTH, -1);
        c1.set(Calendar.DATE, c1.getMaximum(Calendar.DAY_OF_MONTH));
        return simple_date_format.format(c1.getTime());
    }
    public String getDateNowTH(String format){
        return getDateNow(this.locale_th,format);
    }
    public String getDateNowEN(String format){
        return getDateNow(this.locale,format);
    }
    public String getDateNow(Locale locale_th,String format) {
        SimpleDateFormat simple_date_format = new SimpleDateFormat ( format, locale_th );
        return simple_date_format.format(new Date());
    }
}
