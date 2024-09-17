package com.ged.dto.titresciel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CleIndiceBrvmDto implements Serializable {
    private LocalDateTime dateIndice;
    private String libelleIndice;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CleIndiceBrvmDto that)) return false;
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

    @Override
    public String toString() {
        return "CleIndiceBrvmDto{" +
                "dateIndice=" + dateIndice +
                ", libelleIndice='" + libelleIndice + '\'' +
                '}';
    }
}
