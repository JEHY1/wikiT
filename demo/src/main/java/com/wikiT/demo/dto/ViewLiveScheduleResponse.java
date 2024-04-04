package com.wikiT.demo.dto;


import com.wikiT.demo.domain.Schedule;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ViewLiveScheduleResponse {

    private Long scheduleId;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String content;
    private Long groupId;
    private String groupName;
    private String space;

    public ViewLiveScheduleResponse(Schedule schedule){
        this.scheduleId = schedule.getId();
        this.startAt = schedule.getStartAt();
        this.endAt = schedule.getEndAt();
        this.content = schedule.getContent();
        this.groupId = schedule.getGroupId();
        this.space = schedule.getSpace();
    }

    public void submitGroupName(String groupName){
        this.groupName = groupName;
    }
}
