package com.wikiT.demo.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class GroupMaker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "group_name", nullable = false)
    private String groupName;

    @Column(name = "constructor", nullable = false)
    private String constructor;

    @Builder
    public GroupMaker(String groupName, String constructor){
        this.groupName = groupName;
        this.constructor = constructor;
    }
}
