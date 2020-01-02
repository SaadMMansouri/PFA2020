package com.emsi.iir5.pfa.controllers;

import com.emsi.iir5.pfa.dao.VehiculeRepository;
import com.emsi.iir5.pfa.entities.Vehicule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/vehicules")
public class VehiculeController {

    @Autowired
    private VehiculeRepository vehiculeRepository;

    @DeleteMapping(path = "/{id}/delete")
    public ResponseEntity<?> deleteVehicule(@PathVariable(value = "id") Integer vehiculeId) throws ResourceNotFoundException {
        Vehicule vehicule = vehiculeRepository.findById(vehiculeId)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicule not found for this id :: " + vehiculeId));
        vehiculeRepository.delete(vehicule);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/")
    public ResponseEntity<Vehicule> createVehicule(@Valid @RequestBody Vehicule vehicule) throws URISyntaxException {
        Vehicule result = vehiculeRepository.save(vehicule);
        return ResponseEntity.created(new URI("/vehicules/"+result.getIdVehicule())).body(result);
    }

    @PutMapping(path = "/{id}/update")
    public ResponseEntity<Vehicule> updateVehicule(@PathVariable(value = "idVehicule") Integer vehiculeId,
                                                   @Valid @RequestBody Vehicule vehiculeDetails) throws ResourceNotFoundException {
        Vehicule vehicule = vehiculeRepository.findById(vehiculeId)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicule not found for this id :: " + vehiculeId));

        vehicule.setMarque(vehiculeDetails.getMarque());
        vehicule.setMatricule(vehiculeDetails.getMatricule());
        vehicule.setEtat(vehiculeDetails.getEtat());
        vehicule.setNbrPlaces(vehiculeDetails.getNbrPlaces());
        vehicule.setType(vehiculeDetails.getType());
        final Vehicule updatedVehicule = vehiculeRepository.save(vehicule);
        return ResponseEntity.ok(updatedVehicule);
    }
}
