package com.emsi.iir5.pfa.controllers;

import com.emsi.iir5.pfa.dao.TrajetRepository;
import com.emsi.iir5.pfa.dao.TrajetVilleRepository;
import com.emsi.iir5.pfa.dao.VilleRepository;
import com.emsi.iir5.pfa.entities.Trajet;

import com.emsi.iir5.pfa.entities.Trajetville;
import com.emsi.iir5.pfa.entities.TrajetvillePK;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/trajets")
public class TrajetController {

    @Autowired
    private TrajetRepository trajetRepository;
    @Autowired
    private VilleRepository villeRepository;
    @Autowired
    private TrajetVilleRepository trajetVilleRepository;

    @DeleteMapping(path = "/{id}/delete")
    public ResponseEntity<?> deleteTrajet(@PathVariable(value = "id") Integer trajetId) throws ResourceNotFoundException {
        Trajet trajet = trajetRepository.findById(trajetId)
                .orElseThrow(() -> new ResourceNotFoundException("Trajet not found for this id :: " + trajetId));
        for (Trajetville t: trajet.getTrajetvilles()) {
            trajetVilleRepository.delete(t);
        }
        trajetRepository.delete(trajet);

        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/newWithVille")
    public ResponseEntity<Trajet> createTrajetWithVille(@RequestBody String content) {
        try {
            //convert from JSON to Map
            Map<String, Object> data = new ObjectMapper().readValue(content, new TypeReference<Map<String, Object>>(){});
            // create new Target
            Trajet trajet = new Trajet();
            trajet.setNomTrajet(data.get("nomTrajet").toString());
            trajet.setType(data.get("type").toString());
            trajet.setCreatedAt(new Date());
            // add villes to the ceated target
            Trajet result = trajetRepository.save(trajet);
            createTrajetVille(result, data.get("villeDepart").toString(), data.get("villeArrive").toString(), (ArrayList<Integer>) data.get("escales"));

            return ResponseEntity.created(new URI("/trajets/"+result.getIdTrajet())).body(result);

        } catch (JsonProcessingException | URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PutMapping(path = "/{idTrajet}/update")
    public ResponseEntity<Trajet> updateTrajet(@PathVariable(value = "idTrajet") Integer trajetId,
                                               @RequestBody String content) {
        try {
            //convert from JSON to Map
            Map<String, Object> data = new ObjectMapper().readValue(content, new TypeReference<Map<String, Object>>(){});
            // create new Target
            Trajet trajet = trajetRepository.findById(trajetId)
                    .orElseThrow(() -> new ResourceNotFoundException("Trajet not found for this id :: " + trajetId));
            // Ajouter les modification
            trajet.setNomTrajet(data.get("nomTrajet").toString());
            trajet.setType(data.get("type").toString());
            // DELETE old escales & villes
            for (Trajetville t: trajet.getTrajetvilles()) {
                trajetVilleRepository.delete(t);
            }
            // add villes les nouvelles villes
            createTrajetVille(trajet, data.get("villeDepart").toString(), data.get("villeArrive").toString(), (ArrayList<Integer>) data.get("escales"));

            trajet.setUpdatedAt(new Date());
            final Trajet updatedTrajet = trajetRepository.save(trajet);
            return ResponseEntity.ok(updatedTrajet);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void createTrajetVille(Trajet trajet, String villeDep, String villeArr, ArrayList<Integer> escales){
        Trajetville tv;
        TrajetvillePK pk;
        for (Integer idv : escales) {
            tv = new Trajetville();
            pk = new TrajetvillePK();
            pk.setIdTrajet(trajet.getIdTrajet());
            pk.setIdVille(villeRepository.findById(idv).get().getIdVille());
            tv.setId(pk);
            tv.setTypeVille("Escale");
            trajetVilleRepository.save(tv);
        }

        for (int i = 0; i < 2; i++) {
            tv = new Trajetville();
            pk = new TrajetvillePK();
            pk.setIdTrajet(trajet.getIdTrajet());
            switch (i){
                case 0:
                    pk.setIdVille(villeRepository.findById(Integer.parseInt(villeDep)).get().getIdVille());
                    tv.setTypeVille("Depart"); break;
                case 1:
                    pk.setIdVille(villeRepository.findById(Integer.parseInt(villeArr)).get().getIdVille());
                    tv.setTypeVille("Arrive"); break;
            }
            tv.setId(pk);
            trajetVilleRepository.save(tv);
        }
    }

}

