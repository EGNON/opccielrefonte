package com.ged.entity.security;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ged.entity.Base;
import com.ged.entity.standard.CleUtilisateurRolePermission;
import jakarta.persistence.*;

@Entity
@Table(name = "TJ_UtilisateurRolePermission", schema = "Parametre")
public class UtilisateurRolePermission extends Base {
    @EmbeddedId
    private CleUtilisateurRolePermission idUtilisateurRolePermission;
    @ManyToOne
    @JoinColumn(name = "idUtilisateur")
    @MapsId("idUtilisateur")
    private Utilisateur utilisateur;
    @ManyToOne
    @JoinColumn(name = "idRole")
    @MapsId("idRole")
    @JsonBackReference
    private Role role;
    @ManyToOne
    @JoinColumn(name = "idPermis")
    @MapsId("idPermis")
    private Permission permission;

    public UtilisateurRolePermission() {
    }

    public UtilisateurRolePermission(CleUtilisateurRolePermission id, Utilisateur utilisateur, Role role, Permission permission) {
        this.idUtilisateurRolePermission = id;
        this.utilisateur = utilisateur;
        this.role = role;
        this.permission = permission;
    }

    public UtilisateurRolePermission(Utilisateur utilisateur, Role role, Permission permission) {
        this.utilisateur = utilisateur;
        this.role = role;
        this.permission = permission;
        this.idUtilisateurRolePermission = new CleUtilisateurRolePermission(utilisateur.getIdPersonne(), role.getIdRole(), permission.getIdPermis());
    }

    public CleUtilisateurRolePermission getIdUtilisateurRolePermission() {
        return idUtilisateurRolePermission;
    }

    public void setIdUtilisateurRolePermission(CleUtilisateurRolePermission id) {
        this.idUtilisateurRolePermission = id;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    @Override
    public String toString() {
        return "UtilisateurRolePermission{" +
                "id=" + idUtilisateurRolePermission +
                ", utilisateur=" + utilisateur +
                ", role=" + role +
                ", permission=" + permission +
                '}';
    }
}
