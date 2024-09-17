package com.ged.dao.security;

import com.ged.entity.security.Utilisateur;
import com.ged.entity.security.UtilisateurRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UtilisateurRoleDao extends JpaRepository<UtilisateurRole, Long> {
    void deleteAllByUtilisateur(Utilisateur utilisateur);
    List<UtilisateurRole> findByUtilisateur(Utilisateur utilisateur);
    @Modifying
    @Query(value = "DELETE FROM UtilisateurRole WHERE utilisateur.idPersonne=:id")
    void deleteUtilisateurRolesByUtilisateurIdPersonne(@Param("id") Long id);
}
