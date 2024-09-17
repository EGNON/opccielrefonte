package com.ged.entity.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ged.entity.Base;
import com.ged.entity.standard.CleRolePermission;
import jakarta.persistence.*;

@Entity
@Table(name = "TJ_RolePermission", schema = "Parametre")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class RolePermission extends Base {
    @EmbeddedId
    private CleRolePermission id;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idRole")
    @MapsId("idRole")
    private Role role;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPermis")
    @MapsId("idPermis")
    private Permission permission;

    public RolePermission() {
    }

    public RolePermission(CleRolePermission id, Role role, Permission permission) {
        this.id = id;
        this.role = role;
        this.permission = permission;
    }

    public RolePermission(Role role, Permission permission) {
        this.role = role;
        this.permission = permission;
    }

    public CleRolePermission getId() {
        return id;
    }

    public void setId(CleRolePermission id) {
        this.id = id;
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
}
