package com.wikiT.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class GroupViewController {

    @GetMapping("/group/{groupId}")
    public String moveGroup(@PathVariable Long groupId, Model model){

        model.addAttribute("groupId", groupId);
        return "groupPage";
    }
}
