package com.emsi.iir5.pfa.controllers;

import com.emsi.iir5.pfa.dao.RespEntretienRepository;
import com.emsi.iir5.pfa.dao.TrajetRepository;
import com.emsi.iir5.pfa.dao.VehiculeRepository;
import com.emsi.iir5.pfa.dao.EntretienRepository;
import com.emsi.iir5.pfa.entities.Entretien;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/entretiens")
public class EntretienController {

    @Autowired
    private EntretienRepository entretienRepository;

    @Autowired
    private VehiculeRepository vehiculeRepository;
    @Autowired
    private RespEntretienRepository respEntretienRepository;

    @DeleteMapping(path = "/{id}/delete")
    public ResponseEntity<?> deleteEntretien(@PathVariable(value = "id") Integer entretienId) throws ResourceNotFoundException {
        Entretien entretien = entretienRepository.findById(entretienId)
                .orElseThrow(() -> new ResourceNotFoundException("Entretien not found for this id :: " + entretienId));
        entretienRepository.delete(entretien);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/new")
    public ResponseEntity<Entretien> createEntretien(@RequestBody String content) throws URISyntaxException, JsonProcessingException, ParseException {
        System.out.println(content);
        //convert from JSON to Map
        Map<String, Object> data = new ObjectMapper().readValue(content, new TypeReference<Map<String, Object>>(){});
        // create new Target
        Entretien entretien = new Entretien();
        entretien.setRespEntretien(respEntretienRepository.findById(Integer.parseInt(data.get("idUtilisateur").toString())).get());
        entretien.setVehicule(vehiculeRepository.findById(Integer.parseInt(data.get("idVehicule").toString())).get());

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        try {
            Date reformatedDate = format.parse(data.get("datePlanifierEntretien").toString().replaceAll("Z$", "+0000"));
            System.out.println(reformatedDate);
            entretien.setDatePlanifierEntretien(reformatedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        entretien.setCreatedAt(new Date());


        Entretien result = entretienRepository.save(entretien);
        return ResponseEntity.created(new URI("/entretiens/"+result.getIdEntretien())).body(result);
    }

    @PutMapping(path = "/{idEntretien}/updateByAdmin")
    public ResponseEntity<Entretien> updateEntretien(@PathVariable(value = "idEntretien") Integer entretienId, @RequestBody String content) throws URISyntaxException, JsonProcessingException, ParseException {
        System.out.println(content);
        //convert from JSON to Map
        Map<String, Object> data = new ObjectMapper().readValue(content, new TypeReference<Map<String, Object>>() {
        });
        // get object
        Entretien entretien = entretienRepository.findById(entretienId)
            .orElseThrow(() -> new ResourceNotFoundException("Entretien not found for this id :: " + entretienId));

        entretien.setRespEntretien(respEntretienRepository.findById(Integer.parseInt(data.get("idUtilisateur").toString())).get());
        entretien.setVehicule(vehiculeRepository.findById(Integer.parseInt(data.get("idVehicule").toString())).get());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        try {
            Date reformatedDate = format.parse(data.get("datePlanifierEntretien").toString().replaceAll("Z$", "+0000"));
            System.out.println(reformatedDate);
            entretien.setDatePlanifierEntretien(reformatedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        entretien.setUpdatedAt(new Date());
        final Entretien updatedTrajet = entretienRepository.save(entretien);
        return ResponseEntity.ok(updatedTrajet);
    }
}
