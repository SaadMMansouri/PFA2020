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
    private String status;
    private String description;
    private String commentaireResponsable;
    @Temporal(TemporalType.DATE)
    private Date datePlanifierEntretien;
    @Temporal(TemporalType.DATE)
    private Date dateValiderEntretien;
    // date de la creation et de la derniere modification
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    // Relations
    @ManyToOne
    @JoinColumn(name="id_RespEntretien")
    private RespEntretien respEntretien;
    @ManyToOne
    @JoinColumn(name="id_vehicule")
    private Vehicule vehicule;

}
