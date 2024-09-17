package com.ged.mapper.standard;

import com.ged.dto.security.PermissionDto;
import com.ged.entity.security.Permission;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class PermissionMapper {
    public PermissionDto dePermission(Permission permission) {
        if(permission == null)
            return null;
        PermissionDto permissionDto = new PermissionDto();
        BeanUtils.copyProperties(permission, permissionDto);

        return permissionDto;
    }

    public Permission dePermissionDto(PermissionDto permissionDto) {
        if(permissionDto == null)
            return  null;
        Permission permission = new Permission();
        BeanUtils.copyProperties(permissionDto, permission);

        return permission;
    }
}
