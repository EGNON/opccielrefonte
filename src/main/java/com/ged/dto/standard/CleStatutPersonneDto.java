package com.ged.dto.standard;

import java.io.Serializable;

public class CleStatutPersonneDto implements Serializable {
    private Long idPersonne;
    private Long idQualite;

    public CleStatutPersonneDto() {
    }

    public CleStatutPersonneDto(Long idPersonne, Long idQualite) {
        this.idPersonne = idPersonne;
        this.idQualite = idQualite;
    }

    public Long getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(Long idPersonne) {
        this.idPersonne = idPersonne;
    }

    public Long getIdQualite() {
        return idQualite;
    }

    public void setIdQualite(Long idQualite) {
        this.idQualite = idQualite;
    }
}
