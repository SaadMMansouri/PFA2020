package com.emsi.iir5.pfa.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Trajet implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTrajet;
    private String nomTrajet;
    private String type;

    @OneToMany(mappedBy="trajet", fetch=FetchType.EAGER)
    private List<Trajetville> Trajetvilles;

    @OneToMany(mappedBy = "trajet")
    Set<Voyage> voyages;
}
