package com.ged;

import com.ged.dao.security.UtilisateurDao;
import com.ged.entity.security.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class CurrentAppInfo {
    @Autowired
    private static UtilisateurDao utilisateurDao;
    public static Utilisateur currentUser;
    public static String username;

    public static void setCurrentUser(Principal principal) {
        String username = principal.getName();
        currentUser = utilisateurDao.findByUsernameAndEstActif(username, true).orElse(null);
    }
}
