package com.emsi.iir5.pfa.controllers;

import com.emsi.iir5.pfa.dao.ControleRepository;
import com.emsi.iir5.pfa.dao.ControleurRepository;
import com.emsi.iir5.pfa.dao.CritereRepository;
import com.emsi.iir5.pfa.entities.Controle;
import com.emsi.iir5.pfa.entities.Critere;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

@RestController
@RequestMapping("/controles")
public class ControleController {

    @Autowired
    private ControleRepository controleRepository;
    @Autowired
    private CritereRepository critereRepository;

    @Autowired
    private ControleurRepository controleurRepository;

    @DeleteMapping(path = "/{id}/delete")
    public ResponseEntity<?> deleteControle(@PathVariable(value = "id") Integer controleId) throws ResourceNotFoundException {
        Controle controle = controleRepository.findById(controleId)
                .orElseThrow(() -> new ResourceNotFoundException("Controle not found for this id :: " + controleId));
        controleRepository.delete(controle);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/")
    public ResponseEntity<Controle> createControle(@Valid @RequestBody Controle controle) throws URISyntaxException {
        controle.setCreatedAt(new Date());
        Controle result = controleRepository.save(controle);
        return ResponseEntity.created(new URI("/controles/"+result.getIdControle())).body(result);
    }


    @PostMapping(path = "/new")
    public ResponseEntity<Controle> create(@RequestBody String content) {
        System.out.println(content);
        try {
            //convert from JSON to Map
            Map<String, Object> data = new ObjectMapper().readValue(content, new TypeReference<Map<String, Object>>(){});
            // create new Target
            Controle controle = new Controle();
            controle.setCommentaire(data.get("commentaire").toString());
            controle.setStatus(data.get("status").toString());
            Set<Critere> selectedCriteres = new HashSet<>();
            for (Integer idc : (ArrayList<Integer>) data.get("criteres")) {
                selectedCriteres.add(critereRepository.findById(idc).get());
            }
            controle.setDateControle(new Date());
            controle.setCreatedAt(new Date());
            controle.setControleur(controleurRepository.findById(1).get());
            controle.setCriteres(selectedCriteres);
            Controle result = controleRepository.save(controle);

            return ResponseEntity.created(new URI("/controles/"+result.getIdControle())).body(result);

        } catch (JsonProcessingException | URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PutMapping(path = "/{idControle}/update")
    public ResponseEntity<Controle> updateTrajet(@PathVariable(value = "idControle") Integer controleId,
                                               @RequestBody String content) {
        try {
            //convert from JSON to Map
            Map<String, Object> data = new ObjectMapper().readValue(content, new TypeReference<Map<String, Object>>(){});
            Controle controle = controleRepository.findById(controleId)
                    .orElseThrow(() -> new ResourceNotFoundException("Controle not found for this id :: " + controleId));
            // Ajouter les modification
            controle.setCommentaire(data.get("commentaire").toString());
            controle.setStatus(data.get("status").toString());
            controle.getCriteres().clear();
            // DELETE old escales & villes
            Set<Critere> selectedCriteres = new HashSet<>();
            for (Integer idc : (ArrayList<Integer>) data.get("criteres")) {
                selectedCriteres.add(critereRepository.findById(idc).get());
            }
            controle.setCriteres(selectedCriteres);
            controle.setUpdatedAt(new Date());
            final Controle updatedControle = controleRepository.save(controle);
            return ResponseEntity.ok(updatedControle);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
