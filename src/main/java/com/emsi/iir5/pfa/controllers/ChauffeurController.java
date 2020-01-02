package com.emsi.iir5.pfa.controllers;

import com.emsi.iir5.pfa.dao.ChauffeurRepository;
import com.emsi.iir5.pfa.entities.Chauffeur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;

@RestController
@RequestMapping("/chauffeurs")
public class ChauffeurController extends UtilisateurController {

    @Autowired
    private ChauffeurRepository chauffeurRepository;

    @DeleteMapping(path = "/{id}/delete")
    public ResponseEntity<?> deleteChauffeur(@PathVariable(value = "id") Integer chauffeurId) throws ResourceNotFoundException {
        Chauffeur chauffeur = chauffeurRepository.findById(chauffeurId)
                .orElseThrow(() -> new ResourceNotFoundException("Chauffeur not found for this id :: " + chauffeurId));
        chauffeurRepository.delete(chauffeur);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/")
    public ResponseEntity<Chauffeur> createChauffeur(@Valid @RequestBody Chauffeur chauffeur) throws URISyntaxException {
        chauffeur.setCreatedAt(new Date());
        Chauffeur result = chauffeurRepository.save(chauffeur);
        return ResponseEntity.created(new URI("/chauffeurs/"+result.getIdUtilisateur())).body(result);
    }

    @PutMapping(path = "/{id}/update")
    public ResponseEntity<Chauffeur> updateChauffeur(@PathVariable(value = "idChauffeur") Integer chauffeurId,
                                             @Valid @RequestBody Chauffeur chauffeurDetails) throws ResourceNotFoundException {
        Chauffeur chauffeur = chauffeurRepository.findById(chauffeurId)
                .orElseThrow(() -> new ResourceNotFoundException("Chauffeur not found for this id :: " + chauffeurId));

        chauffeur.setNom(chauffeurDetails.getNom());
        chauffeur.setPrenom(chauffeurDetails.getPrenom());
        chauffeur.setEmail(chauffeurDetails.getEmail());
        chauffeur.setNumTel(chauffeurDetails.getNumTel());
        chauffeur.setNumTel2(chauffeurDetails.getNumTel2());
        chauffeur.setAdresse(chauffeurDetails.getAdresse());
        
        chauffeur.setPassword(getEncreptedPassword(chauffeurDetails.getPassword()));

        chauffeur.setEtatConnexion(chauffeurDetails.isEtatConnexion());
        chauffeur.setDisponible(chauffeurDetails.getDisponible());
        chauffeur.setUpdatedAt(new Date());

        final Chauffeur updatedChauffeur = chauffeurRepository.save(chauffeur);
        return ResponseEntity.ok(updatedChauffeur);
    }
}
