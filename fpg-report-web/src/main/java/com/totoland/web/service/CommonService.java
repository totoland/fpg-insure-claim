/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.totoland.web.service;

import com.totoland.db.common.entity.DropDownList;
import java.util.List;

/**
 *
 * @author Totoland
 */
public interface CommonService {
    List<DropDownList> getDropdownList(DropDownList dropDownList);
}
