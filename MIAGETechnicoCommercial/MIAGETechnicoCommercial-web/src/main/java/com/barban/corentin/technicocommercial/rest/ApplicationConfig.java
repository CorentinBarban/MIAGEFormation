/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.technicocommercial.rest;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Corentin
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.barban.corentin.technicocommercial.rest.FormateursResource.class);
        resources.add(com.barban.corentin.technicocommercial.rest.FormationCatalogueFormateurResource.class);
        resources.add(com.barban.corentin.technicocommercial.rest.FormationCatalogueResource.class);
        resources.add(com.barban.corentin.technicocommercial.rest.FormationCatalogueSalleResource.class);
        resources.add(com.barban.corentin.technicocommercial.rest.FormationsCatalogueResource.class);
        resources.add(com.barban.corentin.technicocommercial.rest.SallesResource.class);
    }
    
}
