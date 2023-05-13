package com.zebs.facturation.security.controller;

import com.zebs.facturation.security.model.entity.JwtRequestModel;
import com.zebs.facturation.security.model.entity.Role;
import com.zebs.facturation.security.model.entity.User;
import com.zebs.facturation.security.service.UserService;
import com.zebs.facturation.security.until.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

@Controller
public class SecurityController {

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @GetMapping("/login")
    public String login()  {
        return "login";
    }

    @GetMapping("/authenticate")
    public String authenticate()  {
        return "authenticate";
    }

    private Authentication auth(String username, String password) throws Exception {
        try {
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @PostMapping("/authenticates")
    public ResponseEntity<?> authenticatePost(JwtRequestModel jwtRequest)  {
        try {
            UserDetails user = (User)this.auth(jwtRequest.getUsername(), jwtRequest.getPassword()).getPrincipal();

            Map<String, String> result = new HashMap<>();
            result.put("token", jwtTokenUtil.generateToken(user));

            return ResponseEntity.ok()
                    .header(
                            HttpHeaders.AUTHORIZATION,
                            jwtTokenUtil.generateToken(user)
                    )
                    .body(result);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticatePostApi(@RequestBody JwtRequestModel jwtRequest)  {
        try {
            UserDetails user = (User)this.auth(jwtRequest.getUsername(), jwtRequest.getPassword()).getPrincipal();

            String token = jwtTokenUtil.generateToken(user);
            Date expireAt = jwtTokenUtil.getExpirationDateFromToken(token);
            System.out.println(expireAt.toString());
            String expireAtAsSecond = String.valueOf(expireAt.getTime()/1000);

            Map<String, String> result = new HashMap<>();
            result.put("access_token", token);
            result.put("expires_at", expireAtAsSecond);

            return ResponseEntity.ok()
                    .header(
                            HttpHeaders.AUTHORIZATION,
                            jwtTokenUtil.generateToken(user)
                    )
                    .body(result);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/register")
    public String register(Model model)  {
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register-processing")
    public String registerProcessing(@ModelAttribute("user") User user){
        System.out.println(user);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String passwordEncoded = encoder.encode(user.getPassword());
        user.setPassword(passwordEncoded);
        user.setUsername(user.getEmail());
        Set<Role> roles = new HashSet();
        roles.add(new Role("ROLE_ADMIN"));
        user.setRoles(roles);
        userService.save(user);

        System.out.println(user);

        return "register";
    }

}
