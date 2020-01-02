package com.emsi.iir5.pfa.entities;

import org.springframework.data.rest.core.config.Projection;

import java.util.Date;
import java.util.List;

@Projection(name = "entretienProjection",types = Entretien.class)
public interface EntretienProjection {
    Integer getIdEntretien();
    String getDescription();
    String getCommentaireResponsable();
    String getStatus();
    Date getDatePlanifierEntretien();
    Date getDateValiderEntretien();
    RespEntretien getRespEntretien();
    Vehicule getVehicule();
    Date getCreatedAt();
    Date getUpdatedAt();
}

