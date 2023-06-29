package com.donghaeng.dev.controller;

import com.donghaeng.dev.domain.User;
import com.donghaeng.dev.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.HashMap;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AttendanceController {

    private final AttendanceService attendanceService;

    @GetMapping("/attendance/status")
    public ResponseEntity<Boolean> isAttendanceInProgress() {
        return new ResponseEntity<>(attendanceService.isAttendanceInProgress(), HttpStatus.OK);
    }
}
