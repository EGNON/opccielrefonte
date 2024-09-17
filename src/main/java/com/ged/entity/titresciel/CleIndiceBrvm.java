package com.ged.entity.titresciel;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Embeddable
public class CleIndiceBrvm implements Serializable {
    private LocalDateTime dateIndice;
    @Column(name = "libelle")
    private String libelleIndice;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CleIndiceBrvm that)) return false;
        return Objects.equals(dateIndice, that.dateIndice) && Objects.equals(libelleIndice, that.libelleIndice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateIndice, libelleIndice);
    }

    public LocalDateTime getDateIndice() {
        return dateIndice;
    }

    public void setDateIndice(LocalDateTime dateIndice) {
        this.dateIndice = dateIndice;
    }

    public String getLibelleIndice() {
        return libelleIndice;
    }

    public void setLibelleIndice(String libelleIndice) {
        this.libelleIndice = libelleIndice;
    }
}
