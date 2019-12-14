/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.technicocommercial.rest;

import Exceptions.FormateurNotFoundException;
import Exceptions.LienFormateurFormationException;
import com.barban.corentin.technicoCommercial.services.ServiceTechnicoCommercialLocal;
import com.google.gson.Gson;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author jdetr
 */
@Path("formationsCatalogue/{code}/salle/{sallekey}")
@RequestScoped
public class FormationCatalogueSalleResource {

    ServiceTechnicoCommercialLocal serviceTC = lookupServiceTechnicoCommercialLocal();
    
    @Context
    private UriInfo context;
    private Gson gson;

    /**
     * Creates a new instance of FormationCatalogueSalleResource
     */
    public FormationCatalogueSalleResource() {
        this.gson = new Gson();
    }

    /**
     * Post JSON 
     * @param code code 
     * @param sallekey key de la salle 
     * @return boolean 
     * @throws FormateurNotFoundException formateur non trouvé 
     * @throws LienFormateurFormationException lien formateur formation non trouvé 
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public boolean postJson(@PathParam("code") String code, @PathParam("sallekey") int sallekey) throws FormateurNotFoundException, LienFormateurFormationException {
        return this.serviceTC.ajouterSalleDansFormation(code, sallekey);
    }
    
    /**
     * delete JSON 
     * @param code code
     * @param sallekey salle key 
     * @return boolean 
     * @throws FormateurNotFoundException formateur non trouvé 
     * @throws LienFormateurFormationException lien formateur formation non trouvé 
     */
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public boolean deleteJson(@PathParam("code") String code, @PathParam("sallekey") int sallekey) throws FormateurNotFoundException, LienFormateurFormationException {
        return this.serviceTC.supprimerSalleDeFormation(code, sallekey);
    }
    
    /**
     * PUT method for updating or creating an instance of FormationCatalogueSalleResource
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
