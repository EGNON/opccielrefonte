package com.ged.service.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ged.dao.security.RolePermissionDao;
import com.ged.dao.security.UtilisateurDao;
import com.ged.dto.security.LoginRequest;
import com.ged.dto.security.PasswordRequest;
import com.ged.dto.security.UtilisateurDto;
import com.ged.dto.standard.*;
import com.ged.entity.security.RolePermission;
import com.ged.entity.security.Utilisateur;
import com.ged.mapper.standard.UtilisateurMapper;
import com.ged.response.ResponseHandler;
import com.ged.entity.security.token.Token;
import com.ged.dao.security.token.TokenDao;
import com.ged.dto.security.TokenResponse;
import com.ged.entity.security.token.TokenType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.security.Principal;
import java.util.HashSet;

//public class AuthenticationService {

@Service
@Transactional("refonteTransactionManager")
public class AuthenticationService {
    @Value("${application.currentinfo.user}")
    public String currentUser;
    private final UtilisateurDao utilisateurDao;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final TokenDao tokenDao;
    private final UtilisateurMapper utilisateurMapper;
    private final RolePermissionDao rolePermissionDao;

    public AuthenticationService(
            UtilisateurDao utilisateurDao,
            JwtService jwtService,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            TokenDao tokenDao,
            UtilisateurMapper utilisateurMapper,
            RolePermissionDao rolePermissionDao) {
        this.utilisateurDao = utilisateurDao;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.tokenDao = tokenDao;
        this.utilisateurMapper = utilisateurMapper;
        this.rolePermissionDao = rolePermissionDao;
    }

    public ResponseEntity<Object> login(LoginRequest loginRequest) throws Exception {
        try {
            String message = loginRequest.getUsername() + " connecté avec succès.";
            var auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
            var user = (Utilisateur)auth.getPrincipal();
            var utilisateur = utilisateurDao.findByUsernameAndEstActif(loginRequest.getUsername(), true).orElse(null);
            var jwt = jwtService.generateToken(utilisateur);
            var refreshToken = jwtService.generateRefreshToken(utilisateur);
            assert utilisateur != null;
            revokeAllUserTokens(utilisateur);
            saveUserToken(utilisateur, jwt);
            return ResponseHandler.generateResponse(
                    message,
                    HttpStatus.OK,
                    JwtAuthenticationResponse.builder()
                            .authToken(jwt)
                            .refreshToken(refreshToken)
                            .build());
        }
        catch (BadCredentialsException e)
        {
            return ResponseHandler.generateResponse(
                    "NOM D'UTILISATEUR OU MOT DE PASSE INCORRECTS",
                    HttpStatus.MULTI_STATUS,
                    e);
        }
        catch (DisabledException e)
        {
            return ResponseHandler.generateResponse(
                    "UTILISATEUR DESACTIVE",
                    HttpStatus.MULTI_STATUS,
                    e);
        }
        catch (Exception e)
        {
            return ResponseHandler.generateResponse(
                    "INFORMATIONS DE CONNEXION INCORRECTS",
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    public ResponseEntity<Object> me(String bearerToken) {
        TokenResponse token = new TokenResponse();
        try {
            var userToken = tokenDao.findByToken(bearerToken);
            var isTokenValid = userToken.map(t -> !t.isExpired() && !t.isRevoked())
                    .orElse(false);
            if(!isTokenValid)
                return null;
            token.setId(userToken.get().getId());
            token.setToken(userToken.get().getToken());
            token.setExpired(userToken.get().expired);
            token.setRevoked(userToken.get().revoked);
            token.setUser(utilisateurMapper.deUtilisateur(userToken.get().user));

            return ResponseHandler.generateResponse(
                    "Token récupéré",
                    HttpStatus.OK,
                    token);
        }
        catch (Exception e)
        {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    public JwtAuthenticationResponse register(UtilisateurDto utilisateurDto) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Utilisateur utilisateur = modelMapper.map(utilisateurDto, Utilisateur.class);
        if(utilisateurDao.existsByUsername(utilisateur.getUsername()))
        {
            return JwtAuthenticationResponse.builder().authToken(null).build();
        }
        utilisateur.setDenomination(utilisateurDto.getNom() + " " + utilisateurDto.getPrenom());
        utilisateur.setPassword1(passwordEncoder.encode(utilisateurDto.getPassword1()));
        utilisateur.setPassword(passwordEncoder.encode(utilisateurDto.getPassword()));
        //Ajouter les roles
        for (RolePermission rolePermission: rolePermissionDao.findAll()) {
            utilisateur.setRoles1(new HashSet<>());
            utilisateur.setPermissions(new HashSet<>());
            utilisateur.ajouterRole(rolePermission.getRole());
            utilisateur.ajouterPermission(rolePermission);
        }

        Utilisateur utilisateurSave = utilisateurDao.saveAndFlush(utilisateur);
        var jwt = jwtService.generateToken(utilisateurSave);
        var refreshToken = jwtService.generateRefreshToken(utilisateurSave);
        saveUserToken(utilisateurSave, jwt);
        return JwtAuthenticationResponse.builder()
                .authToken(jwt)
                .refreshToken(refreshToken)
                .build();
    }

    public Boolean IsOldPasswordValid(Utilisateur utilisateur, String oldPassword)
    {
        return passwordEncoder.matches(oldPassword, utilisateur.getPassword());
    }

    public ResponseEntity<Object> changePassword(PasswordRequest passwordRequest, Principal connectedUser)
    {
        Utilisateur utilisateur;
        try {
            if(connectedUser != null)
                utilisateur = (Utilisateur)((UsernamePasswordAuthenticationToken)connectedUser).getPrincipal();
            else
                utilisateur = utilisateurDao.findByUsernameAndEstActif(passwordRequest.getUsername(), true).orElse(null);

            if(utilisateur != null && IsOldPasswordValid(utilisateur, passwordRequest.getOldPassword()))
            {
                utilisateur.setPassword1(passwordEncoder.encode(passwordRequest.getNewPassword()));
                utilisateur.setPassword(passwordEncoder.encode(passwordRequest.getNewPassword()));
                return ResponseHandler.generateResponse(
                        "Votre mot de passe a été changé avec succès",
                        HttpStatus.OK,
                        utilisateurMapper.deUtilisateur(utilisateurDao.save(utilisateur)));
            }
            else
            {
                return ResponseHandler.generateResponse(
                        "Ancien mot de passe incorrect",
                        HttpStatus.OK,
                        null);
            }
        }
        catch (Exception e)
        {
            return ResponseHandler.generateResponse(
                    "Une erreur a été détectée lors du changement de mot de passe.\n" +
                            "Veuillez réessayer plus tard.",
                    HttpStatus.OK,
                    e);
        }
    }

    private void saveUserToken(Utilisateur user, String jwtToken) {
        Token token = new Token();
        token.setToken(jwtToken);
        token.setTokenType(TokenType.BEARER);
        token.setExpired(false);
        token.setRevoked(false);
        token.setUser(user);
        tokenDao.save(token);
    }

    private void revokeAllUserTokens(Utilisateur user) {
        var validUserTokens = tokenDao.findAllValidTokenByUser(user.getId().intValue());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenDao.saveAll(validUserTokens);
    }

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String username;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        username = jwtService.extractUsername(refreshToken);
        if (username != null) {
            var user = this.utilisateurDao.findByUsernameAndEstActif(username, true)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = JwtAuthenticationResponse.builder()
                        .authToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }

    public UserDetails getLoggedInUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            return (UserDetails)authentication.getPrincipal();
        }
        return  null;
    }
}
