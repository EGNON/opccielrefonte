package com.ged.entity.security;

import com.ged.dto.security.UtilisateurDto;
import com.ged.entity.standard.Personnel;
import com.ged.entity.security.token.Token;
import jakarta.persistence.*;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@DiscriminatorValue("U")
@PrimaryKeyJoinColumn(name = "idPersonne")
@Table(
    name = "T_User",
    schema = "Parametre",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "username")
    }
)
//@SQLInsert(sql = "insert into utilisateur ()")
public class Utilisateur extends Personnel implements UserDetails, Principal {
    @Transient
    private Long id;
    @Basic
    @Column(unique = true)
    private String username;
    @Basic
    private String password;
    private String password1;
    @Column(columnDefinition = "BIT", length = 1)
    private Boolean estActif;
    //OPCCIEL 1
    private Boolean estActiveDirectory;
    private Boolean autoriseDateAnterieur;
    private Boolean modifierPassword;
    private Boolean estMotdePasseTemporaire;
    private String motDePasse;
    private String motDePasseCrypte;
    private String cheminPhoto;
    private String login;
    private String nomUtilisateur;
    private String prenomUtilisateur;
    private LocalDateTime dateDernModifMotdePasse;
    @Column(nullable = true)
    private long numeroUtilisateur;
    //FIN
    private String authToken;
    private String refreshToken;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    //@JsonManagedReference
    private Set<Token> tokens = new HashSet<>();
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "utilisateur", cascade = CascadeType.ALL)
//    //@JsonManagedReference
//    private Set<UtilisateurRole> utilisateurRoles = new HashSet<>();
//    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
//    @JoinTable(name = "TJ_UtilisateurRole",
//    joinColumns = {@JoinColumn(name = "idUtilisateur", referencedColumnName = "idPersonne")},
//    inverseJoinColumns = {@JoinColumn(name = "idRole")}, schema = "Parametre")
//    private Set<Role> roles = new HashSet<>();

//    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinTable(schema = "Parametre", name = "TJ_UtilisateurRole", joinColumns = @JoinColumn(name = "idUtilisateur"),
//            inverseJoinColumns = @JoinColumn(name = "idRole"))
    /*@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(schema = "Parametre", name = "TJ_UtilisateurRole", joinColumns = @JoinColumn(name = "idUtilisateur"),
            inverseJoinColumns = @JoinColumn(name = "idRole"))
    private Set<Role> roles = new HashSet<>();*/

    /*@OneToMany(
            mappedBy = "utilisateur",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE},
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )*/
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "idUtilisateur")
    private Set<UtilisateurRole> roles1 = new HashSet<>();
    /*@OneToMany(
            mappedBy = "utilisateur",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE},
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )*/
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "idUtilisateur")
    private Set<UtilisateurRolePermission> permissions = new HashSet<>();

    public static UtilisateurDto deUtilisateur(Utilisateur user) {
        if (user == null) {
            return null;
        }

        UtilisateurDto utilisateurDto = new UtilisateurDto();
        BeanUtils.copyProperties(user, utilisateurDto);

        return utilisateurDto;
    }

    public void addUtilisateurRole(Set<UtilisateurRole> utilisateurRoles) {
        this.roles1 = utilisateurRoles;
        this.roles1.forEach(c -> c.setUtilisateur(this));
    }

    //Getters and setters omitted for brevity
    public void ajouterRole(Role role) {
        if (this.roles1 == null){
            this.roles1 = new HashSet<>();
        }
        UtilisateurRole utilisateurRole = new UtilisateurRole(this, role);
        roles1.add(utilisateurRole);
        role.getUtilisateurs1().add(utilisateurRole);
    }

    public void ajouterPermission(RolePermission rolePermission) {
        if (this.permissions == null){
            this.permissions = new HashSet<>();
        }
        UtilisateurRolePermission utilisateurRolePermission = new UtilisateurRolePermission(this, rolePermission.getRole(), rolePermission.getPermission());
        this.permissions.add(utilisateurRolePermission);
        rolePermission.getRole().getUserPermissions().add(utilisateurRolePermission);
        rolePermission.getPermission().getUserRoles().add(utilisateurRolePermission);
    }

    public void supprimerRole(Role role) {
        for (Iterator<UtilisateurRole> iterator = roles1.iterator();
             iterator.hasNext(); ) {
            UtilisateurRole utilisateurRole = iterator.next();

            if (utilisateurRole.getUtilisateur().equals(this) &&
                    utilisateurRole.getRole().equals(role)) {
                iterator.remove();
                utilisateurRole.getRole().getUtilisateurs1().remove(utilisateurRole);
                utilisateurRole.setUtilisateur(null);
                utilisateurRole.setRole(null);
            }
        }
    }

    public void supprimerPermission(Role role) {
        for (Iterator<UtilisateurRole> iterator = roles1.iterator();
             iterator.hasNext(); ) {
            UtilisateurRole utilisateurRole = iterator.next();

            if (utilisateurRole.getUtilisateur().equals(this) &&
                    utilisateurRole.getRole().equals(role)) {
                iterator.remove();
                utilisateurRole.getRole().getUtilisateurs1().remove(utilisateurRole);
                utilisateurRole.setUtilisateur(null);
                utilisateurRole.setRole(null);
            }
        }
    }

    public Utilisateur() {
        super();
    }

    public Utilisateur(Long id, String username, String password1, Boolean estActif, String authToken, String refreshToken) {
        this.id = id;
        this.username = username;
        this.password1 = password1;
        this.estActif = estActif;
        this.authToken = authToken;
        this.refreshToken = refreshToken;
    }

    public Utilisateur(String userLogin, String matricule, Long id, String username, String password1, Boolean estActif, String authToken, String refreshToken) {
        super(matricule);
        this.id = id;
        this.username = username;
        this.password1 = password1;
        this.estActif = estActif;
        this.authToken = authToken;
        this.refreshToken = refreshToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Utilisateur that = (Utilisateur) o;
        return Objects.equals(username, that.username) && Objects.equals(password1, that.password1);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), username, password1);
    }

    @Override
    public String getName() {
        return username;
    }

    public Long getId() {
        return getIdPersonne();
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (UtilisateurRole utilisateurRole: this.getRoles1()) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(utilisateurRole.getRole().getNom());
            authorities.add(authority);
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Set<Token> getTokens() {
        return tokens;
    }

    public void setTokens(Set<Token> tokens) {
        this.tokens = tokens;
    }

    //OPCCIEL1

    public Boolean getEstActiveDirectory() {
        return estActiveDirectory;
    }

    public void setEstActiveDirectory(Boolean estActiveDirectory) {
        this.estActiveDirectory = estActiveDirectory;
    }

    public Boolean getAutoriseDateAnterieur() {
        return autoriseDateAnterieur;
    }

    public void setAutoriseDateAnterieur(Boolean autoriseDateAnterieur) {
        this.autoriseDateAnterieur = autoriseDateAnterieur;
    }

    public Boolean getModifierPassword() {
        return modifierPassword;
    }

    public void setModifierPassword(Boolean modifierPassword) {
        this.modifierPassword = modifierPassword;
    }

    public Boolean getEstMotdePasseTemporaire() {
        return estMotdePasseTemporaire;
    }

    public void setEstMotdePasseTemporaire(Boolean estMotdePasseTemporaire) {
        this.estMotdePasseTemporaire = estMotdePasseTemporaire;
    }

    public String getCheminPhoto() {
        return cheminPhoto;
    }

    public void setCheminPhoto(String cheminPhoto) {
        this.cheminPhoto = cheminPhoto;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public LocalDateTime getDateDernModifMotdePasse() {
        return dateDernModifMotdePasse;
    }

    public void setDateDernModifMotdePasse(LocalDateTime dateDernModifMotdePasse) {
        this.dateDernModifMotdePasse = dateDernModifMotdePasse;
    }

    public long getNumeroUtilisateur() {
        return numeroUtilisateur;
    }

    public void setNumeroUtilisateur(long numeroUtilisateur) {
        this.numeroUtilisateur = numeroUtilisateur;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    public String getPrenomUtilisateur() {
        return prenomUtilisateur;
    }

    public void setPrenomUtilisateur(String prenomUtilisateur) {
        this.prenomUtilisateur = prenomUtilisateur;
    }

    public Set<UtilisateurRole> getRoles1() {
        return roles1;
    }

    public void setRoles1(Set<UtilisateurRole> roles1) {
        this.roles1.clear();
        if (roles1 != null) {
            this.roles1.addAll(roles1);
            for (UtilisateurRole utilisateurRole : roles1) {
                utilisateurRole.setUtilisateur(this);
            }
        }
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getMotDePasseCrypte() {
        return motDePasseCrypte;
    }

    public void setMotDePasseCrypte(String motDePasseCrypte) {
        this.motDePasseCrypte = motDePasseCrypte;
    }

    public Set<UtilisateurRolePermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<UtilisateurRolePermission> permissions) {
        this.permissions.clear();
        if (permissions != null) {
            this.permissions.addAll(permissions);
        }
    }

    //FIN
    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + getIdPersonne() +
                ", username='" + username + '\'' +
                ", password='" + password1 + '\'' +
                ", estActif=" + estActif +
                ", authToken='" + authToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
//                ", tokens=" + tokens.size() +
//                ", permissions=" + permissions + '\'' +
//                ", utilisateurRoles=" + utilisateurRoles.size() +
                '}';
    }
}
