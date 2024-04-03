package com.wikiT.demo.controller;

import com.wikiT.demo.domain.Schedule;
import com.wikiT.demo.dto.AddScheduleRequest;
import com.wikiT.demo.dto.CompleteScheduleRequest;
import com.wikiT.demo.dto.DeleteScheduleRequest;
import com.wikiT.demo.dto.UpdateScheduleRequest;
import com.wikiT.demo.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ScheduleApiController {

    private final ScheduleService scheduleService;

    @PostMapping("/api/schedule")
    public ResponseEntity<Schedule> submit(@RequestBody AddScheduleRequest request){

        Schedule schedule = scheduleService.submit(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(schedule);
    }

    @PutMapping("/api/scheduleComplete")
    public ResponseEntity<Schedule> scheduleComplete(@RequestBody CompleteScheduleRequest request){

        System.err.println("schedule Id :  "+ request.getScheduleId());

        return ResponseEntity.ok()
                .body(scheduleService.update(request.getScheduleId()));
    }

    @DeleteMapping("/api/schedule")
    public ResponseEntity<Void> delete(@RequestBody DeleteScheduleRequest request){

        scheduleService.delete(request.getScheduleId());
        return ResponseEntity.ok()
                .build();
    }

    @PutMapping("/api/schedule")
    public ResponseEntity<Schedule> update(@RequestBody UpdateScheduleRequest request){

        return ResponseEntity.ok()
                .body(scheduleService.update(request));
    }



}
