package com.ged.dao.security;

import com.ged.entity.security.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(propagation = Propagation.REQUIRES_NEW)
public interface UtilisateurDao extends JpaRepository<Utilisateur,Long> {
    @Query(value = "SELECT u FROM Utilisateur u WHERE u.username = :username AND u.password = :password")
    Utilisateur findByUsernameAndPassword(String username, String password);
    @Query(value = "SELECT u FROM Utilisateur u WHERE LOWER(u.username) = LOWER(:username)")
    Optional<Utilisateur> findByUsernameIgnoreCase(String username);
    Utilisateur findById(long idUtilisateur);
    @Query(value = "SELECT u FROM Utilisateur u WHERE u.username = :username AND u.estActif = :estActif")
    Optional<Utilisateur> findByUsernameAndEstActif(String username, boolean estActif);
    Boolean existsByUsername(String username);
}
