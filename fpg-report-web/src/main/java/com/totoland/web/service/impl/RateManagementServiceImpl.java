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
package com.totoland.web.service.impl;

import com.totoland.db.bean.ProductRateCriteria;
import com.totoland.db.common.dao.GennericDao;
import com.totoland.db.entity.ProductRate;
import com.totoland.db.entity.Valuation;
import com.totoland.db.entity.ViewProductRate;
import com.totoland.db.rate.dao.RateManagementDao;
import com.totoland.web.service.RateManagementService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author totoland
 */
@Service("rateManagementService")
public class RateManagementServiceImpl extends GennericServiceImpl<ProductRate> implements RateManagementService {

    @Autowired
    RateManagementDao dao;
    
    @Autowired
    GennericDao<Valuation>gennericDao;

    @Override
    public List<ProductRate> findByCriteria(ProductRateCriteria criteria) {
        return dao.findByCriteria(criteria);
    }

    @Override
    public List<ViewProductRate> findDetailByCriteria(ProductRateCriteria criteria) {
        return dao.findDetailByCriteria(criteria);
    }

    @Transactional(rollbackFor = {Throwable.class})
    @Override
    public void createWithValuation(ProductRate rate, Valuation valuation) {
        dao.create(rate);
        gennericDao.edit(valuation);
    }
    
    @Transactional(rollbackFor = {Throwable.class})
    @Override
    public void updateWithValuation(ProductRate rate, Valuation valuation) {
        dao.edit(rate);
        gennericDao.edit(valuation);
    }

}
