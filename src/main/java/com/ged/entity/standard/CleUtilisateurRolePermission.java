package com.ged.entity.standard;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CleUtilisateurRolePermission implements Serializable {
    private Long idUtilisateur;
    private Long idRole;
    private Long idPermis;

    public CleUtilisateurRolePermission() {
    }

    public CleUtilisateurRolePermission(Long idUtilisateur, Long idRole, Long idPermis) {
        this.idUtilisateur = idUtilisateur;
        this.idRole = idRole;
        this.idPermis = idPermis;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CleUtilisateurRolePermission that)) return false;
        return Objects.equals(idUtilisateur, that.idUtilisateur) && Objects.equals(idRole, that.idRole) && Objects.equals(idPermis, that.idPermis);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUtilisateur, idRole, idPermis);
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

    public Long getIdPermis() {
        return idPermis;
    }

    public void setIdPermis(Long idPermis) {
        this.idPermis = idPermis;
    }
}
