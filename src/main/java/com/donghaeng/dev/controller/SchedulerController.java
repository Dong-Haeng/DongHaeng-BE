package com.donghaeng.dev.controller;

import com.donghaeng.dev.service.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
//        String identifier = "kX1PU11u97hLEB6AT2s2";
        boolean friendInfo = true;
        return schedulerService.fetchTimetable(identifier, friendInfo);
    }

    @GetMapping("/fetchAndSaveTimetable")
    public String fetchAndSaveTimetable(@RequestParam String identifier) {
//        String identifier = "kX1PU11u97hLEB6AT2s2";
        boolean friendInfo = true;
        return schedulerService.fetchAndSaveTimetable(identifier, friendInfo);
    }
}