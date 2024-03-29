/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.technicocommercial.rest;

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
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author jdetr
 */
@Path("formationsCatalogue/{code}")
@RequestScoped
public class FormationCatalogueResource {

    ServiceTechnicoCommercialLocal serviceTC = lookupServiceTechnicoCommercialLocal();
    
    @Context
    private UriInfo context;
    private Gson gson;

    /**
     * Creates a new instance of FormationCatalogueResource
     */
    public FormationCatalogueResource() {
        this.gson = new Gson();
    }

    /**
     * Retrieves representation of an instance of com.barban.corentin.technicocommercial.rest.FormationCatalogueResource
     * @param code code 
     * @return an instance of java.lang.String
     * @throws Exceptions.FormationCatalogueNotFoundException formation catalogue non trouvé 
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson(@PathParam("code") String code) throws FormationCatalogueNotFoundException {
        return this.gson.toJson(this.serviceTC.consulterFormationCatalogue(code));
    }

    /**
     * PUT method for updating or creating an instance of FormationCatalogueResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public boolean deleteJson(@PathParam("code") String code) throws FormationCatalogueNotFoundException {
        return this.serviceTC.supprimerFormationCatalogue(code);
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
