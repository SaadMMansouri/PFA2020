package com.emsi.iir5.pfa.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@DiscriminatorValue("RespEntretien")
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class RespEntretien extends Utilisateur implements Serializable {
    private static final long serialVersionUID = 1L;

    private String numTel2;
    private String specialite;
    private String disponible;

    @OneToMany(mappedBy="respEntretien", fetch=FetchType.LAZY)
    private Set<Entretien> entretiens;
}
