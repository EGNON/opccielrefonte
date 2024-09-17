package com.ged.service.security;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.security.UtilisateurDao;
import com.ged.entity.security.Utilisateur;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AppUserDetailsService implements UserDetailsService {
//    private static final Logger LOG = LoggerFactory.getLogger(AppUserDetailsService.class);
    private final UtilisateurDao utilisateurDao;

    public AppUserDetailsService(UtilisateurDao utilisateurDao) {
        this.utilisateurDao = utilisateurDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return utilisateurDao.findByUsernameAndEstActif(username, true)
                .orElseThrow(() -> new EntityNotFoundException(Utilisateur.class, username));
    }

//    private Collection<GrantedAuthority> mapRolesToAuthorities(Set<Role> roles) {
//        return roles.stream().map(role->new SimpleGrantedAuthority(role.getNom())).collect(Collectors.toList());
//    }
}
