package com.ilci.UserController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PromoController {

    @GetMapping( "/promos" )
    public String index() {
        return "promo/index";
    }

}
