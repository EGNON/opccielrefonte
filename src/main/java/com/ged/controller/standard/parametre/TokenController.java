package com.ged.controller.standard.parametre;

import com.ged.dto.security.UtilisateurDto;
import com.ged.service.standard.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tokens")
@CrossOrigin(origins = "*")
public class TokenController {
    private final TokenService tokenService;

    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<Object> afficherTokenSelonUser(UtilisateurDto utilisateurDto) {
        return tokenService.afficherSelonUser(utilisateurDto);
    }
}
