package com.ged.dao.security;

import com.ged.entity.security.Permission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PermissionDao extends JpaRepository<Permission, Long> {
    Page<Permission> findByLibellePermisContainsIgnoreCase(String libelle, Pageable pageable);
    Optional<Permission> findByLibellePermisContainsIgnoreCase(String libelle);
    Boolean existsByLibellePermis(String libelle);
}
