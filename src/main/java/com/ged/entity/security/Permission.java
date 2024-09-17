package com.ged.entity.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ged.entity.Base;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "T_Permission", schema = "Parametre")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idPermis")
public class Permission extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPermis;
    private String codePermis;
    private Boolean estPrincipale;
    private Boolean estParDefaut;
    private String libellePermis;
    private String description;
    @JsonIgnore
    @OneToMany(
            mappedBy = "permission",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            orphanRemoval = true
    )
    private List<RolePermission> roles = new ArrayList<>();
    @OneToMany(
            mappedBy = "permission",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            orphanRemoval = true
    )
    private Set<UtilisateurRolePermission> userRoles = new HashSet<>();

    public Permission() {
    }

    public Long getIdPermis() {
        return idPermis;
    }

    public void setIdPermis(Long idPermis) {
        this.idPermis = idPermis;
    }

    public String getCodePermis() {
        return codePermis;
    }

    public void setCodePermis(String codePermis) {
        this.codePermis = "ALLOW_" + codePermis;
    }

    public Boolean getEstPrincipale() {
        return estPrincipale;
    }

    public void setEstPrincipale(Boolean estPrincipale) {
        this.estPrincipale = estPrincipale;
    }

    public Boolean getEstParDefaut() {
        return estParDefaut;
    }

    public void setEstParDefaut(Boolean estParDefaut) {
        this.estParDefaut = estParDefaut;
    }

    public String getLibellePermis() {
        return libellePermis;
    }

    public void setLibellePermis(String libellePermis) {
        this.libellePermis = libellePermis;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<RolePermission> getRoles() {
        return roles;
    }

    public void setRoles(List<RolePermission> roles) {
        this.roles = roles;
    }

    public Set<UtilisateurRolePermission> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UtilisateurRolePermission> userRoles) {
        this.userRoles = userRoles;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "idPermis=" + idPermis +
                ", estPrincipale=" + estPrincipale +
                ", libellePermis='" + libellePermis + '\'' +
                '}';
    }
}
