package com.zebs.facturation.security;

import com.zebs.facturation.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

    UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) authentication;
    String name = auth.getName();
    String password = auth.getCredentials().toString();

    UserDetails user = userService.loadUserByUsername(name);
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    if (user == null || !encoder.matches(password, user.getPassword())) {
        throw new BadCredentialsException("Username/Password does not match for " + auth.getPrincipal());
    }

        return new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
