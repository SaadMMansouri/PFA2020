package com.emsi.iir5.pfa.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Vehicule implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVehicule;
    private String matricule;
    private String marque;
    private String etat;
    private String type;
    private int nbrPlaces;
}
