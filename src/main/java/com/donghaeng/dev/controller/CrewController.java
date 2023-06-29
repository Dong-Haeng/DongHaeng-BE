package com.donghaeng.dev.controller;

import com.donghaeng.dev.domain.Division;
import com.donghaeng.dev.domain.User;
import com.donghaeng.dev.dto.CrewListResponseDto;
import com.donghaeng.dev.dto.CrewRegisterRequestDto;
import com.donghaeng.dev.repository.UserRepository;
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
    private final UserRepository userRepository;

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @PostMapping("/crews")

    public ResponseEntity<Long> register(@RequestBody CrewRegisterRequestDto crewRegisterDto, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return new ResponseEntity<>(crewService.register(user, crewRegisterDto), HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/crews")
    public ResponseEntity<CrewListResponseDto> get(@RequestParam Optional<Division> division, @RequestParam Optional<Boolean> isRecruiting, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return new ResponseEntity<>(crewService.findAllDesc(user.getUniversity(), Optional.ofNullable(division.orElse(null)), Optional.ofNullable(isRecruiting.orElse(null))), HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    
    }
}
