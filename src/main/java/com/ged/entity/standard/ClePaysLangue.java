package com.ged.entity.standard;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ClePaysLangue implements Serializable {
    private Long idLangue;
    private Long idPays;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClePaysLangue that)) return false;
        return Objects.equals(idLangue, that.idLangue) && Objects.equals(idPays, that.idPays);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idLangue, idPays);
    }

    public Long getIdLangue() {
        return idLangue;
    }

    public void setIdLangue(Long idLangue) {
        this.idLangue = idLangue;
    }

    public Long getIdPays() {
        return idPays;
    }

    public void setIdPays(Long idPays) {
        this.idPays = idPays;
    }
}
