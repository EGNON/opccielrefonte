package com.ged.entity.security;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CleRolePermission implements Serializable {
    @Column(name = "idRole")
    private Long idRole;
    @Column(name = "idPermis")
    private Long idPermis;

    public CleRolePermission() {
    }

    public CleRolePermission(Long idRole, Long idPermis) {
        this.idRole = idRole;
        this.idPermis = idPermis;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CleRolePermission that = (CleRolePermission) o;
        return Objects.equals(idRole, that.idRole) && Objects.equals(idPermis, that.idPermis);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRole, idPermis);
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
