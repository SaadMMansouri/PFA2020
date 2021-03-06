package com.emsi.iir5.pfa.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Controle implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idControle;
    private String commentaire;
    private String status;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateControle;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @ManyToOne(optional = false)
    @JoinColumn(name="id_controleur")
    private Controleur controleur;

    @ManyToMany
    @JoinTable(joinColumns={@JoinColumn(name="id_Contole")},inverseJoinColumns={@JoinColumn(name="id_Critere")})
    private Set<Critere> criteres;

    @OneToOne
    @JoinColumn(name="id_voyage")
    private Voyage voyage;

}
