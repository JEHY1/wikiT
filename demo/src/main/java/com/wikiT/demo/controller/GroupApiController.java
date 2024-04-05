package com.wikiT.demo.controller;

import com.wikiT.demo.domain.Article;
import com.wikiT.demo.domain.Group;
import com.wikiT.demo.domain.GroupMaker;
import com.wikiT.demo.domain.InviteMessage;
import com.wikiT.demo.dto.AddGroupRequest;
import com.wikiT.demo.dto.ExitRequest;
import com.wikiT.demo.dto.GroupRemoveRequest;
import com.wikiT.demo.dto.InviteRequest;
import com.wikiT.demo.service.ArticleService;
import com.wikiT.demo.service.GroupService;
import com.wikiT.demo.service.ScheduleService;
import com.wikiT.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class GroupApiController {

    private final GroupService groupService;
    private final UserService userService;
    private final ArticleService articleService;
    private final ScheduleService scheduleService;

    @PostMapping("/api/group")
    public ResponseEntity<GroupMaker> submitGroup(@RequestBody AddGroupRequest request){

        return ResponseEntity.status(HttpStatus.CREATED)
                        .body(groupService.submit(request));
    }

    @PostMapping("/api/invite")
    public ResponseEntity<InviteMessage> invite(@RequestBody InviteRequest request){

        InviteMessage inviteMessage = groupService.invite(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(inviteMessage);
    }

    @DeleteMapping("/api/exit")
    public ResponseEntity<Void> exit(@RequestBody ExitRequest request, Principal principal){

        groupService.exit(request.getGroupId(), principal.getName());
        List<Article> articles = articleService.findBySpaceAndGroupId(principal.getName(), request.getGroupId());
        articleService.deleteArticles(articles);
        return ResponseEntity.ok()
                .build();
    }

    @DeleteMapping("/api/remove")
    public ResponseEntity<Void> exit(@RequestBody GroupRemoveRequest request){

        articleService.deleteArticles(articleService.findByGroupId(request.getGroupId()));
        scheduleService.deleteSchedules(scheduleService.findByGroupId(request.getGroupId()));
        groupService.groupRemove(request.getGroupId());


        return ResponseEntity.ok()
                .build();
    }

}
