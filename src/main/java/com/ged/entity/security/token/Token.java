package com.ged.entity.security.token;

import com.ged.entity.Base;
import com.ged.entity.security.Utilisateur;
import jakarta.persistence.*;

import java.time.LocalDateTime;

//@Data
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
@Entity
@Table(name = "T_Token", schema = "Parametre")
public class Token extends Base {
    @Id
    @GeneratedValue
    public Integer id;

    @Column(unique = true)
    public String token;

    @Enumerated(EnumType.STRING)
    public TokenType tokenType = TokenType.BEARER;

    public boolean revoked;

    public boolean expired;

    private LocalDateTime expiresAt;
    private LocalDateTime validatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idPersonne")
    //@JsonBackReference
    public Utilisateur user;

    public Token() {
    }

    public Token(Integer id, String token, TokenType tokenType, boolean revoked, boolean expired, Utilisateur user) {
        this.id = id;
        this.token = token;
        this.tokenType = tokenType;
        this.revoked = revoked;
        this.expired = expired;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public void setTokenType(TokenType tokenType) {
        this.tokenType = tokenType;
    }

    public boolean isRevoked() {
        return revoked;
    }

    public void setRevoked(boolean revoked) {
        this.revoked = revoked;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public LocalDateTime getValidatedAt() {
        return validatedAt;
    }

    public void setValidatedAt(LocalDateTime validatedAt) {
        this.validatedAt = validatedAt;
    }

    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }
}
