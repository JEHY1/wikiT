package com.wikiT.demo.controller;

import com.wikiT.demo.domain.Schedule;
import com.wikiT.demo.dto.AddScheduleRequest;
import com.wikiT.demo.dto.DeleteScheduleRequest;
import com.wikiT.demo.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ScheduleApiController {

    private final ScheduleService scheduleService;

    @PostMapping("/api/schedule")
    public ResponseEntity<Schedule> submit(@RequestBody AddScheduleRequest request){

        System.err.println(request.getSpace());

        Schedule schedule = scheduleService.submit(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(schedule);
    }

    @DeleteMapping("/api/schedule")
    public ResponseEntity<Void> delete(@RequestBody DeleteScheduleRequest request){

        scheduleService.delete(request.getScheduleId());
        return ResponseEntity.ok()
                .build();
    }
}
