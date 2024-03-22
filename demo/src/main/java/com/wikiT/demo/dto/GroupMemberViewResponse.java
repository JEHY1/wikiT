package com.wikiT.demo.dto;

import com.wikiT.demo.domain.Group;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupMemberViewResponse {

    private String memberName;
    private Long memberId;

    public GroupMemberViewResponse(Group group){
        this.memberName = group.getMember();
    }

}
