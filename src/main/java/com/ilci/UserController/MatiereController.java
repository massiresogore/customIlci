package com.ilci.UserController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MatiereController {

    @GetMapping( "/matieres" )
    public String index() {
        return "matieres/index";
    }

}
