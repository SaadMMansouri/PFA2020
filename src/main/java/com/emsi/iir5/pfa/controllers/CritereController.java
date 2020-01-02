package com.emsi.iir5.pfa.controllers;

import com.emsi.iir5.pfa.dao.CritereRepository;
import com.emsi.iir5.pfa.entities.Critere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;

@RestController
@RequestMapping("/criteres")
public class CritereController {

    @Autowired
    private CritereRepository critereRepository;

    @DeleteMapping(path = "/{id}/delete")
    public ResponseEntity<?> deleteCritere(@PathVariable(value = "id") Integer critereId) throws ResourceNotFoundException {
        Critere critere = critereRepository.findById(critereId)
                .orElseThrow(() -> new ResourceNotFoundException("Critere not found for this id :: " + critereId));
        critereRepository.delete(critere);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/")
    public ResponseEntity<Critere> createCritere(@Valid @RequestBody Critere critere) throws URISyntaxException {
        critere.setCreatedAt(new Date());
        Critere result = critereRepository.save(critere);
        return ResponseEntity.created(new URI("/criteres/"+result.getIdCritere())).body(result);
    }

    @PutMapping(path = "/{id}/update")
    public ResponseEntity<Critere> updateCritere(@PathVariable(value = "idCritere") Integer critereId,
                                             @Valid @RequestBody Critere critereDetails) throws ResourceNotFoundException {
        Critere critere = critereRepository.findById(critereId)
                .orElseThrow(() -> new ResourceNotFoundException("Critere not found for this id :: " + critereId));
        critere.setUpdatedAt(new Date());
        critere.setTitre(critereDetails.getTitre());
        critere.setDescription(critereDetails.getDescription());
        final Critere updatedCritere = critereRepository.save(critere);
        return ResponseEntity.ok(updatedCritere);
    }
}
