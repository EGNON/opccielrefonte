package com.ged.entity.standard;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CleEnvoiMail implements Serializable {
    private Long idPersonne;
    private Long idMail;

    public Long getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(Long idPersonne) {
        this.idPersonne = idPersonne;
    }

    public Long getIdMail() {
        return idMail;
    }

    public void setIdMail(Long idMail) {
        this.idMail = idMail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CleEnvoiMail that = (CleEnvoiMail) o;
        return Objects.equals(idPersonne, that.idPersonne) && Objects.equals(idMail, that.idMail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPersonne, idMail);
    }
}
