package com.donghaeng.dev.controller;

import com.donghaeng.dev.dto.PostSchedulerDto;
import com.donghaeng.dev.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;
    private final SchedulerController schedulerController;

    @GetMapping("/member/create")
    public List<PostSchedulerDto> create(Long groupId, String name, String identifier) {
        List<PostSchedulerDto> postSchedulerDtos = schedulerController.fetchAndSaveTimetable(identifier);
        memberService.create(groupId, name, identifier);
        return postSchedulerDtos;
    }

}
