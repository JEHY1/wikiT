package com.wikiT.demo.controller;


import com.wikiT.demo.dto.GroupButtonViewResponse;
import com.wikiT.demo.dto.InviteMessageViewResponse;
import com.wikiT.demo.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserViewController {

    private final GroupService groupService;


    @GetMapping("/login")
    public String login(){return "login";}

    @GetMapping("/signup")
    public String signup(@RequestParam(required = false) Long re, Model model){
        model.addAttribute("re", re);
        return "signup";
    }

    @GetMapping("/home")
    public String home(Principal principal, Model model){

        model.addAttribute("email", principal.getName());
        List<GroupButtonViewResponse> groups = groupService.findByMember(principal.getName())
                .stream()
                .map(GroupButtonViewResponse::new)
                .toList();
        model.addAttribute("groups", groups);

        List<InviteMessageViewResponse> messages = groupService.findMessage(principal.getName())
                .stream()
                .map(InviteMessageViewResponse::new)
                .toList();

        model.addAttribute("messages", messages);
        return "home";
    }
}
