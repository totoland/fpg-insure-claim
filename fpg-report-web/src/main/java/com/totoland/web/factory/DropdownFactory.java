/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.totoland.web.factory;

import com.totoland.db.common.entity.DropDownList;
import com.totoland.web.service.CommonService;
import com.totoland.web.utils.MessageUtils;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author totoland
 */
@Component
public class DropdownFactory implements Serializable {

    private static final long serialVersionUID = 7739283351270265693L;
    @Autowired
    CommonService commonService;

    private static List<DropDownList> ectGroupLvl;

    /**
     * *
     * ระดับผู้ใช้
     *
     * @return
     */
    public List<DropDownList> ddlUserLvl() {
        if (ectGroupLvl == null) {

            DropDownList criteria = new DropDownList();
            criteria.setTableName("GROUP_LVL");
            criteria.setOrderByField("GROUP_LVL_ID");
            criteria.setName("GROUP_LVL_NAME");
            criteria.setValue("GROUP_LVL_ID");
            criteria.setSortName("ASC");
            //criteria.setCondition("GROUP_LVL_ID <> 5");

            ectGroupLvl = commonService.getDropdownList(criteria);
        }

        return ectGroupLvl;
    }

    private List<DropDownList> ectUserGroup;

    /**
     * *
     * กลุ่มผู้ใช้
     *
     * @return
     */
    public List<DropDownList> ddlUserGroup() {
        if (ectUserGroup == null) {

            DropDownList criteria = new DropDownList();
            criteria.setTableName("USER_GROUP");
            criteria.setOrderByField("USER_GROUP_NAME");
            criteria.setName("USER_GROUP_NAME");
            criteria.setValue("USER_GROUP_ID");
            criteria.setSortName("ASC");

            ectUserGroup = commonService.getDropdownList(criteria);
        }

        return ectUserGroup;
    }

    private static List<DropDownList> insureTypes;

    public List<DropDownList> ddlInsureTypesList() {

        insureTypes = new ArrayList<>();
        insureTypes.add(new DropDownList(MessageUtils.getString("option_air"), "1"));
        insureTypes.add(new DropDownList(MessageUtils.getString("option_vessel"), "2"));
        insureTypes.add(new DropDownList(MessageUtils.getString("option_truck"), "3"));

        return insureTypes;
    }

    public List<DropDownList> ddlCustomer() {

        List<DropDownList> customers = new ArrayList<>();
        DropDownList criteria = new DropDownList();
        criteria.setTableName("SV_USER");
        criteria.setOrderByField("FNAME");
        criteria.setName("FNAME");
        criteria.setValue("USER_ID");
        criteria.setSortName("ASC");
        criteria.setCondition("USER_GROUP_LVL = 3");

        customers = commonService.getDropdownList(criteria);
        return customers;
    }

    public List<DropDownList> ddlBroker() {

        List<DropDownList> customers = new ArrayList<>();
        DropDownList criteria = new DropDownList();
        criteria.setTableName("BROKER");
        criteria.setOrderByField("BROKER_NAME");
        criteria.setName("BROKER_NAME");
        criteria.setValue("BROKER_ID");
        criteria.setSortName("ASC");

        customers = commonService.getDropdownList(criteria);
        return customers;
    }

    public List<DropDownList> ddlProduct() {

        List<DropDownList> insureTypes = new ArrayList<>();
        insureTypes.add(new DropDownList("Product A", "1"));
        insureTypes.add(new DropDownList("Product B", "2"));
        insureTypes.add(new DropDownList("Product B", "3"));

        return insureTypes;
    }

    public List<DropDownList> ddlInsureName() {

        List<DropDownList> customers = new ArrayList<>();
        DropDownList criteria = new DropDownList();
        criteria.setTableName("insures");
        criteria.setOrderByField("insured_name");
        criteria.setName("insured_name");
        criteria.setValue("insured_id");
        criteria.setSortName("ASC");

        customers = commonService.getDropdownList(criteria);
        return customers;
    }

    public List<DropDownList> ddlCurrencyType() {

        List<DropDownList> customers = new ArrayList<>();
        DropDownList criteria = new DropDownList();
        criteria.setTableName("currency_type");
        criteria.setOrderByField("currency_name");
        criteria.setName("currency_name");
        criteria.setValue("currency_code");
        criteria.setSortName("ASC");

        customers = commonService.getDropdownList(criteria);
        return customers;
    }

    public List<DropDownList> ddlCountries() {

        List<DropDownList> customers = new ArrayList<>();
        DropDownList criteria = new DropDownList();
        criteria.setTableName("apps_countries");
        criteria.setOrderByField("country_name");
        criteria.setName("country_name");
        criteria.setValue("country_code");
        criteria.setSortName("ASC");

        customers = commonService.getDropdownList(criteria);
        return customers;
    }

    public List<DropDownList> ddlCommodityType() {

        List<DropDownList> customers = new ArrayList<>();
        DropDownList criteria = new DropDownList();
        criteria.setTableName("commodity_type");
        criteria.setOrderByField("commodity_type_name");
        criteria.setName("commodity_type_name");
        criteria.setValue("commodity_type_id");
        criteria.setSortName("ASC");

        customers = commonService.getDropdownList(criteria);
        return customers;
    }

    public List<DropDownList> ddlCoverageType() {

        List<DropDownList> customers = new ArrayList<>();
        DropDownList criteria = new DropDownList();
        criteria.setTableName("coverage_type");
        criteria.setOrderByField("coverage_type_name");
        criteria.setName("coverage_type_name");
        criteria.setValue("coverage_type_id");
        criteria.setSortName("ASC");

        customers = commonService.getDropdownList(criteria);
        return customers;
    }

    public List<DropDownList> ddlInsuringTermsType() {

        List<DropDownList> customers = new ArrayList<>();
        DropDownList criteria = new DropDownList();
        criteria.setTableName("insuring_terms");
        criteria.setOrderByField("insuring_terms_name");
        criteria.setName("insuring_terms_name");
        criteria.setValue("insuring_terms_id");
        criteria.setSortName("ASC");

        customers = commonService.getDropdownList(criteria);
        return customers;
    }

    public List<DropDownList> ddlClaimSurveyors() {

        List<DropDownList> customers = new ArrayList<>();
        DropDownList criteria = new DropDownList();
        criteria.setTableName("claim_surveyors");
        criteria.setOrderByField("surveyor_name");
        criteria.setName("surveyor_name");
        criteria.setValue("surveyor_id");
        criteria.setSortName("ASC");

        customers = commonService.getDropdownList(criteria);
        return customers;
    }

    private static List<DropDownList> claimStatus;

    public List<DropDownList> ddlStatus() {
        if (claimStatus == null) {
            claimStatus = new ArrayList<>();
            DropDownList criteria = new DropDownList();
            criteria.setTableName("claim_status");
            criteria.setOrderByField("claim_status_name");
            criteria.setName("claim_status_name");
            criteria.setValue("claim_status_id");
            criteria.setSortName("ASC");

            claimStatus = commonService.getDropdownList(criteria);
        }
        return claimStatus;
    }
    
    public List<DropDownList> ddlRateSchedule() {

        List<DropDownList> customers = new ArrayList<>();
        DropDownList criteria = new DropDownList();
        criteria.setTableName("rate_schedule");
        criteria.setOrderByField("rate_schedule_name");
        criteria.setName("CONCAT(rate_schedule_name , '-------------------------------------------------------------------' , rate )");
        criteria.setValue("rate");
        criteria.setSortName("ASC");

        customers = commonService.getDropdownList(criteria);
        return customers;
    }
}
