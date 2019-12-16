package com.emsi.iir5.pfa.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Entretien implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEntretien;
    private String commentaire;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEntretien;

    @ManyToOne
    @JoinColumn(name="id_RespEntretien")
    private RespEntretien respEntretien;

    @ManyToOne
    @JoinColumn(name="id_vehicule")
    private Vehicule vehicule;

}
