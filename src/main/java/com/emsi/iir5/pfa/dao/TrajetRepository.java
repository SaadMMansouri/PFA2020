package com.emsi.iir5.pfa.dao;


import com.emsi.iir5.pfa.entities.Trajet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin("*")
@RepositoryRestResource
public interface TrajetRepository extends JpaRepository<Trajet, Integer> {
    @RestResource(path = "/byNom")
    public List<Trajet> findByNomTrajetContains(@Param("nom") String nom);
}
