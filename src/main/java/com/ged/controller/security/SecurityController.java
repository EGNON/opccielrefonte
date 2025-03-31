package com.ged.controller.security;

import com.ged.dao.security.UtilisateurDao;
import com.ged.dto.security.LoginRequest;
import com.ged.dto.security.PasswordRequest;
import com.ged.service.security.AuthenticationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

public class SecurityController {

}

/*@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class SecurityController {
    @Value("${application.currentinfo.user}")
    public String currentUser;
    private final AuthenticationService authenticationService;

    public SecurityController(UtilisateurDao utilisateurDao, AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping("/profile")
    public Authentication authentication(Authentication authentication)
    {
        return authentication;
    }

    @GetMapping("/me")
    public ResponseEntity<Object> me(@RequestHeader("Authorization") String bearerToken)
    {
        String realToken = bearerToken.substring(7);
        return authenticationService.me(realToken);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest) throws Exception {
        return authenticationService.login(loginRequest);
    }

private final AuthenticationService authenticationService;
    @PatchMapping("change-password")
    public ResponseEntity<Object> changePassword(
            @RequestBody PasswordRequest passwordRequest, Principal connectedUser)
    {
        return authenticationService.changePassword(passwordRequest, connectedUser);
    }
}*/
