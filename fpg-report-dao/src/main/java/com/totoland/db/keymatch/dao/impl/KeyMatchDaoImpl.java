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
package com.totoland.db.keymatch.dao.impl;

import com.totoland.db.dao.BaseDao;
import com.totoland.db.entity.KeyMatch;
import com.totoland.db.keymatch.dao.KeyMatchDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author totoland
 */
@Repository
public class KeyMatchDaoImpl extends BaseDao implements KeyMatchDao {

    @Transactional
    @Override
    public void updateKeyMatch(KeyMatch entity) {
        getHibernateTemplate().merge(entity);
    }

    @Transactional(readOnly = true)
    @Override
    public KeyMatch findByCustomerId(String customerId) {
        return (KeyMatch) findUniqNativeQuery("SELECT * FROM key_match WHERE CUST_ID=?", KeyMatch.class, customerId);
    }

}
