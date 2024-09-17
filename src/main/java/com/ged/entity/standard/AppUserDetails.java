package com.ged.entity.standard;

import com.ged.entity.security.Utilisateur;
import com.ged.entity.security.UtilisateurRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class AppUserDetails implements UserDetails {
    private final Utilisateur user;

    public AppUserDetails(Utilisateur user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        Set<UtilisateurRole> utilisateurRoles = user.getUtilisateurRoles();
//        for (UtilisateurRole utilisateurRole : utilisateurRoles) {
//            authorities.add(new SimpleGrantedAuthority(utilisateurRole.getRole().getNom()));
//        }
        Set<UtilisateurRole> utilisateurRoles = user.getRoles1();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (UtilisateurRole utilisateurRole : utilisateurRoles) {
            authorities.add(new SimpleGrantedAuthority(utilisateurRole.getRole().getNom()));
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
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
        return user.getEstActif();
    }
}
