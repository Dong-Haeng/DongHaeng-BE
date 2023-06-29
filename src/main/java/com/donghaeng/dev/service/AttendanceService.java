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

    public String startAttendance() {
        attendanceCode = generateCode();
        isProgressing = true;
        attendanceMap = new HashMap<>();
        return attendanceCode;
    }
    public void endAttendance() {
        isProgressing = false;
        attendanceMap.clear();
    }

    public String generateCode() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 6;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }
}
