package com.emsi.iir5.pfa.dao;

import com.emsi.iir5.pfa.entities.Voyage;
import com.emsi.iir5.pfa.entities.VoyageProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin("*")
@RepositoryRestResource(excerptProjection = VoyageProjection.class)
public interface VoyageRepository extends JpaRepository<Voyage, Integer> {
    //@Query(value="SELECT idVoyage,dateVoyage,trajet,chauffeur,vehicule FROM Voyage",nativeQuery=true)
    //private List<Voyage> getVoyages();
}
