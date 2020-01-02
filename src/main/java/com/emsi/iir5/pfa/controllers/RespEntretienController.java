package com.emsi.iir5.pfa.controllers;

import com.emsi.iir5.pfa.dao.RespEntretienRepository;
import com.emsi.iir5.pfa.entities.RespEntretien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;

@RestController
@RequestMapping("/respEntretiens")
public class RespEntretienController {

    @Autowired
    private RespEntretienRepository respEntretienRepository;

    @DeleteMapping(path = "/{id}/delete")
    public ResponseEntity<?> deleteRespEntretien(@PathVariable(value = "id") Integer respEntretienId) throws ResourceNotFoundException {
        RespEntretien respEntretien = respEntretienRepository.findById(respEntretienId)
                .orElseThrow(() -> new ResourceNotFoundException("RespEntretien not found for this id :: " + respEntretienId));
        respEntretienRepository.delete(respEntretien);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/")
    public ResponseEntity<RespEntretien> createRespEntretien(@Valid @RequestBody RespEntretien respEntretien) throws URISyntaxException {
        respEntretien.setCreatedAt(new Date());
        RespEntretien result = respEntretienRepository.save(respEntretien);
        return ResponseEntity.created(new URI("/respEntretiens/"+result.getIdUtilisateur())).body(result);
    }

    @PutMapping(path = "/{id}/update")
    public ResponseEntity<RespEntretien> updateRespEntretien(@PathVariable(value = "idRespEntretien") Integer respEntretienId,
                                             @Valid @RequestBody RespEntretien respEntretienDetails) throws ResourceNotFoundException {
        RespEntretien respEntretien = respEntretienRepository.findById(respEntretienId)
                .orElseThrow(() -> new ResourceNotFoundException("RespEntretien not found for this id :: " + respEntretienId));

        respEntretien.setNom(respEntretienDetails.getNom());
        respEntretien.setPrenom(respEntretienDetails.getPrenom());
        respEntretien.setEmail(respEntretienDetails.getEmail());
        respEntretien.setNumTel(respEntretienDetails.getNumTel());
        respEntretien.setNumTel2(respEntretienDetails.getNumTel2());
        respEntretien.setAdresse(respEntretienDetails.getAdresse());
        respEntretien.setPassword(respEntretienDetails.getPassword());
        respEntretien.setEtatConnexion(respEntretienDetails.isEtatConnexion());
        respEntretien.setDisponible(respEntretienDetails.getDisponible());
        respEntretien.setSpecialite(respEntretienDetails.getSpecialite());
        respEntretien.setUpdatedAt(new Date());

        final RespEntretien updatedRespEntretien = respEntretienRepository.save(respEntretien);
        return ResponseEntity.ok(updatedRespEntretien);
    }
}
