package com.emsi.iir5.pfa.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class TrajetvillePK implements Serializable {

    @Column(name = "ville_id",insertable=false, updatable=false)
    private Integer idVille;

    @Column(name = "trajet_id",insertable=false, updatable=false)
    private Integer idTrajet;


    public TrajetvillePK() {}

    public int getIdVille() {
        return this.idVille;
    }
    public void setIdVille(Integer idVille) {
        this.idVille = idVille;
    }

    public Integer getIdTrajet() {
        return this.idTrajet;
    }
    public void setIdTrajet(Integer idTrajet) {
        this.idTrajet = idTrajet;
    }
}
