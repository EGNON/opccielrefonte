package com.ged.service.security;

import com.ged.com.ged.email.EmailsService;
import com.ged.com.ged.email.EmailTemplateName;
import com.ged.dao.security.RoleDao;
import com.ged.dao.security.UtilisateurDao;
import com.ged.dao.security.UtilisateurRoleDao;
import com.ged.dao.security.token.TokenDao;
import com.ged.dto.security.LoginRequest;
import com.ged.dto.security.PasswordRequest;
import com.ged.dto.security.TokenResponse;
import com.ged.dto.security.UtilisateurDto;
import com.ged.dto.standard.JwtAuthenticationResponse;
import com.ged.entity.security.CleUtilisateurRole;
import com.ged.entity.security.Utilisateur;
import com.ged.entity.security.UtilisateurRole;
import com.ged.entity.security.token.Token;
import com.ged.entity.security.token.TokenType;
import com.ged.mapper.standard.UtilisateurMapper;
import com.ged.response.AuthenticationResponse;
import com.ged.response.ResponseHandler;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Set;

import static com.ged.entity.security.token.TokenType.*;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final RoleDao roleDao;
    private final PasswordEncoder passwordEncoder;
    private final UtilisateurDao utilisateurDao;
    private final UtilisateurRoleDao utilisateurRoleDao;
    private final TokenDao tokenDao;
    private final EmailsService emailService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UtilisateurMapper utilisateurMapper;
    @Value("${application.mailing.frontend.activation-url}")
    private String activationUrl;

    public void register(UtilisateurDto request) throws MessagingException {
        var role = roleDao.findByNom("ROLE_SUPER_ADMIN")
                .orElseThrow(() -> new IllegalStateException("ROLE USER n'a pas été initialisé"));
        var user = new Utilisateur();
        user.setNom(request.getNom());
        user.setPrenom(request.getPrenom());
        user.setDenomination(request.getNom() + " " + user.getPrenom());
        user.setMobile1(request.getMobile1());
        user.setMobile2(request.getMobile2());
        user.setEmailPerso(request.getEmailPerso());
        user.setEmailPro(request.getEmailPro());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEstActif(false);
        UtilisateurRole userRole = new UtilisateurRole();
        user = utilisateurDao.save(user);
        CleUtilisateurRole cleUr = new CleUtilisateurRole();
        cleUr.setIdRole(role.getIdRole());
        cleUr.setIdUtilisateur(user.getIdPersonne());
        userRole.setId(cleUr);
        userRole.setRole(role);
        userRole.setUtilisateur(user);
        user.setRoles1(Set.of(userRole));
        utilisateurRoleDao.save(userRole);
        sendValidationEmail(user);
    }

    private void sendValidationEmail(Utilisateur user) throws MessagingException {
        var newToken = generateAndSaveActivationToken(user);
        emailService.sendEmail(
                user.getEmailPro(),
                user.getDenomination(),
                EmailTemplateName.ACTIVATE_ACCOUNT,
                activationUrl,
                newToken,
                "Activation de compte"
        );
    }

    private String generateAndSaveActivationToken(Utilisateur user) {
        // generate a token
        String generatedToken = generateActivationCode(6);
        var token = new Token();
        token.setToken(generatedToken);
        token.setDateCreationServeur(LocalDateTime.now());
        token.setExpiresAt(LocalDateTime.now().plusMinutes(15));
        token.setUser(user);
        tokenDao.save(token);
        return generatedToken;
    }

    private String generateActivationCode(int length) {
        String characters = "0123456789";
        StringBuilder codeBuilder = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();
        for (int i = 0; i < length; i++) {
            int randomIndex = secureRandom.nextInt(characters.length());
            codeBuilder.append(characters.charAt(randomIndex));
        }
        return codeBuilder.toString();
    }

    public ResponseEntity<Object> me(String bearerToken) {
        TokenResponse token = new TokenResponse();
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

    public ResponseEntity<?> authenticate(LoginRequest request) {
        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var claims = new HashMap<String, Object>();
        var user = ((Utilisateur)auth.getPrincipal());
        claims.put("nomComplet", user.getDenomination());
        var jwtToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        System.out.println("lenght="+jwtToken.length());
        saveUserToken(user, jwtToken);

        return ResponseHandler.generateResponse(
                "L'utilisateur " + user.getUsername() + " a été authentifié avec succès",
                HttpStatus.OK,
                AuthenticationResponse.builder()
                        .token(jwtToken)
                        .authToken(jwtToken)
                        .refreshToken(null)
                        .build());
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

    private void saveUserToken(Utilisateur user, String jwtToken) {
        Token token = new Token();
        token.setToken(jwtToken);
        token.setTokenType(BEARER);
        token.setExpired(false);
        token.setRevoked(false);
        token.setUser(user);
        tokenDao.save(token);
    }

    //@Transactional
    public void activateAccount(String token) throws MessagingException {
        Token savedToken = tokenDao.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Toke invalide"));
        if(LocalDateTime.now().isAfter(savedToken.getExpiresAt())) {
            sendValidationEmail(savedToken.getUser());
            throw new RuntimeException("Le token d'activation a expiré. Un nouveau token a été envoyé à la même adresse email");
        }
        var user = utilisateurDao.findById(savedToken.getUser().getIdPersonne())
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur introuvable"));
        user.setEstActif(true);
        utilisateurDao.save(user);
        savedToken.setValidatedAt(LocalDateTime.now());
        tokenDao.save(savedToken);
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

}
