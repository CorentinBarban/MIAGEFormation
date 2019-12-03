/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.commercial.rest;

import Exceptions.FormationCatalogueNotFoundException;
import com.barban.corentin.commercial.services.serviceGestionCommercialeLocal;
import com.google.gson.Gson;
import java.text.ParseException;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Corentin
 */
@Path("demandeFormation")
@RequestScoped
public class DemandeFormationResource {

    serviceGestionCommercialeLocal serviceGestionCommerciale = lookupserviceGestionCommercialeLocal();

    @Context
    private UriInfo context;

    private Gson gson;

    /**
     * Creates a new instance of DemandeFormationResource
     */
    public DemandeFormationResource() {
        this.gson = new Gson();
    }

    /**
     * Retrieves representation of an instance of
     * com.barban.corentin.commercial.rest.DemandeFormationResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJson() {
        return Response.status(Response.Status.FORBIDDEN).build();
    }

    /**
     * PUT method for updating or creating an instance of
     * DemandeFormationResource
     *
     * @param nomClient
     * @param codeFormation
     * @param intitule
     * @param codeClient
     * @param nbPersonnes
     * @throws Exceptions.FormationCatalogueNotFoundException
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(@QueryParam("nomClient") String nomClient, @QueryParam("codeFormation") String codeFormation, @QueryParam("intitule") String intitule, @QueryParam("codeClient") Integer codeClient, @QueryParam("nbPersonnes") Integer nbPersonnes) throws FormationCatalogueNotFoundException, ParseException {

        this.serviceGestionCommerciale.demanderFormation(nomClient, codeFormation, intitule, codeClient, nbPersonnes);

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
