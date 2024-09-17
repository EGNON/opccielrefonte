package com.ged.dto.titresciel;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class CleTableauAmortissementDto implements Serializable {
    private Long idTitre;
    private LocalDateTime dateEcheance;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CleTableauAmortissementDto that = (CleTableauAmortissementDto) o;
        return Objects.equals(idTitre, that.idTitre) && Objects.equals(dateEcheance, that.dateEcheance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTitre, dateEcheance);
    }

    public Long getIdTitre() {
        return idTitre;
    }

    public void setIdTitre(Long idTitre) {
        this.idTitre = idTitre;
    }

    public LocalDateTime getDateEcheance() {
        return dateEcheance;
    }

    public void setDateEcheance(LocalDateTime dateEcheance) {
        this.dateEcheance = dateEcheance;
    }

    @Override
    public String toString() {
        return "CleTableauAmortissementDto{" +
                "idTitre=" + idTitre +
                ", dateEcheance=" + dateEcheance +
                '}';
    }
}
