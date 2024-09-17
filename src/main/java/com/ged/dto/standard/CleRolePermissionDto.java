package com.ged.dto.standard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.NonNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CleRolePermissionDto {
    private Long idRole;
    private Long idPermis;

    public CleRolePermissionDto() {
        idRole = null;
        idPermis = null;
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
