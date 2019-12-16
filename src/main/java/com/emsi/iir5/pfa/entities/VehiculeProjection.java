package com.emsi.iir5.pfa.entities;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "P1",types = Vehicule.class)
public interface VehiculeProjection {
    public Integer getIdVehicule();
    public String getMatricule();
}
