package com.ged.service.standard;

import com.ged.dto.security.Utilisateur2Dto;
import com.ged.dto.security.UtilisateurDto;
import com.ged.entity.security.Utilisateur;
import com.ged.dto.security.LoginRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UtilisateurService {
    UtilisateurDto login(LoginRequest loginRequest);
    UtilisateurDto findByUsername(String username);
    Page<Utilisateur2Dto> afficherUsers(int page, int size);
    Page<UtilisateurDto> afficherUtilisateurs(int page, int size);
    List<UtilisateurDto> afficherTous();
    UtilisateurDto afficherUtilisateur(Long id);
    Utilisateur afficherUtilisateurSelonId(Long idUtilisateur);
    UtilisateurDto creerUtilisateur(UtilisateurDto utilisateurDto);
    UtilisateurDto modifierUtilisateur(UtilisateurDto utilisateurDto);

    void registerDefaultUsers();

    void supprimerUtilisateur(Long idUtilisateur);
//    UserDetailsService userDetailsService();
}
