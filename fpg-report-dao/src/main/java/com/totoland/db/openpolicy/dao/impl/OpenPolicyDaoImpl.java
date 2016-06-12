/*
 * Copyright (C) 2016 thanapong.n
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package com.totoland.db.openpolicy.dao.impl;

import com.totoland.db.bean.OpenPolicyCriteria;
import com.totoland.db.common.dao.hibernate.GennericDaoImpl;
import com.totoland.db.entity.OpenPolicy;
import com.totoland.db.openpolicy.dao.OpenPolicyDao;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author thanapong.n
 */
@Repository
public class OpenPolicyDaoImpl extends GennericDaoImpl<OpenPolicy> implements OpenPolicyDao {

    private static final long serialVersionUID = 4524461589080267071L;

    @Transactional(readOnly = true)
    @Override
    public List<OpenPolicy> findByCriteria(OpenPolicyCriteria criteria) {

        String SQL = "select * from open_policy where 1=1 ";
        List<Object> params = new ArrayList<>();

        if (criteria.getOpenPolicyNo() != null && !criteria.getOpenPolicyNo().trim().isEmpty()) {
            SQL += "and open_policy = ?";
            params.add(criteria.getOpenPolicyNo());
        }
        
//        if (criteria.getProductId()!= null) {
//            SQL += "and product_id = ?";
//            params.add(criteria.getProductId());
//        }

        return findNativeQuery(SQL, OpenPolicy.class, params);
    }

}
