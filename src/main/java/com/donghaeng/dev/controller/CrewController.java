package com.donghaeng.dev.controller;

import com.donghaeng.dev.domain.Division;
import com.donghaeng.dev.domain.User;
import com.donghaeng.dev.dto.CrewListResponseDto;
import com.donghaeng.dev.dto.CrewRegisterRequestDto;
import com.donghaeng.dev.service.CrewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class CrewController {

    private final CrewService crewService;

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @PostMapping("/crews")
    public ResponseEntity<Long> register(@RequestBody CrewRegisterRequestDto crewRegisterRequestDto, HttpSession session) {
        User user = (User) session.getAttribute("user");

        return new ResponseEntity<>(crewService.register(user, crewRegisterRequestDto), HttpStatus.OK);
    }

    @GetMapping("/crews")
    public ResponseEntity<List<CrewListResponseDto>> get(@RequestParam Optional<Division> division, @RequestParam Optional<Boolean> isRecruiting, HttpSession session) {

        User user = (User) session.getAttribute("user");
        log.info("user name = {}, email = {}", user.getName(), user.getEmail());
        return new ResponseEntity<>(crewService.findAllDesc(user.getUniversity(), division, isRecruiting), HttpStatus.OK);
    }
}
