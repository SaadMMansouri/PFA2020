package com.emsi.iir5.pfa.dao;

import com.emsi.iir5.pfa.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RepositoryRestResource
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
    @RestResource(path = "/byNom")
    public List<Utilisateur> findByNomContains(@Param("nom") String nom);
    @RestResource(path = "/byEmail")
    public Optional<Utilisateur> findByEmail(String email);

    @Query(value = "SELECT profile FROM utilisateur WHERE email = ?1", nativeQuery = true)
    public String getUserProfileUsingEmail(String email);


}
