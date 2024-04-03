package com.wikiT.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UpdateScheduleRequest {

    private Long scheduleId;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String content;
}
