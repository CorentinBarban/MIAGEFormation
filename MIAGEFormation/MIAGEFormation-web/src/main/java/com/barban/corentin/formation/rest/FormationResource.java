/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.formation.rest;

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
import com.barban.corentin.formation.services.ServiceFormationLocal;

/**
 * REST Web Service
 *
 * @author Corentin
 */
@Path("formations/{id}")
@RequestScoped
public class FormationResource {

    ServiceFormationLocal serviceFormation = lookupserviceFormationLocal();
    private Gson gson;
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of FormationResource
     */
    public FormationResource() {
        this.gson = new Gson();
    }

    /**
     * REtourner le statut d'une formation
     *
     * @param id idenfiant d'une formation
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson(@PathParam("id") String id) {
        return this.gson.toJson(this.serviceFormation.demanderEtatFormation(Integer.parseInt(id)));
    }

    /**
     * PUT method for updating or creating an instance of FormationResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }

    /**
     * look up service formation local
     * @return 
     */
    private com.barban.corentin.formation.services.ServiceFormationLocal lookupserviceFormationLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (com.barban.corentin.formation.services.ServiceFormationLocal) c.lookup("java:global/MIAGEFormation-ear/MIAGEFormation-ejb-1.0-SNAPSHOT/serviceFormation!com.barban.corentin.formation.services.serviceFormationLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
