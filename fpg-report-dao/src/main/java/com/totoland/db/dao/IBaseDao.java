/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.totoland.db.dao;

import java.util.List;

/**
 *
 * @author totoland
 */
public interface IBaseDao {
    public Object findUniqNativeQuery(final String SQL, final Class entity, final Object... value);
    
    public List findNativeQuery(final String SQL, final Class entity, final List<Object> value);
    
    public List findNativeQuery(final String SQL, final Class entity, final Object... value);

    public List findNativeQuery(final String SQL, final Class entity);
    
    public List findNativePagginQuery(final String SQL,final int start,final int max, final Class entity);

    public String genSQLPaggin(String sql, int start, int max);

    public Integer countNativeQuery(final String SQL, final Object... value);
    
    public Integer countNativeQuery(final String SQL);

    public Integer updateNativeQuery(final String SQL, final Object... value);
}
