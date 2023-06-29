package com.donghaeng.dev.controller;

import com.donghaeng.dev.dto.MeetResponseDto;
import com.donghaeng.dev.service.MeetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class MeetController {

    private final MeetService meetService;

    @PostMapping("/meets/create")
    public Long create(@RequestParam String name) {
        return meetService.create(name);
    }

    @GetMapping("/meets")
    public List<MeetResponseDto> get() {
        return meetService.get();
    }
}
