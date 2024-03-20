package com.wikiT.demo.dto;

import com.wikiT.demo.domain.Group;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GroupButtonViewResponse {

    private String GroupName;
    private Long GroupId;

    public GroupButtonViewResponse(Group group){
        this.GroupName = group.getGroupName();
        this.GroupId = group.getId();
    }
}
