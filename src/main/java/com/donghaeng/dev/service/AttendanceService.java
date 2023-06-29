package com.donghaeng.dev.service;

import com.donghaeng.dev.domain.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

@NoArgsConstructor
@Service
public class AttendanceService {

    private static boolean isProgressing = false;
    private String attendanceCode;
    private HashMap<String, LocalDateTime> attendanceMap;

    public boolean isAttendanceInProgress() {
        if (isProgressing) return true;
        return false;
    }
}
