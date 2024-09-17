package com.ged.entity.standard;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CleUtilisateurRole implements Serializable {

    @Column(name = "idUtilisateur")
    private Long idUtilisateur;

    @Column(name = "idRole")
    private Long idRole;

    public CleUtilisateurRole() {}

    public CleUtilisateurRole(
            Long idUtilisateur,
            Long idRole) {
        this.idUtilisateur = idUtilisateur;
        this.idRole = idRole;
    }

    //Getters omitted for brevity

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        CleUtilisateurRole that = (CleUtilisateurRole) o;
        return Objects.equals(idUtilisateur, that.idUtilisateur) &&
                Objects.equals(idRole, that.idRole);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUtilisateur, idRole);
    }

    public Long getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Long idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public Long getIdRole() {
        return idRole;
    }

    public void setIdRole(Long idRole) {
        this.idRole = idRole;
    }

    @Override
    public String toString() {
        return "CleUtilisateurRole{" +
                "idUtilisateur=" + idUtilisateur +
                ", idRole=" + idRole +
                '}';
    }
}
