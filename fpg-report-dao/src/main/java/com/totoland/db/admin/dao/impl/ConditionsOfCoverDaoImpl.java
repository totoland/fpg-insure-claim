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
package com.totoland.db.admin.dao.impl;

import com.totoland.db.admin.dao.ConditionsOfCoverDao;
import com.totoland.db.bean.ConditionsOfCoverCriteria;
import com.totoland.db.common.dao.hibernate.GennericDaoImpl;
import com.totoland.db.entity.ConditionsOfCover;
import com.totoland.db.entity.ViewConditionsOfCover;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 *
 * @author totoland
 */
@Repository
public class ConditionsOfCoverDaoImpl extends GennericDaoImpl<ConditionsOfCover> implements ConditionsOfCoverDao {

    private static final long serialVersionUID = 7615685221888510973L;

    @Transactional(readOnly = true)
    @Override
    public List<ViewConditionsOfCover> findByCriteria(ConditionsOfCoverCriteria criteria) {
        String SQL = "SELECT "
                + "conditions_of_cover.conditions_cover_id, "
                + "conditions_of_cover.air_conditions, "
                + "conditions_of_cover.vessel_conditions, "
                + "conditions_of_cover.truck_conditions, "
                + "conditions_of_cover.customer_id, "
                + "sv_user.COMPANY_NAME customer_name "
                + "FROM "
                + "conditions_of_cover "
                + "INNER JOIN sv_user ON conditions_of_cover.customer_id = sv_user.USER_ID ";

        if (!StringUtils.isEmpty(criteria.getCustomerId())) {
            SQL += "WHERE conditions_of_cover.customer_id = ?";
            return findNativeQuery(SQL, ViewConditionsOfCover.class, criteria.getCustomerId());
        }

        return findNativeQuery(SQL, ViewConditionsOfCover.class);
    }

}
