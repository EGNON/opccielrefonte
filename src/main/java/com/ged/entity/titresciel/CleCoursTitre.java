package com.ged.entity.titresciel;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Embeddable
public class CleCoursTitre implements Serializable {
    private Long idTitre;
    private LocalDateTime dateCours;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CleCoursTitre that = (CleCoursTitre) o;
        return Objects.equals(idTitre, that.idTitre) && Objects.equals(dateCours, that.dateCours);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTitre, dateCours);
    }

    public Long getIdTitre() {
        return idTitre;
    }

    public void setIdTitre(Long idTitre) {
        this.idTitre = idTitre;
    }

    public LocalDateTime getDateCours() {
        return dateCours;
    }

    public void setDateCours(LocalDateTime dateCours) {
        this.dateCours = dateCours;
    }
}
