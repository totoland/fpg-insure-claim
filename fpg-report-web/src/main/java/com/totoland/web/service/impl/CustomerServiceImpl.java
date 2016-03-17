/*
 * Copyright (C) 2016 totoland
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
package com.totoland.web.service.impl;

import com.totoland.db.enums.UserType;
import com.totoland.db.user.dao.UserDao;
import com.totoland.web.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author totoland
 */
@Service(value = "customerService")
public class CustomerServiceImpl implements CustomerService{

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);
    
    @Autowired
    UserDao userDao;
    
    @Override
    public long countAllCustomer() {
        return userDao.countUser(UserType.CUSTOMER);
    }
    
}
