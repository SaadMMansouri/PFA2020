package com.emsi.iir5.pfa.dao;

import com.emsi.iir5.pfa.entities.Controleur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("*")
@RepositoryRestResource
public interface ControleurRepository extends JpaRepository<Controleur, Integer> {}
