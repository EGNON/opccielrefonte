package com.ged.dto.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ged.dto.standard.CleUtilisateurRoleDto;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UtilisateurRoleDto {
    private CleUtilisateurRoleDto id;
    @JsonIgnore
    private UtilisateurDto utilisateur;
    private RoleDto role;

    public UtilisateurRoleDto() {
    }

    public CleUtilisateurRoleDto getId() {
        return id;
    }

    public void setId(CleUtilisateurRoleDto id) {
        this.id = id;
    }

    public UtilisateurDto getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(UtilisateurDto utilisateur) {
        this.utilisateur = utilisateur;
    }

    public RoleDto getRole() {
        return role;
    }

    public void setRole(RoleDto role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UtilisateurRoleDto{" +
                ", role=" + role +
                '}';
    }
}
