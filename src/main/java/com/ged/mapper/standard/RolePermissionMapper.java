package com.ged.mapper.standard;

import com.ged.dto.security.RolePermissionDto;
import com.ged.entity.security.RolePermission;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class RolePermissionMapper {
    public RolePermissionDto deRolePermission(RolePermission rolePermission) {
        RolePermissionDto rolePermissionDto = new RolePermissionDto();
        BeanUtils.copyProperties(rolePermission, rolePermissionDto);
        return rolePermissionDto;
    }
    public RolePermission deRolePermissionDto(RolePermissionDto rolePermissionDto) {
        RolePermission rolePermission = new RolePermission();
        BeanUtils.copyProperties(rolePermissionDto, rolePermission);
        return rolePermission;
    }
}
