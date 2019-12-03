/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.commercial.rest;

import com.barban.corentin.commercial.services.serviceGestionCommercialeLocal;
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
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Corentin
 */
@Path("comptesRendus")
@RequestScoped
public class ComptesRendusResource {

    serviceGestionCommercialeLocal serviceGestionCommerciale = lookupserviceGestionCommercialeLocal();

    @Context
    private UriInfo context;
    private Gson gson;

    /**
     * Creates a new instance of ComptesRendusResource
     */
    public ComptesRendusResource() {
        this.gson = new Gson();
    }

    /**
     * Retrieves representation of an instance of
     * com.barban.corentin.commercial.rest.ComptesRendusResource
     *
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public void getJson() {
       this.serviceGestionCommerciale.demandeEditionComptesRendus();
    }

    /**
     * PUT method for updating or creating an instance of ComptesRendusResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }

    private serviceGestionCommercialeLocal lookupserviceGestionCommercialeLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (serviceGestionCommercialeLocal) c.lookup("java:global/MIAGECommercial-ear/MIAGECommercial-ejb-1.0-SNAPSHOT/serviceGestionCommerciale");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
