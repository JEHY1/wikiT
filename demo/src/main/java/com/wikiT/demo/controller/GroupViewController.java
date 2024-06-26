package com.wikiT.demo.controller;

import com.wikiT.demo.domain.Article;
import com.wikiT.demo.domain.Group;
import com.wikiT.demo.domain.Schedule;
import com.wikiT.demo.dto.*;
import com.wikiT.demo.service.ArticleService;
import com.wikiT.demo.service.GroupService;
import com.wikiT.demo.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class GroupViewController {

    private final GroupService groupService;
    private final ArticleService articleService;
    private final ScheduleService scheduleServic;

    @GetMapping("/group/{groupId}")
    public String moveGroup(@PathVariable Long groupId, MoveRequest request, Principal principal, Model model){



        if(groupService.findByGroupMakerIdAndMember(groupId, principal.getName()) == null){
            return "error";
        }

        model.addAttribute("groupId", groupId);
        List<GroupMemberViewResponse> members = groupService.findByGroupMakerId(groupId).stream()
                .map(GroupMemberViewResponse::new)
                .toList();
        model.addAttribute("members", members);
        model.addAttribute("groupName", groupService.findGroupName(groupId));
        model.addAttribute("inviterEmail", principal.getName());
        model.addAttribute("constructor", groupService.findConstructor(groupId));
        model.addAttribute("isConstructor", groupService.findConstructor(groupId).equals(principal.getName()));
        model.addAttribute("myEmail", principal.getName());
        model.addAttribute("memberSpace", request.getMemberEmail());

        List<ArticleListViewResponse> articles = articleService.findBySpaceAndGroupId(request.getMemberEmail(), groupId)
                .stream()
                .map(ArticleListViewResponse::new)
                .toList();
        model.addAttribute("articles", articles);

        List<GroupButtonViewResponse> groups = groupService.findByMember(principal.getName())
                .stream()
                .map(GroupButtonViewResponse::new)
                .toList();
        model.addAttribute("groups", groups);

        model.addAttribute("groupSchedules", scheduleServic.findByGroupIdAndSpaceAndStatus(groupId, "master", "run"));
        model.addAttribute("personalSchedules", scheduleServic.findByGroupIdAndSpaceAndStatus(groupId, principal.getName(), "run"));

        model.addAttribute("scheduleId", request.getScheduleId());
        if(request.getScheduleId() != null) {
            Schedule schedule = scheduleServic.findById(request.getScheduleId());
            model.addAttribute("scheduleStartAt", schedule.getStartAt());
            model.addAttribute("scheduleEndAt", schedule.getEndAt());
            model.addAttribute("scheduleContent", schedule.getContent());
        }

        model.addAttribute("status", request.getStatus());

        return "groupPage";
    }

    @PostMapping("/api/accept/{messageId}")
    public String accept(@PathVariable Long messageId, MoveRequest request){
        Group group = groupService.accept(messageId);

        System.err.println(request.getMemberEmail());

        return "redirect:/group/" + group.getGroupMakerId() + "?memberEmail=" + request.getMemberEmail();
    }

    @PostMapping("/api/denied/{messageId}")
    public String denied(@PathVariable Long messageId){

        groupService.denied(messageId);
        return "redirect:/home";
    }

    @PostMapping("/api/expel")
    public String expel(MemberExpelRequest request){

        List<Article> articles = articleService.findBySpaceAndGroupId(request.getUserEmail(), request.getGroupId());
        articleService.deleteArticles(articles);
        scheduleServic.deleteSchedules(scheduleServic.findByGroupIdAndSpace(request.getGroupId(), request.getUserEmail()));
        groupService.expel(request);
        return "redirect:/group/" + request.getGroupId() + "?memberEmail=master";
    }

}
