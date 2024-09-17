package com.ged.dto.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.standard.BaseDto;
import com.ged.dto.standard.CleUtilisateurRolePermissionDto;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UtilisateurRolePermissionDto extends BaseDto {
    private CleUtilisateurRolePermissionDto idUtilisateurRolePermission;
    @JsonIgnore
    private UtilisateurDto utilisateur;
    private RoleDto role;
    private PermissionDto permission;

    public CleUtilisateurRolePermissionDto getIdUtilisateurRolePermission() {
        return idUtilisateurRolePermission;
    }

    public void setIdUtilisateurRolePermission(CleUtilisateurRolePermissionDto id) {
        this.idUtilisateurRolePermission = id;
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

    public PermissionDto getPermission() {
        return permission;
    }

    public void setPermission(PermissionDto permission) {
        this.permission = permission;
    }

    @Override
    public String toString() {
        return "UtilisateurRolePermissionDto{" +
                ", utilisateur=" + utilisateur +
                ", role=" + role +
                ", permission=" + permission +
                '}';
    }
}
