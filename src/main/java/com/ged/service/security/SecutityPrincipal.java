package com.ged.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;

public class SecutityPrincipal {

}

/*@Service
public class SecutityPrincipal {
    private final Authentication principal = SecurityContextHolder.getContext().getAuthentication();
    private static AppUserDetailsService userService;

    @Autowired
    private SecutityPrincipal(AppUserDetailsService userService)
    {
        SecutityPrincipal.userService = userService;
    }

    public static SecutityPrincipal getInstance() {
        return new SecutityPrincipal(userService);
    }

    public UserDetails getLoggedInPrincipal() {
        if(principal != null)
        {
            UserDetails loggedInPrincipal = (UserDetails) principal.getPrincipal();
            return userService.loadUserByUsername(loggedInPrincipal.getUsername());
        }
        return null;
    }

    public Collection<?> getLoggedInPrincipalAuthorities() {
        return ((UserDetails) principal.getPrincipal()).getAuthorities();
    }
}*/
