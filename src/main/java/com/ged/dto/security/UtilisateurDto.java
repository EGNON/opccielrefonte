package com.ged.dto.security;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ged.dto.standard.PaysDto;
import com.ged.dto.standard.PersonnelDto;
import com.ged.dto.standard.ProfessionDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UtilisateurDto extends PersonnelDto {
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
    private String emailPro;
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
    private PaysDto paysResidence;
    private PaysDto paysNationalite;
    private ProfessionDto profession;
    private Set<TokenResponse> tokens = new HashSet<>();
    private Set<RoleDto> roles;
    private Set<UtilisateurRoleDto> roles1 = new HashSet<>();
    private Set<UtilisateurRolePermissionDto> permissions = new HashSet<>();

    public UtilisateurDto() {
        this.estActif = false;
    }

    public UtilisateurDto(String username, String password1, Boolean estActif) {
        this.username = username;
        this.password1 = password1;
        this.estActif = estActif;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Override
    public LocalDateTime getDateNaissance() {
        return dateNaissance;
    }
    @Override
    public void setDateNaissance(LocalDateTime dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
    @Override
    public String getCivilite() {
        return civilite;
    }
    @Override
    public void setCivilite(String civilite) {
        this.civilite = civilite;
    }
    @Override
    public String getStatutMatrimonial() {
        return statutMatrimonial;
    }
    @Override
    public void setStatutMatrimonial(String statutMatrimonial) {
        this.statutMatrimonial = statutMatrimonial;
    }
    @Override
    public String getMobile2() {
        return mobile2;
    }
    @Override
    public void setMobile2(String mobile2) {
        this.mobile2 = mobile2;
    }
    @Override
    public PaysDto getPaysResidence() {
        return paysResidence;
    }
    @Override
    public void setPaysResidence(PaysDto paysResidence) {
        this.paysResidence = paysResidence;
    }
    @Override
    public PaysDto getPaysNationalite() {
        return paysNationalite;
    }
    @Override
    public void setPaysNationalite(PaysDto paysNationalite) {
        this.paysNationalite = paysNationalite;
    }
    @Override
    public String getEmailPro() {
        return emailPro;
    }
    @Override
    public void setEmailPro(String emailPro) {
        this.emailPro = emailPro;
    }
    @Override
    public String getEmailPerso() {
        return emailPerso;
    }
    @Override
    public void setEmailPerso(String emailPerso) {
        this.emailPerso = emailPerso;
    }
    @Override
    public String getSexe() {
        return sexe;
    }
    @Override
    public void setSexe(String sexe) {
        this.sexe = sexe;
    }
    @Override
    public String getMobile1() {
        return mobile1;
    }

    @Override
    public void setMobile1(String mobile1) {
        this.mobile1 = mobile1;
    }
    @Override
    public String getNom() {
        return nom;
    }
    @Override
    public void setNom(String nom) {
        this.nom = nom;
    }
    @Override
    public String getPrenom() {
        return prenom;
    }
    @Override
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    @Override
    public Long getIdPersonne() {
        return IdPersonne;
    }
    @Override
    public void setIdPersonne(Long idPersonne) {
        IdPersonne = idPersonne;
    }
    @Override
    public String getDenomination() {
        return denomination;
    }
    @Override
    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public Boolean getEstActif() {
        return estActif;
    }

    public void setEstActif(Boolean estActif) {
        this.estActif = estActif;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password) {
        this.password1 = password;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public ProfessionDto getProfession() {
        return profession;
    }

    public void setProfession(ProfessionDto profession) {
        this.profession = profession;
    }

    public Set<TokenResponse> getTokens() {
        return tokens;
    }

    public void setTokens(Set<TokenResponse> tokens) {
        this.tokens = tokens;
    }

    public Set<RoleDto> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleDto> roles) {
        this.roles = roles;
    }

    public Set<UtilisateurRoleDto> getRoles1() {
        return roles1;
    }

    public void setRoles1(Set<UtilisateurRoleDto> roles1) {
        this.roles1 = roles1;
    }

    public Set<UtilisateurRolePermissionDto> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<UtilisateurRolePermissionDto> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return "UtilisateurDto{" +
                "id=" + id +
                ", IdPersonne=" + IdPersonne +
                ", denomination='" + denomination + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", sexe='" + sexe + '\'' +
                ", dateNaissance=" + dateNaissance +
                ", civilite='" + civilite + '\'' +
                ", statutMatrimonial='" + statutMatrimonial + '\'' +
                ", mobile1='" + mobile1 + '\'' +
                ", mobile2='" + mobile2 + '\'' +
                ", emailPro='" + emailPro + '\'' +
                ", emailPerso='" + emailPerso + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password1 + '\'' +
                ", estActif=" + estActif +
                ", authToken='" + authToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", pic='" + pic + '\'' +
                ", paysResidence=" + paysResidence +
                ", paysNationalite=" + paysNationalite +
                ", profession=" + profession +
                ", tokens=" + tokens.size() +
                ", permissions=" + permissions.size() +
                '}';
    }
}
