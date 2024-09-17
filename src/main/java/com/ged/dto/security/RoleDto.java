package com.ged.dto.security;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.standard.BaseDto;

import java.util.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RoleDto extends BaseDto {
    private Long idRole;
    private String nom;
    private String description;
    private Set<RolePermissionDto> permissions = new HashSet<>();
    private Set<UtilisateurRolePermissionDto> userPermissions = new HashSet<>();
    private List<UtilisateurRoleDto> utilisateurs1 = new ArrayList<>();

    public RoleDto() {

    }

    public RoleDto(String nom) {
        this.nom = nom;
    }

    public Long getIdRole() {
        return idRole;
    }

    public void setIdRole(Long idRole) {
        this.idRole = idRole;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<RolePermissionDto> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<RolePermissionDto> permissions) {
        this.permissions = permissions;
    }

    public Set<UtilisateurRolePermissionDto> getUserPermissions() {
        return userPermissions;
    }

    public void setUserPermissions(Set<UtilisateurRolePermissionDto> userPermissions) {
        this.userPermissions = userPermissions;
    }

    public List<UtilisateurRoleDto> getUtilisateurs1() {
        return utilisateurs1;
    }

    public void setUtilisateurs1(List<UtilisateurRoleDto> utilisateurs1) {
        this.utilisateurs1 = utilisateurs1;
    }

    @Override
    public String toString() {
        return "Role{" +
                "idRole=" + idRole +
                ", nom='" + nom +
                '}';
    }
}
