/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.technicocommercial.rest;
import Exceptions.FormationCatalogueException;
import Exceptions.FormationCatalogueNotFoundException;
import com.barban.corentin.technicoCommercial.services.ServiceTechnicoCommercialLocal;
import com.google.gson.Gson;
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
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author jdetr
 */
@Path("formationsCatalogue")
@RequestScoped
public class FormationsCatalogueResource {

    ServiceTechnicoCommercialLocal serviceTC = lookupServiceTechnicoCommercialLocal();
    
    @Context
    private UriInfo context;
    private Gson gson;

    /**
     * Creates a new instance of FormationsCatalogueResource
     */
    public FormationsCatalogueResource() {
        this.gson = new Gson();
    }

    /**
     * Retrieves representation of an instance of com.barban.corentin.technicocommercial.rest.FormationsCatalogueResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        return this.gson.toJson(this.serviceTC.listerCatalogueFormations());
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String postJson(@QueryParam("code") String code, @QueryParam("intitule") String intitule, @QueryParam("niveau") String niveau, @QueryParam("typeduree") String typeduree, @QueryParam("capacitemin") Integer capacitemin, @QueryParam("capacitemax") Integer capacitemax, @QueryParam("tarifforfaitaire") Double tarifforfaitaire) throws FormationCatalogueNotFoundException, FormationCatalogueException {
        return this.serviceTC.ajouterFormationCatalogue(code, intitule, niveau, typeduree, capacitemin, capacitemax, tarifforfaitaire).toString();
    }
    
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public boolean deleteJson(@PathParam("code") String code) throws FormationCatalogueNotFoundException {
        return this.serviceTC.supprimerFormationCatalogue(code);
    }
            
    
    /**
     * PUT method for updating or creating an instance of FormationsCatalogueResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
    private ServiceTechnicoCommercialLocal lookupServiceTechnicoCommercialLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (ServiceTechnicoCommercialLocal) c.lookup("java:global/MIAGETechnicoCommercial-ear/MIAGETechnicoCommercial-ejb-1.0-SNAPSHOT/ServiceTechnicoCommercial!com.barban.corentin.technicoCommercial.services.ServiceTechnicoCommercialLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
