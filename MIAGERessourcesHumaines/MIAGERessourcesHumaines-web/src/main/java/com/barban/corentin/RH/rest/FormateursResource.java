/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.RH.rest;

import com.barban.corentin.RH.services.ServiceRHLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author julie
 */
@Path("formateurs")
@RequestScoped
public class FormateursResource {

    ServiceRHLocal serviceRH = lookupServiceRHLocal();

    @Context
    private UriInfo context;
    
    

    /**
     * Creates a new instance of FormateursResource
     */
    public FormateursResource() {
    }

    /**
     * Retrieves representation of an instance of com.barban.corentin.RH.rest.FormateursResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of FormateursResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }

    private ServiceRHLocal lookupServiceRHLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (ServiceRHLocal) c.lookup("java:global/com.barban.corentin_MIAGERessourcesHumaines-ear_ear_1.0-SNAPSHOT/com.barban.corentin_MIAGERessourcesHumaines-ejb_ejb_1.0-SNAPSHOT/ServiceRH!com.barban.corentin.RH.services.ServiceRHLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
