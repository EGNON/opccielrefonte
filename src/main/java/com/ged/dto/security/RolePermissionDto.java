package com.ged.dto.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.standard.CleRolePermissionDto;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RolePermissionDto {
    private CleRolePermissionDto id;
    @JsonIgnore
    private RoleDto role;
    private PermissionDto permission;

    public CleRolePermissionDto getId() {
        return id;
    }

    public void setId(CleRolePermissionDto id) {
        this.id = id;
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
}
