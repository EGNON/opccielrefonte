package com.ged.dao.security;

import com.ged.entity.security.CleRolePermission;
import com.ged.entity.security.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolePermissionDao extends JpaRepository<RolePermission, CleRolePermission> {
}
