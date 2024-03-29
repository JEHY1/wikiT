package com.wikiT.demo.domain;


import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "schedule_tb")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "group_id")
    private Long groupId;

    @Column(name = "space")
    private String space;

    @Column(name = "startAt")
    private LocalDateTime startAt;

    @Column(name = "endAt")
    private LocalDateTime endAt;

    @Column(name = "content")
    private String content;

    @Column(name = "status")
    private String status;

    @Builder
    public Schedule(Long groupId, String space, LocalDateTime startAt, LocalDateTime endAt, String content, String status){

        this.groupId = groupId;
        this.space = space;
        this.startAt = startAt;
        this.endAt = endAt;
        this.content = content;
        this. status = status;
    }
}
