package com.emsi.iir5.pfa.controllers;

import com.emsi.iir5.pfa.dao.ControleurRepository;
import com.emsi.iir5.pfa.entities.Controleur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;

@RestController
@RequestMapping("/controleurs")
public class ControleurController {

    @Autowired
    private ControleurRepository controleurRepository;

    @DeleteMapping(path = "/{id}/delete")
    public ResponseEntity<?> deleteControleur(@PathVariable(value = "id") Integer controleurId) throws ResourceNotFoundException {
        Controleur controleur = controleurRepository.findById(controleurId)
                .orElseThrow(() -> new ResourceNotFoundException("Controleur not found for this id :: " + controleurId));
        controleurRepository.delete(controleur);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/")
    public ResponseEntity<Controleur> createControleur(@Valid @RequestBody Controleur controleur) throws URISyntaxException {
        controleur.setCreatedAt(new Date());
        Controleur result = controleurRepository.save(controleur);
        return ResponseEntity.created(new URI("/controleurs/"+result.getIdUtilisateur())).body(result);
    }

    @PutMapping(path = "/{id}/update")
    public ResponseEntity<Controleur> updateControleur(@PathVariable(value = "idControleur") Integer controleurId,
                                             @Valid @RequestBody Controleur controleurDetails) throws ResourceNotFoundException {
        Controleur controleur = controleurRepository.findById(controleurId)
                .orElseThrow(() -> new ResourceNotFoundException("Controleur not found for this id :: " + controleurId));

        controleur.setNom(controleurDetails.getNom());
        controleur.setPrenom(controleurDetails.getPrenom());
        controleur.setEmail(controleurDetails.getEmail());
        controleur.setNumTel(controleurDetails.getNumTel());
        controleur.setNumTel2(controleurDetails.getNumTel2());
        controleur.setAdresse(controleurDetails.getAdresse());
        controleur.setPassword(controleurDetails.getPassword());
        controleur.setEtatConnexion(controleurDetails.isEtatConnexion());
        controleur.setDisponible(controleurDetails.getDisponible());
        controleur.setUpdatedAt(new Date());

        final Controleur updatedControleur = controleurRepository.save(controleur);
        return ResponseEntity.ok(updatedControleur);
    }
}
