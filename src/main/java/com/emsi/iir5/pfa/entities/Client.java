package com.emsi.iir5.pfa.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@DiscriminatorValue("Client")
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Client extends Utilisateur implements Serializable {
    private String numTelFix;
    private int codePostal;
    private String description;

    @OneToMany(mappedBy = "client")
    Set<Location> locations;
}
