package com.ged.service.standard;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.security.UtilisateurDto;
import com.ged.entity.security.token.Token;
import com.ged.dto.security.TokenResponse;
import org.springframework.http.ResponseEntity;

public interface TokenService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    ResponseEntity<Object> afficherTous();
    ResponseEntity<Object> afficherTous(int page, int size);
    ResponseEntity<Object> afficher(Long id);
    Token afficherSelonId(Long id);
    ResponseEntity<Object> afficherSelonUser(UtilisateurDto utilisateurDto);
    ResponseEntity<Object> ajouter(TokenResponse tokenResponse);
    ResponseEntity<Object> modifier(TokenResponse tokenResponse);
    ResponseEntity<Object> supprimer(Long id);
}
