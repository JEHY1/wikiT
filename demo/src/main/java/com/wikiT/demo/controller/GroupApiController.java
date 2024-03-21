package com.wikiT.demo.controller;

import com.wikiT.demo.domain.Group;
import com.wikiT.demo.domain.GroupMaker;
import com.wikiT.demo.domain.InviteMessage;
import com.wikiT.demo.dto.AddGroupRequest;
import com.wikiT.demo.dto.InviteRequest;
import com.wikiT.demo.service.GroupService;
import com.wikiT.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class GroupApiController {

    private final GroupService groupService;
    private final UserService userService;

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





}
