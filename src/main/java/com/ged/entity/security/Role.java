package com.ged.entity.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ged.entity.Base;
import com.ged.entity.standard.Module;
import jakarta.persistence.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;

import java.util.*;

@Entity
@Table(name = "T_Role", schema = "Parametre")
/*@NaturalIdCache
@org.hibernate.annotations.Cache(
        usage = CacheConcurrencyStrategy.READ_WRITE
)*/
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idRole")
public class Role extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRole;
    @Column(nullable = false, length = 45, unique = true)
//    @NaturalId
    private String nom;
    private String description;

    @JsonIgnore
    @OneToMany(
            mappedBy = "role",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            orphanRemoval = true
    )
    private Set<RolePermission> permissions = new HashSet<>();
    @OneToMany(
            mappedBy = "role",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            orphanRemoval = true
    )
    private Set<UtilisateurRolePermission> userPermissions = new HashSet<>();
    @OneToMany(
            mappedBy = "role",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            orphanRemoval = true
    )
    private List<UtilisateurRole> utilisateurs1 = new ArrayList<>();
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idModule", referencedColumnName = "idModule")
    private Module module;

    public Role() {}

    public Role(String nom) {
        this.nom = nom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(idRole, role.idRole) && Objects.equals(nom, role.nom) && Objects.equals(permissions, role.permissions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRole, nom, permissions);
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

    public Set<RolePermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<RolePermission> permissions) {
        this.permissions = permissions;
    }

    public List<UtilisateurRole> getUtilisateurs1() {
        return utilisateurs1;
    }

    public void setUtilisateurs1(List<UtilisateurRole> utilisateurs1) {
        this.utilisateurs1 = utilisateurs1;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public Set<UtilisateurRolePermission> getUserPermissions() {
        return userPermissions;
    }

    public void setUserPermissions(Set<UtilisateurRolePermission> userPermissions) {
        this.userPermissions = userPermissions;
    }

    @Override
    public String toString() {
        return "Role{" +
                "idRole=" + idRole +
                ", nom='" + nom + '\'' +
                ", permissions='" + permissions.size() + '\'' +
                '}';
    }
}
