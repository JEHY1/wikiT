package com.wikiT.demo.controller;

import com.wikiT.demo.domain.Schedule;
import com.wikiT.demo.dto.AddScheduleRequest;
import com.wikiT.demo.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
}
