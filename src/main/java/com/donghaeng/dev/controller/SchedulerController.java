package com.donghaeng.dev.controller;

import com.donghaeng.dev.dto.PostSchedulerDto;
import com.donghaeng.dev.service.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/scheduler")
public class SchedulerController {
    private final SchedulerService schedulerService;

    @Autowired
    public SchedulerController(SchedulerService schedulerService) {
        this.schedulerService = schedulerService;
    }

    @GetMapping("/fetchTimetable")
    public String fetchTimetable(@RequestParam String identifier) {
        boolean friendInfo = true;
        return schedulerService.fetchTimetable(identifier, friendInfo);
    }

    @GetMapping("/fetchAndSaveTimetable")
    public List<PostSchedulerDto> fetchAndSaveTimetable(@RequestParam String identifier) {
        boolean friendInfo = true;
        return schedulerService.fetchAndSaveTimetable(identifier, friendInfo);
    }
}