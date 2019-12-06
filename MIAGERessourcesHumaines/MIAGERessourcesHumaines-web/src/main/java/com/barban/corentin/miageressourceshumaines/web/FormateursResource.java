/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.miageressourceshumaines.web;

import com.google.gson.Gson;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Corentin
 */
@Path("formateurs")
public class FormateursResource {

    com.barban.corentin.RH.services.ServiceRHLocal serviceRH = lookupServiceRHLocal();

    @Context
    private UriInfo context;

    private Gson gson;
    
    
    /**
     * Creates a new instance of FormateursResource
     */
    public FormateursResource() {
        this.gson = new Gson();
    }

    /**
     * Retrieves representation of an instance of
     * com.barban.corentin.miageressourceshumaines.web.FormateursResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        return this.gson.toJson(this.serviceRH.listerFormateurs());
    }

    /**
     * PUT method for updating or creating an instance of FormateursResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }

    private com.barban.corentin.RH.services.ServiceRHLocal lookupServiceRHLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (com.barban.corentin.RH.services.ServiceRHLocal) c.lookup("java:global/MIAGERessourcesHumaines-ear/MIAGERessourcesHumaines-ejb-1.0-SNAPSHOT/ServiceRH!com.barban.corentin.RH.services.ServiceRHLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
