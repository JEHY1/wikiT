package com.wikiT.demo.dto;

import com.wikiT.demo.domain.InviteMessage;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InviteRequest {

    private String groupName;
    private Long groupId;
    private String inviterEmail;
    private String inviteeEmail;

    public InviteMessage toEntity(){
        return InviteMessage.builder()
                .groupName(groupName)
                .groupId(groupId)
                .inviterEmail(inviterEmail)
                .inviteeEmail(inviteeEmail)
                .build();
    }
}
