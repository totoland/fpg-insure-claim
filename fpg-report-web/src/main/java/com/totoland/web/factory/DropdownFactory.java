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

    /**
     * *
     * getFiscalYear
     *
     * @return ปีงบประมาณ ย้อนหลัง 10 ปี
     */
    public List<DropDownList> ddlFiscalYear() {

        List<DropDownList> dropDownLists = new ArrayList<>();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");

        int curYear = Integer.parseInt(dateFormat.format(new Date()));

        for (int i = 0; i < 10; i++) {
            dropDownLists.add(new DropDownList(String.valueOf(curYear - i), String.valueOf(curYear - i)));
        }

        return dropDownLists;
    }

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
}
