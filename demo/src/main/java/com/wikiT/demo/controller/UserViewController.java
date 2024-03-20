package com.wikiT.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class UserViewController {


    @GetMapping("/login")
    public String login(){return "login";}

    @GetMapping("/signup")
    public String signup(){return "signup";}

    @GetMapping("/home")
    public String home(Principal principal, Model model){

        model.addAttribute("email", principal.getName());
        return "home";
    }
}
