package com.wikiT.demo.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "groups")
@NoArgsConstructor
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "group_name", nullable = false)
    private String groupName;

    @Column(name = "member", nullable = false)
    private String member;

    @Builder
    public Group(String groupName, String member){
        this.groupName = groupName;
        this.member = member;
    }
}
