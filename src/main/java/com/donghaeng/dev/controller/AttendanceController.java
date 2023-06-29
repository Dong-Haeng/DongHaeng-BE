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


    @PostMapping("/attendance/open")
    public ResponseEntity<String> startAttendance() {
        return new ResponseEntity<>(attendanceService.startAttendance(), HttpStatus.OK);
    }

    @PostMapping("/attendance/close")
    public ResponseEntity endAttendance() {
        attendanceService.endAttendance();
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/attendance/process")
    public ResponseEntity<Boolean> processAttendance(@RequestParam String code, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (!attendanceService.isAttendanceInProgress()) {
            return new ResponseEntity<>(attendanceService.isAttendanceInProgress(), HttpStatus.OK);
        }
        return new ResponseEntity<>(attendanceService.processAttendance(user, code), HttpStatus.OK);
    }

    @GetMapping("/attendance/sheet")
    public ResponseEntity<HashMap<String, LocalDateTime>> requestAttendanceSheet() {
        return new ResponseEntity<>(attendanceService.requestAttendanceSheet(), HttpStatus.OK);
    }
}
