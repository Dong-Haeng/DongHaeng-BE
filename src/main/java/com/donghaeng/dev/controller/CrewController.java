package com.donghaeng.dev.controller;

import com.donghaeng.dev.domain.User;
import com.donghaeng.dev.dto.CrewRegisterRequestDto;
import com.donghaeng.dev.service.CrewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CrewController {

    private final CrewService crewService;
    HttpSession session;

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @PostMapping("/crews")
    public ResponseEntity<Long> register(@RequestBody CrewRegisterRequestDto crewRegisterDto) {
        User user = (User) session.getAttribute("user");
        return new ResponseEntity<>(crewService.register(user, crewRegisterDto), HttpStatus.OK);
    }
}