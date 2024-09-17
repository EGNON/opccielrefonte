package com.ged.dao.security;

import com.ged.entity.security.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtilisateurDao extends JpaRepository<Utilisateur,Long> {
    Utilisateur findByUsernameAndPassword(String username, String password);
    Optional<Utilisateur> findByUsernameIgnoreCase(String username);
    Utilisateur findById(long idUtilisateur);
    Optional<Utilisateur> findByUsernameAndEstActif(String username, boolean estActif);
    Boolean existsByUsername(String username);
}
