package com.zebs.facturation;

import com.zebs.facturation.tva.model.entity.Taxe;
import com.zebs.facturation.tva.service.TaxeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @Autowired
    TaxeService taxeService;

    @GetMapping("/login")
    public String login()  {
        return "login";
    }

}
