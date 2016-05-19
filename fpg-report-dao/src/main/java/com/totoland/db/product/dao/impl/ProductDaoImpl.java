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
package com.totoland.db.product.dao.impl;

import com.totoland.db.common.dao.hibernate.GennericDaoImpl;
import com.totoland.db.entity.Product;
import com.totoland.db.product.dao.ProductDao;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author totoland
 */
@Repository
public class ProductDaoImpl extends GennericDaoImpl<Product> implements ProductDao {

    private static final long serialVersionUID = 5227730327680842144L;

    @Transactional(readOnly = true)
    @Override
    public List<Product> findById(String productId) {
        if (productId == null || productId.trim().isEmpty()) {
            return findNativeQuery("SELECT * FROM PRODUCT", Product.class);
        }

        return findNativeQuery("SELECT * FROM PRODUCT WHERE PRODUCT_ID = ? ", Product.class, productId);
    }

}
