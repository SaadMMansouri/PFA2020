package com.emsi.iir5.pfa.entities;

import org.springframework.data.rest.core.config.Projection;

import java.util.Date;

@Projection(name = "voyageProjection",types = Voyage.class)
public interface VoyageProjection {
    Integer getIdVoyage();
    Date getDateVoyage();
    Trajet getTrajet();
    Chauffeur getChauffeur();
    Vehicule getVehicule();
    Date getCreatedAt();
    Date getUpdatedAt();
}
