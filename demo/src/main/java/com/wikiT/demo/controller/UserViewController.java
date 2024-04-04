package com.wikiT.demo.controller;


import com.wikiT.demo.domain.Schedule;
import com.wikiT.demo.dto.GroupButtonViewResponse;
import com.wikiT.demo.dto.HomeViewRequest;
import com.wikiT.demo.dto.InviteMessageViewResponse;
import com.wikiT.demo.dto.ViewLiveScheduleResponse;
import com.wikiT.demo.service.GroupService;
import com.wikiT.demo.service.ScheduleService;
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
    private final ScheduleService scheduleService;


    @GetMapping("/login")
    public String login(){return "login";}

    @GetMapping("/signup")
    public String signup(@RequestParam(required = false) Long re, Model model){
        model.addAttribute("re", re);
        return "signup";
    }

    @GetMapping("/home")
    public String home(HomeViewRequest request, Principal principal, Model model){

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


        List<ViewLiveScheduleResponse> allSchedules = scheduleService.findMySchedules(principal.getName(), groupService.findByMember(principal.getName()))
                .stream().map(ViewLiveScheduleResponse::new)
                .toList();

        allSchedules.forEach(schedule -> {
            schedule.submitGroupName(groupService.findGroupMakerByGroupId(schedule.getGroupId()).getGroupName());
        });

        model.addAttribute("allSchedules", allSchedules);

        model.addAttribute("scheduleId", request.getScheduleId());

        if(request.getScheduleId() != null) {
            Schedule schedule = scheduleService.findById(request.getScheduleId());

            model.addAttribute("scheduleStartAt", schedule.getStartAt());
            model.addAttribute("scheduleEndAt", schedule.getEndAt());
            model.addAttribute("scheduleContent", schedule.getContent());
        }

        //
        model.addAttribute("doneSchedules", scheduleService.findMyScheduleOthers(principal.getName(), groupService.findByMember(principal.getName())));
        model.addAttribute("isViewAll", request.getStatus());




        return "home";
    }
}
