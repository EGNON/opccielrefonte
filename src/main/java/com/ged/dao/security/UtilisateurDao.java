package com.ged.dao.security;

import com.ged.entity.security.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.lang.NonNullApi;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(propagation = Propagation.REQUIRES_NEW)
public interface UtilisateurDao extends JpaRepository<Utilisateur,Long> {
    @Query(value = "SELECT u FROM Utilisateur u WHERE u.idPersonne = :id")
    Optional<Utilisateur> findByIdPersonne(Long id);
    @Query(value = "SELECT u FROM Utilisateur u WHERE u.username = :username AND u.password = :password")
    Utilisateur findByUsernameAndPassword(String username, String password);
    @Query(value = "SELECT u FROM Utilisateur u WHERE LOWER(u.username) = LOWER(:username)")
    Optional<Utilisateur> findByUsernameIgnoreCase(String username);
    Utilisateur findById(long idUtilisateur);
    @Query(value = "SELECT u FROM Utilisateur u WHERE u.username = :username AND u.estActif = :estActif")
    Optional<Utilisateur> findByUsernameAndEstActif(String username, boolean estActif);
    Boolean existsByUsername(String username);

    @Query(value = "SELECT u FROM Utilisateur u order by u.denomination asc")
    List<Utilisateur> afficherTous();
}
