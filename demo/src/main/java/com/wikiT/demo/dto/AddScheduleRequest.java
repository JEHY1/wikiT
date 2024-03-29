package com.wikiT.demo.dto;

import com.wikiT.demo.domain.Schedule;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
public class AddScheduleRequest {

    private Long groupId;
    private String space;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String content;
    private String status;


    public Schedule toEntity(){
        return Schedule.builder()
                .groupId(groupId)
                .space(space)
                .startAt(startAt)
                .endAt(endAt)
                .content(content)
                .status(status)
                .build();
    }
}
