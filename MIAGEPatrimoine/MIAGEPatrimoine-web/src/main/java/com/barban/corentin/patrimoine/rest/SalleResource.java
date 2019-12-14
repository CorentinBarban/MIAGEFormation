/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.patrimoine.rest;

import com.barban.corentin.patrimoine.services.ServicePatrimoineLocal;
import com.google.gson.Gson;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Corentin
 */
@Path("salles/{id}")
@RequestScoped
public class SalleResource {

    ServicePatrimoineLocal servicePatrimoine = lookupServicePatrimoineLocal();

    

    @Context
    private UriInfo context;
    private Gson gson;
    

    /**
     * Creates a new instance of SalleResource
     */
    public SalleResource() {
        this.gson = new Gson();
    }
   
    /**
     *  Retrieves representation of an instance of com.toulouse.miage.m1.CorentinBarban.LaTEM.SalleResource
     * @param id id
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson(@PathParam("id") String id) {

         return this.gson.toJson(this.servicePatrimoine.verifierExistenceSalle(Integer.parseInt(id)));
    }

    /**
     * PUT method for updating or creating an instance of SalleResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }

    /**
     * Cherche le service patrimoine
     * @return  le service patrimoine
     */
    private ServicePatrimoineLocal lookupServicePatrimoineLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (ServicePatrimoineLocal) c.lookup("java:global/MIAGEPatrimoine-ear/MIAGEPatrimoine-ejb-1.0-SNAPSHOT/ServicePatrimoine!com.barban.corentin.patrimoine.services.ServicePatrimoineLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
   
}
