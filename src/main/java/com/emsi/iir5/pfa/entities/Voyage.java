package com.emsi.iir5.pfa.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Voyage implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVoyage;
    @Temporal(TemporalType.DATE)
    private Date dateVoyage;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_trajet")
    private Trajet trajet;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_chauffeur")
    private Chauffeur chauffeur;

    @ManyToOne(optional = false)
    @JoinColumn(name="id_vehicule")
    private Vehicule vehicule;

}
