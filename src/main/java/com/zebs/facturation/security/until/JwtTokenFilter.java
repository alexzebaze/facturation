package com.zebs.facturation.security.until;

import com.zebs.facturation.security.service.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.apache.logging.log4j.util.Strings.isEmpty;

@Component
    public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    UserService userService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String tokenHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        System.out.println("token: "+tokenHeader);
        if(tokenHeader != null && tokenHeader.startsWith("Bearer ")){
            String token = tokenHeader.substring(7);
            String username = "";
            try {
                username = jwtTokenUtil.getUsernameFromToken(token);
            } catch (MalformedJwtException e) {
                System.out.println("Unable to get JWT Token");
                throw new MalformedJwtException(e.getMessage());
            } catch (ExpiredJwtException e) {
                System.out.println("JWT Token has expired");
                throw new MalformedJwtException(e.getMessage());
            }

            if (!isEmpty(username)) {

                try {
                    UserDetails userDetails = this.userService.loadUserByUsername(username);
                    // if token is valid configure Spring Security to manually set authentication
                    if (userDetails != null && jwtTokenUtil.validateToken(token, userDetails)) {

                        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities());
                        usernamePasswordAuthenticationToken
                                .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    }
                }
                catch (UsernameNotFoundException e){
                    System.out.println("UsernameNotFoundException");
                    throw new UsernameNotFoundException(e.getMessage());
                }
                catch (AccessDeniedException e){
                    System.out.println("AccessDeniedException");
                    throw new AccessDeniedException(e.getMessage());
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}