package com.emsi.iir5.pfa.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Trajetville implements Serializable {
    @EmbeddedId
    private TrajetvillePK id;

    @ManyToOne
    @MapsId("trajet_id")
    @JoinColumn(name="trajet_id")
    private Trajet trajet;

    @ManyToOne
    @MapsId("ville_id")
    @JoinColumn(name="ville_id")
    private Ville ville;

    private String typeVille;

}
