package com.emsi.iir5.pfa.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@DiscriminatorValue("Controleur")
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Controleur extends Utilisateur implements Serializable{
    private static final long serialVersionUID = 1L;

    private String numTel2;
    private boolean disponible;

}
