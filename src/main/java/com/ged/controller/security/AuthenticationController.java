package com.ged.controller.security;

import com.ged.dto.security.LoginRequest;
import com.ged.dto.security.PasswordRequest;
import com.ged.dto.security.UtilisateurDto;
import com.ged.service.security.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@CrossOrigin("*")
@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class AuthenticationController {

    private final AuthService service;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> register(
            @RequestBody @Valid UtilisateurDto utilisateurDto
    ) throws MessagingException {
        service.register(utilisateurDto);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/me")
    public ResponseEntity<Object> me(
            @RequestHeader("Authorization") String bearerToken)
    {
        String realToken = bearerToken.substring(7);
        return service.me(realToken);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(
            @RequestBody @Valid LoginRequest request
    ) {
        return service.authenticate(request);
    }

    @GetMapping("/activate-account")
    public void confirm(
            @RequestParam String token
    ) throws MessagingException {
        service.activateAccount(token);
    }

    @PatchMapping("change-password")
    public ResponseEntity<Object> changePassword(
            @RequestBody PasswordRequest passwordRequest, Principal connectedUser)
    {
        return service.changePassword(passwordRequest, connectedUser);
    }
}
