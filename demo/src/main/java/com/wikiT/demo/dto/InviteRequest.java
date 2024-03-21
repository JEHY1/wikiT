package com.wikiT.demo.dto;

import com.wikiT.demo.domain.Group;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InviteRequest {

    private String groupName;
    private Long groupMakerId;
    private String email;

    public Group toEntity(){
        return Group.builder()
                .groupName(groupName)
                .groupMakerId(groupMakerId)
                .member(email)
                .build();
    }
}
