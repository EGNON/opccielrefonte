package com.ged.dto.titresciel;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

public class CleStatutTitreDto implements Serializable {
    private Long idTitre;
    private Long idQualite;

    public CleStatutTitreDto() {
    }

    public Long getIdTitre() {
        return idTitre;
    }

    public void setIdTitre(Long idTitre) {
        this.idTitre = idTitre;
    }

    public Long getIdQualite() {
        return idQualite;
    }

    public void setIdQualite(Long idQualite) {
        this.idQualite = idQualite;
    }
}
