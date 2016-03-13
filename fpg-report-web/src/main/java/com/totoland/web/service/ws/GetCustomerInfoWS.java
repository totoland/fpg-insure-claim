/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.totoland.web.service.ws;

import com.google.gson.Gson;
import com.totoland.db.entity.SvUser;
import com.totoland.web.service.GennericService;
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
@Path("getCustomerInfo")
public class GetCustomerInfoWS {

    @Autowired
    GennericService gennericService;
    /**
     * Creates a new instance of GetCustomerInfoWS
     */
    public GetCustomerInfoWS() {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    /**
     * Retrieves representation of an instance of
     * com.totoland.web.service.ws.GetCustomerInfoWS
     *
     * @return an instance of java.lang.String
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
    @Path("/{param}")
    public String getMsg(@PathParam("param") String msg) {

        List<SvUser> listUser = gennericService.findAll(SvUser.class);

        return new Gson().toJson(listUser);

    }
}
