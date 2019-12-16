package com.emsi.iir5.pfa.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data @NoArgsConstructor @ToString
public class Location implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLocation;
    private int duree;
    private String description;
    @Temporal(TemporalType.DATE)
    private Date dateLocation;
    @ManyToOne
    @JoinColumn(name="id_vehicule")
    private Vehicule vehicule;
    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;
    @ManyToOne
    @JoinColumn(name = "id_chauffeur")
    private Chauffeur chauffeur;


}
