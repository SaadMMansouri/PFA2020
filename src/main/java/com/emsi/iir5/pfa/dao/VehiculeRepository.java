package com.emsi.iir5.pfa.dao;

import com.emsi.iir5.pfa.entities.Vehicule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Collection;
import java.util.List;

@CrossOrigin("*")
@RepositoryRestResource
public interface VehiculeRepository extends JpaRepository<Vehicule, Integer> {
    @RestResource(path = "/etat")
    @Query("SELECT v FROM Vehicule v WHERE v.etat LIKE CONCAT('%',:etat,'%')")
    public List<Vehicule> findByEtatContains(@Param("etat") String etat);

    @RestResource(path = "/byMatricule")
    public List<Vehicule> findByMatriculeContains( @Param("matr") String matricule);
    // pour tester http://localhost:8999/vehicules/search/byMatricule?matr=25sq

    @RestResource(path = "/byEtatPage")
    public Page<Vehicule> findByEtatContains(@Param("etat") String etat, Pageable pageable);
    // pour tester http://localhost:8999/vehicules/search/byEtatPage?etat=Active&page=0&size=1

}
