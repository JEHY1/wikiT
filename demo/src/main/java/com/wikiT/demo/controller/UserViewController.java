package com.wikiT.demo.controller;


import com.wikiT.demo.dto.GroupButtonViewResponse;
import com.wikiT.demo.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserViewController {

    private final GroupService groupService;

    @GetMapping("/login")
    public String login(){return "login";}

    @GetMapping("/signup")
    public String signup(){return "signup";}

    @GetMapping("/home")
    public String home(Principal principal, Model model){

        model.addAttribute("email", principal.getName());
        List<GroupButtonViewResponse> groups = groupService.findByMember(principal.getName())
                .stream()
                .map(GroupButtonViewResponse::new)
                .toList();
        model.addAttribute("groups", groups);

        return "home";
    }
}
