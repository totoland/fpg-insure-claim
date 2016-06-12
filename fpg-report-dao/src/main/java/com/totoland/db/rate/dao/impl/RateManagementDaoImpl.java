/*
 * The MIT License
 *
 * Copyright 2016 totoland.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.totoland.db.rate.dao.impl;

import com.totoland.db.bean.OpenPolicyCriteria;
import com.totoland.db.common.dao.hibernate.GennericDaoImpl;
import com.totoland.db.entity.ProductRate;
import com.totoland.db.entity.ViewProductRate;
import com.totoland.db.rate.dao.RateManagementDao;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 *
 * @author totoland
 */
@Repository
public class RateManagementDaoImpl extends GennericDaoImpl<ProductRate> implements RateManagementDao {

    private static final long serialVersionUID = 746663340600925347L;

    @Transactional(readOnly = true)
    @Override
    public List<ProductRate> findByCriteria(OpenPolicyCriteria criteria) {
        String SQL = "SELECT product.product_name, product_rate.* FROM product_rate inner join product on product.product_id = product_rate.product_id WHERE 1=1 ";

        List<Object> params = new ArrayList<>();

        if (!StringUtils.isEmpty(criteria.getProductId())) {
            SQL += "and product_rate.product_id = ? ";
            params.add(criteria.getProductId());
        }
        if (!StringUtils.isEmpty(criteria.getOpenPolicyNo())) {
            SQL += "and product_rate.open_policy_no = ? ";
            params.add(criteria.getOpenPolicyNo());
        }
        
        return findNativeQuery(SQL, ProductRate.class, params);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ViewProductRate> findDetailByCriteria(OpenPolicyCriteria criteria) {
        String SQL = "SELECT "
                + "product_rate.product_rate_id, "
                + "product_rate.product_rate, "
                + "product_rate.customer_id, "
                + "product_rate.product_id, "
                + "sv_user.USERNAME customer_name, "
                + "product.product_name "
                + "FROM "
                + "product_rate "
                + "INNER JOIN sv_user ON product_rate.customer_id = sv_user.USER_ID "
                + "INNER JOIN product ON product_rate.product_id = product.product_id WHERE 1 = 1 ";

        List<Object> params = new ArrayList<>();

        if (!StringUtils.isEmpty(criteria.getProductId())) {
            SQL += "and product_rate.product_id = ? ";
            params.add(criteria.getProductId());
        }

        return findNativeQuery(SQL, ViewProductRate.class, params);
    }

}
