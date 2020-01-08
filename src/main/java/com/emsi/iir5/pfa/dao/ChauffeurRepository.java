package com.emsi.iir5.pfa.dao;

import com.emsi.iir5.pfa.entities.Chauffeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Collection;


@CrossOrigin("*")
@RepositoryRestResource
public interface ChauffeurRepository extends JpaRepository<Chauffeur, Integer> {
    @RestResource(path = "/disponible")
    @Query("SELECT ch FROM Chauffeur ch WHERE ch.disponible LIKE CONCAT('%',:isdisponible,'%')")
    Collection<Chauffeur> findByDisponibleContains(@Param("isdisponible") String isdisponible);

}
