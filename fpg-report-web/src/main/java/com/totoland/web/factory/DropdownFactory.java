/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.totoland.web.factory;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.totoland.db.bean.ProductRateBean;
import com.totoland.db.bean.ValuationBean;
import com.totoland.db.common.entity.DropDownList;
import com.totoland.web.service.CommonService;
import com.totoland.web.utils.MessageUtils;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    private static List<DropDownList> fpgCustLvl;
    private static List<DropDownList> fpgUserLvl;

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
            criteria.setCondition("GROUP_LVL_ID <> 3");

            ectGroupLvl = commonService.getDropdownList(criteria);
        }

        return ectGroupLvl;
    }

    public List<DropDownList> ddlCustomerLvl() {
        if (fpgCustLvl == null) {

            DropDownList criteria = new DropDownList();
            criteria.setTableName("GROUP_LVL");
            criteria.setOrderByField("GROUP_LVL_ID");
            criteria.setName("GROUP_LVL_NAME");
            criteria.setValue("GROUP_LVL_ID");
            criteria.setSortName("ASC");
            criteria.setCondition("GROUP_LVL_ID = 3");

            fpgCustLvl = commonService.getDropdownList(criteria);
        }

        return fpgCustLvl;
    }

    public List<DropDownList> ddllAllUserCustomerLvl() {
        if (fpgUserLvl == null) {

            DropDownList criteria = new DropDownList();
            criteria.setTableName("GROUP_LVL");
            criteria.setOrderByField("GROUP_LVL_ID");
            criteria.setName("GROUP_LVL_NAME");
            criteria.setValue("GROUP_LVL_ID");
            criteria.setSortName("ASC");

            fpgUserLvl = commonService.getDropdownList(criteria);
        }

        return fpgUserLvl;
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

    public List<DropDownList> ddlMethodOfTransport() {

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
        criteria.setOrderByField("COMPANY_NAME");
        criteria.setName("COMPANY_NAME");
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

        List<DropDownList> customers = new ArrayList<>();
        DropDownList criteria = new DropDownList();
        criteria.setTableName("product");
        criteria.setOrderByField("product_name");
        criteria.setName("product_name");
        criteria.setValue("product_id");
        criteria.setSortName("ASC");

        customers = commonService.getDropdownList(criteria);
        return customers;
    }

    public List<DropDownList> ddlInsureName() {

        List<DropDownList> customers = new ArrayList<>();
        DropDownList criteria = new DropDownList();
        criteria.setTableName("sv_user");
        criteria.setOrderByField("company_name");
        criteria.setName("company_name");
        criteria.setValue("user_id");
        criteria.setCondition("USER_GROUP_LVL = 3");
        criteria.setSortName("ASC");

        customers = commonService.getDropdownList(criteria);
        return customers;
    }

    public List<DropDownList> ddlAllInsureName() {

        List<DropDownList> customers = new ArrayList<>();
        DropDownList criteria = new DropDownList();
        criteria.setTableName("sv_user");
        criteria.setOrderByField("company_name");
        criteria.setName("company_name");
        criteria.setValue("user_id");
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
        criteria.setValue("commodity_type_code");
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
        criteria.setTableName("surveyors");
        criteria.setOrderByField("CONCAT(Country ,'---', Location)");
        criteria.setName("CONCAT(Country ,'---', Location)");
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
        criteria.setTableName("view_rate_schedule");
        criteria.setOrderByField("product_name");
        criteria.setName("CONCAT(product_name , '-------------------------' , product_rate)");
        criteria.setValue("product_rate");
        criteria.setSortName("ASC");

        customers = commonService.getDropdownList(criteria);
        return customers;
    }

    private static DecimalFormat formatter = new DecimalFormat("#0.0000");

    public List<DropDownList> ddlRateSchedule(String openPolicyNo) {

        List<DropDownList> resList = new ArrayList<>();
        DropDownList criteria = new DropDownList();
        criteria.setTableName("open_policy");
        criteria.setOrderByField("open_policy_no");
        criteria.setName("'-'");
        criteria.setValue("product_rate_data");
        criteria.setCondition("open_policy_no = '" + openPolicyNo + "'");

        List<DropDownList> ddl = commonService.getDropdownList(criteria);
        if (ddl != null && !ddl.isEmpty()) {
            List<ProductRateBean> beans = new Gson().fromJson(ddl.get(0).getValue(), new TypeToken<List<ProductRateBean>>() {
            }.getType());

            for (ProductRateBean bean : beans) {
                resList.add(new DropDownList(bean.getProductRateDetail() + " + " + formatter.format(bean.getRate()) + "%", String.valueOf(bean.getRate()),BigDecimal.valueOf(bean.getRate())));
            }
        }

        return resList;
    }

    public Map<String, String> ddlConf() {

        List<DropDownList> confs = new ArrayList<>();
        DropDownList criteria = new DropDownList();
        criteria.setTableName("conf");
        criteria.setOrderByField("conf_name");
        criteria.setName("conf_name");
        criteria.setValue("conf_value");
        criteria.setSortName("ASC");

        confs = commonService.getDropdownList(criteria);

        Map<String, String> map = new HashMap<>();

        if (confs != null && !confs.isEmpty()) {
            for (DropDownList ddl : confs) {
                map.put(ddl.getName(), ddl.getValue());
            }
        }

        return map;
    }

    public String getCurrentExchangeRate() {

        List<DropDownList> customers = new ArrayList<>();
        DropDownList criteria = new DropDownList();
        criteria.setTableName("conf");
        criteria.setName("conf_name");
        criteria.setValue("conf_value");
        criteria.setCondition("conf_name like '%exchange_rate%'");

        customers = commonService.getDropdownList(criteria);

        System.out.println(customers);

        if (customers != null && !customers.isEmpty()) {
            return customers.get(0).getValue();
        }

        return "0";
    }

    public String getMinimumPremium() {

        List<DropDownList> customers = new ArrayList<>();
        DropDownList criteria = new DropDownList();
        criteria.setTableName("conf");
        criteria.setOrderByField("conf_name");
        criteria.setName("conf_name");
        criteria.setValue("conf_value");
        criteria.setSortName("ASC");
        criteria.setCondition("conf_name = 'min_premium'");

        customers = commonService.getDropdownList(criteria);

        if (customers != null && !customers.isEmpty()) {
            return customers.get(0).getValue();
        }

        return null;
    }

    public List<DropDownList> getListOpenPolicyNumber() {

        DropDownList criteria = new DropDownList();
        criteria.setTableName("open_policy");
        criteria.setOrderByField("open_policy_no");
        criteria.setName("DISTINCT(open_policy_no)");
        criteria.setValue("open_policy_no");
        criteria.setSortName("ASC");

        return commonService.getDropdownList(criteria);
    }

    public List<DropDownList> getValuation(String openPolicyNo) {

        List<DropDownList> resList = new ArrayList<>();
        DropDownList criteria = new DropDownList();
        criteria.setTableName("open_policy");
        criteria.setOrderByField("open_policy_no");
        criteria.setName("'-'");
        criteria.setValue("valuation_data");
        criteria.setCondition("open_policy_no = '" + openPolicyNo + "'");

        List<DropDownList> ddl = commonService.getDropdownList(criteria);
        if (ddl != null && !ddl.isEmpty()) {
            List<ValuationBean> beans = new Gson().fromJson(ddl.get(0).getValue(), new TypeToken<List<ValuationBean>>() {
            }.getType());

            for (ValuationBean bean : beans) {
                resList.add(new DropDownList(bean.getName() + " + " + bean.getPercen() + "%", String.valueOf(bean.getPercen())));
            }
        }

        return resList;
    }
}
