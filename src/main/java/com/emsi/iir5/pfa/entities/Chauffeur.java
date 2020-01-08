package com.emsi.iir5.pfa.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@DiscriminatorValue("Chauffeur")
@Data @NoArgsConstructor @ToString
public class Chauffeur extends Utilisateur implements Serializable{
    private static final long serialVersionUID = 1L;
    private String numTel2;
    private String disponible;

    @OneToMany(mappedBy = "chauffeur",fetch = FetchType.EAGER)
    private Set<Location> locations;
    @OneToMany(mappedBy = "chauffeur",fetch = FetchType.LAZY)
    private List<Voyage> voyages;
}
