package com.ged.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class RegistrationRequest {
    private Long id;
    private Long IdPersonne;
    private String denomination;
    private String nom;
    private String prenom;
    private String sexe;
    private LocalDateTime dateNaissance;
    private String civilite;
    private String statutMatrimonial;
    private String mobile1;
    private String mobile2;
    @Email(message = "L'email n'est pas bien formatté --> toto@mail.com")
    private String emailPro;
    @Email(message = "L'email n'est pas bien formatté --> toto@mail.com")
    private String emailPerso;
    @NotEmpty(message = "Le nom d'utilisateur est obligatoire")
    @NotBlank(message = "Le mot d'utilisateur est obligatoire")
    private String username;
    @NotEmpty(message = "Le mot de passe est obligatoire")
    @NotBlank(message = "Le mot de passe est obligatoire")
    @Size(min = 8, message = "Le mot de passe doit contenir au moins 8 caractères")
    private String password1;
    @NotEmpty(message = "Le mot de passe est obligatoire")
    @NotBlank(message = "Le mot de passe est obligatoire")
    @Size(min = 8, message = "Le mot de passe doit contenir au moins 8 caractères")
    private String password;
    private Boolean estActif = false;
    private String authToken;
    private String refreshToken;
    private String pic;
}
