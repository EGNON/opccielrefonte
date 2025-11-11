package com.ged.service.standard;

import com.ged.dto.security.Utilisateur2Dto;
import com.ged.dto.security.UtilisateurDto;
import com.ged.entity.security.Utilisateur;
import com.ged.dto.security.LoginRequest;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

public interface UtilisateurService {
    UtilisateurDto login(LoginRequest loginRequest);
    UtilisateurDto findByUsername(String username);
    Page<Utilisateur2Dto> afficherUsers(int page, int size);
    Page<UtilisateurDto> afficherUtilisateurs(int page, int size);
    ResponseEntity<Object> afficherTous();
    ResponseEntity<Object> afficherTousListe();
    Workbook createExcel() throws IOException;
    ResponseEntity<Object> afficherUtilisateur(Long id);
    Utilisateur afficherUtilisateurSelonId(Long idUtilisateur);
    ResponseEntity<Object> creerUtilisateur(UtilisateurDto utilisateurDto);
    ResponseEntity<Object> modifierUtilisateur(UtilisateurDto utilisateurDto);

    void registerDefaultUsers();

    void supprimerUtilisateur(Long idUtilisateur);
//    UserDetailsService userDetailsService();
}
