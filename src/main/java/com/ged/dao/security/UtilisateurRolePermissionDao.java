package com.ged.dao.security;

import com.ged.entity.standard.CleUtilisateurRolePermission;
import com.ged.entity.security.UtilisateurRolePermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRolePermissionDao extends JpaRepository<UtilisateurRolePermission, CleUtilisateurRolePermission> {
}
