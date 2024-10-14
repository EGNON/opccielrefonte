package com.ged.entity.titresciel;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CleCompte implements Serializable {
    @Column(name = "idRegistraire")
    private Long idRegistraire;
    @Column(length = 16)
    private String numeroCompte;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CleCompte cleCompte)) return false;
        return Objects.equals(getIdRegistraire(), cleCompte.getIdRegistraire()) && Objects.equals(getNumeroCompte(), cleCompte.getNumeroCompte());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdRegistraire(), getNumeroCompte());
    }

    public Long getIdRegistraire() {
        return idRegistraire;
    }

    public void setIdRegistraire(Long idRegistraire) {
        this.idRegistraire = idRegistraire;
    }

    public String getNumeroCompte() {
        return numeroCompte;
    }

    public void setNumeroCompte(String numeroCompte) {
        this.numeroCompte = numeroCompte;
    }
}
