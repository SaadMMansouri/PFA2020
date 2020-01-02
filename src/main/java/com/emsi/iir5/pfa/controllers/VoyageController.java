package com.emsi.iir5.pfa.controllers;
import com.emsi.iir5.pfa.dao.*;
import com.emsi.iir5.pfa.entities.Voyage;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/voyages")
public class VoyageController {

    @Autowired
    private VoyageRepository voyageRepository;
    @Autowired
    private TrajetRepository trajetRepository;
    @Autowired
    private VehiculeRepository vehiculeRepository;
    @Autowired
    private ChauffeurRepository chauffeurRepository;

    @DeleteMapping(path = "/{id}/delete")
    public ResponseEntity<?> deleteVoyage(@PathVariable(value = "id") Integer voyageId) throws ResourceNotFoundException {
        Voyage voyage = voyageRepository.findById(voyageId)
                .orElseThrow(() -> new ResourceNotFoundException("Voyage not found for this id :: " + voyageId));
        voyageRepository.delete(voyage);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/new")
    public ResponseEntity<Voyage> createVoyage(@RequestBody String content) throws URISyntaxException, JsonProcessingException, ParseException {
        System.out.println(content);
        //convert from JSON to Map
        Map<String, Object> data = new ObjectMapper().readValue(content, new TypeReference<Map<String, Object>>(){});
        // create new Target
        Voyage voyage = new Voyage();
        voyage.setChauffeur(chauffeurRepository.findById(Integer.parseInt(data.get("idUtilisateur").toString())).get());
        voyage.setTrajet(trajetRepository.findById(Integer.parseInt(data.get("idTrajet").toString())).get());
        voyage.setVehicule(vehiculeRepository.findById(Integer.parseInt(data.get("idVehicule").toString())).get());

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        try {
            Date reformatedDate = format.parse(data.get("dateVoyage").toString().replaceAll("Z$", "+0000"));
            System.out.println(reformatedDate);
            voyage.setDateVoyage(reformatedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        voyage.setCreatedAt(new Date());


        Voyage result = voyageRepository.save(voyage);
        return ResponseEntity.created(new URI("/voyages/"+result.getIdVoyage())).body(result);
    }

    @PutMapping(path = "/{idVoyage}/update")
    public ResponseEntity<Voyage> updateVoyage(@PathVariable(value = "idVoyage") Integer voyageId, @RequestBody String content) throws URISyntaxException, JsonProcessingException, ParseException {
        System.out.println(content);
        //convert from JSON to Map
        Map<String, Object> data = new ObjectMapper().readValue(content, new TypeReference<Map<String, Object>>() {
        });
        // get object
        Voyage voyage = voyageRepository.findById(voyageId)
            .orElseThrow(() -> new ResourceNotFoundException("Voyage not found for this id :: " + voyageId));

        voyage.setChauffeur(chauffeurRepository.findById(Integer.parseInt(data.get("idUtilisateur").toString())).get());
        voyage.setTrajet(trajetRepository.findById(Integer.parseInt(data.get("idTrajet").toString())).get());
        voyage.setVehicule(vehiculeRepository.findById(Integer.parseInt(data.get("idVehicule").toString())).get());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        try {
            Date reformatedDate = format.parse(data.get("dateVoyage").toString().replaceAll("Z$", "+0000"));
            System.out.println(reformatedDate);
            voyage.setDateVoyage(reformatedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        voyage.setUpdatedAt(new Date());
        final Voyage updatedTrajet = voyageRepository.save(voyage);
        return ResponseEntity.ok(updatedTrajet);
    }
}
