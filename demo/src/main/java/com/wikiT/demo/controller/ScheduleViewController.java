package com.wikiT.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ScheduleViewController {

    @GetMapping("/schedule")
    public String schedule(){
        return "scheduleTest";
    }
}
