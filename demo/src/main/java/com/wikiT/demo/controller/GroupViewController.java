package com.wikiT.demo.controller;

import com.wikiT.demo.dto.GroupMemberViewResponse;
import com.wikiT.demo.dto.InviteRequest;
import com.wikiT.demo.service.GroupService;
import com.wikiT.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class GroupViewController {

    private final GroupService groupService;

    @GetMapping("/group/{groupId}")
    public String moveGroup(@PathVariable Long groupId, Principal principal, Model model){

        model.addAttribute("groupId", groupId);
        List<GroupMemberViewResponse> members = groupService.findByGroupMakerId(groupId).stream()
                .map(GroupMemberViewResponse::new)
                .toList();
        model.addAttribute("members", members);
        model.addAttribute("groupName", groupService.findGroupName(groupId));
        model.addAttribute("inviterEmail", principal.getName());
        return "groupPage";
    }

}
