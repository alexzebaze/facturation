package com.zebs.facturation.security;

import com.zebs.facturation.security.model.entity.Role;
import com.zebs.facturation.security.model.entity.User;
import com.zebs.facturation.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
public class SecurityController {

    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String login()  {
        return "login";
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
