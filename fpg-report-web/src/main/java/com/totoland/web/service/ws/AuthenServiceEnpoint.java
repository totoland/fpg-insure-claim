/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.totoland.web.service.ws;

import com.google.gson.Gson;
import com.totoland.db.authen.dao.AuthenDao;
import com.totoland.db.entity.SvUser;
import com.totoland.db.entity.ViewUser;
import com.totoland.web.service.GennericService;
import com.totoland.web.utils.WebUtils;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 * REST Web Service
 *
 * @author Totoland
 */
@Path("authenServiceEnpoint")
public class AuthenServiceEnpoint {

    @Autowired
    GennericService gennericService;
    @Autowired
    AuthenDao authenDao;
    /**
     * Creates a new instance of GetCustomerInfoWS
     */
    public AuthenServiceEnpoint() {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    /**
     * Retrieves representation of an instance of
     * com.totoland.web.service.ws.GetCustomerInfoWS
     *
     * @param pwd
     * @return an instance of java.lang.String
     * @throws java.lang.Exception
     */
//    @GET
//    @Produces("application/json")
//    @Path("/json")
//    public String getJson() {
//        //TODO return proper representation object
//        List<SvUser> listUser = DaoFactory.createGennericService().findAll(SvUser.class);
//
//        return new Gson().toJson(listUser);
//    }

    @GET
    @Produces("application/json")
    @Path("/{user}/{pwd}")
    public String getMsg(@PathParam("user") String user,
                         @PathParam("pwd") String pwd) throws Exception {

        ViewUser viewUser = authenDao.loginUser(user, WebUtils.encrypt(pwd));

        return new Gson().toJson(viewUser);

    }
}
