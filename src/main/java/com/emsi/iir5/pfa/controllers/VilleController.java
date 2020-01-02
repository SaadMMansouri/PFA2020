package com.emsi.iir5.pfa.controllers;

import com.emsi.iir5.pfa.dao.VilleRepository;
import com.emsi.iir5.pfa.entities.Ville;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/villes")
public class VilleController {

    @Autowired
    private VilleRepository villeRepository;

    @DeleteMapping(path = "/{id}/delete")
    public ResponseEntity<?> deleteVille(@PathVariable(value = "id") Integer villeId) throws ResourceNotFoundException {
        Ville ville = villeRepository.findById(villeId)
                .orElseThrow(() -> new ResourceNotFoundException("Ville not found for this id :: " + villeId));
        villeRepository.delete(ville);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/")
    public ResponseEntity<Ville> createVille(@Valid @RequestBody Ville ville) throws URISyntaxException {
        Ville result = villeRepository.save(ville);
        return ResponseEntity.created(new URI("/villes/"+result.getIdVille())).body(result);
    }

    @PutMapping(path = "/{id}/update")
    public ResponseEntity<Ville> updateVille(@PathVariable(value = "idVille") Integer villeId,
                                                   @Valid @RequestBody Ville villeDetails) throws ResourceNotFoundException {
        Ville ville = villeRepository.findById(villeId)
                .orElseThrow(() -> new ResourceNotFoundException("Ville not found for this id :: " + villeId));

        ville.setLibelle(villeDetails.getLibelle());
        ville.setAltitude(villeDetails.getAltitude());
        ville.setLongitude(villeDetails.getLongitude());
        final Ville updatedVille = villeRepository.save(ville);
        return ResponseEntity.ok(updatedVille);
    }
}
