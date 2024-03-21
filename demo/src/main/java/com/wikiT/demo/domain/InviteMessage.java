package com.wikiT.demo.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InviteMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "inviter_email", nullable = false)
    private String inviterEmail;

    @Column(name = "group_name")
    private String groupName;

    @Column(name = "group_id")
    private Long groupId;

    @Column(name = "invitee_email")
    private String inviteeEmail;

    @Builder
    public InviteMessage(String inviterEmail, String groupName, Long groupId, String inviteeEmail){
        this.inviterEmail = inviterEmail;
        this. groupName = groupName;
        this.groupId = groupId;
        this.inviteeEmail = inviteeEmail;
    }

}
