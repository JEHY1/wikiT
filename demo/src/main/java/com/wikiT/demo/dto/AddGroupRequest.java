package com.wikiT.demo.dto;

import com.wikiT.demo.domain.Group;
import com.wikiT.demo.domain.GroupMaker;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddGroupRequest {

    private String groupName;
    private String constructor;

    public GroupMaker toEntity(){
        return GroupMaker.builder()
                .groupName(groupName)
                .constructor(constructor)
                .build();
    }

    public Group toEntityGroup(){
        return Group.builder()
                .groupName(groupName)
                .member(constructor)
                .build();
    }

}
