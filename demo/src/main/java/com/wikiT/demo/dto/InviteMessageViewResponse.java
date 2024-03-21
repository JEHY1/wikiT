package com.wikiT.demo.dto;

import com.wikiT.demo.domain.InviteMessage;
import com.wikiT.demo.repository.InviteMessageRepository;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InviteMessageViewResponse {

    private Long groupId;
    private String groupName;
    private String inviterEmail;

    public InviteMessageViewResponse(InviteMessage inviteMessage){
        this.groupId = inviteMessage.getGroupId();
        this.groupName = inviteMessage.getGroupName();
        this.inviterEmail = inviteMessage.getInviterEmail();
    }
}
