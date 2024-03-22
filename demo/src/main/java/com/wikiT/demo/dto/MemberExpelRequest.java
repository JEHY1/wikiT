package com.wikiT.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberExpelRequest {

    private String userEmail;
    private Long groupId;
}
