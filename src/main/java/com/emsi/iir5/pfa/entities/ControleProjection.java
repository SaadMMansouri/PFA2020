package com.emsi.iir5.pfa.entities;

import org.springframework.data.rest.core.config.Projection;
import java.util.Date;
import java.util.List;

@Projection(name = "controleProjection",types = Controle.class)
public interface ControleProjection {
    Integer getIdControle();
    String getCommentaire();
    String getStatus();
    Date getDateControle();
    List<Critere> getCriteres();
    Date getCreatedAt();
    Date getUpdatedAt();
}
