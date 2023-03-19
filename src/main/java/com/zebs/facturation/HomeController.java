package com.zebs.facturation;

import com.zebs.facturation.security.model.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")

    public String home(Authentication auth){
        System.out.println(auth.getPrincipal());
        return "home";
    }
}
