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
package com.totoland.db.openpolicy.dao;

import com.totoland.db.bean.OpenPolicyCriteria;
import com.totoland.db.common.dao.GennericDao;
import com.totoland.db.entity.OpenPolicy;
import java.util.List;

/**
 *
 * @author thanapong.n
 */
public interface OpenPolicyDao extends GennericDao<OpenPolicy>{

    List<OpenPolicy> findByCriteria(OpenPolicyCriteria criteria);
}
