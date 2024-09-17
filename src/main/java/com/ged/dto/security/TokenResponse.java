package com.ged.dto.security;

import com.ged.entity.security.token.TokenType;

import java.io.Serializable;

public class TokenResponse implements Serializable {
    public Integer id;
    public String token;
    public TokenType tokenType;
    public boolean revoked;
    public boolean expired;
    public UtilisateurDto user;

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

    public UtilisateurDto getUser() {
        return user;
    }

    public void setUser(UtilisateurDto user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "TokenResponse{" +
                "id=" + id +
                ", token='" + token + '\'' +
                ", tokenType=" + tokenType +
                ", revoked=" + revoked +
                ", expired=" + expired +
                '}';
    }
}
