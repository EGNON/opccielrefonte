package com.ged.dao.security;

import com.ged.entity.security.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RoleDao extends JpaRepository<Role, Long>, JpaSpecificationExecutor<Role> {
    Optional<Role> findByNom(String nom);
    @Query(value = "select r from Role as r where r.idRole = :idRole")
    Role trouverRoleSelonId(@Param("idRole") Long idRole);
    Optional<Role> findByNomEquals(String keyword);
    Boolean existsByNom(String nom);
    Page<Role> findByNomContainingIgnoreCase(String keyword, Pageable pageable);
}
