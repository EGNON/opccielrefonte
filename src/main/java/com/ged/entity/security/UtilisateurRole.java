package com.ged.entity.security;

import com.ged.entity.Base;
import com.ged.entity.standard.CleUtilisateurRole;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "TJ_UtilisateurRole", schema = "Parametre")
public class UtilisateurRole extends Base {
    @EmbeddedId
    private CleUtilisateurRole id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUtilisateur")
    @MapsId("idUtilisateur")
    private Utilisateur utilisateur;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idRole")
    @MapsId("idRole")
    private Role role;

    public UtilisateurRole() {}

    public UtilisateurRole(Utilisateur utilisateur, Role role) {
        this.utilisateur = utilisateur;
        this.role = role;
        this.id = new CleUtilisateurRole(utilisateur.getIdPersonne(), role.getIdRole());
    }

    //Getters and setters omitted for brevity
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        UtilisateurRole that = (UtilisateurRole) o;
        return Objects.equals(utilisateur, that.utilisateur) &&
                Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(utilisateur, role);
    }

    public CleUtilisateurRole getId() {
        return id;
    }

    public void setId(CleUtilisateurRole id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "UtilisateurRole{" +
                "id=" + id +
                ", utilisateur=" + utilisateur +
                ", role=" + role +
                '}';
    }
}
