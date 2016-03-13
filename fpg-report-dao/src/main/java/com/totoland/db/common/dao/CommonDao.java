/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.totoland.db.common.dao;

import com.totoland.db.common.entity.DropDownList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public interface CommonDao {
    List<DropDownList> getDropdownList(DropDownList dropDownList);
}
