package com.donghaeng.dev.controller;

import com.donghaeng.dev.domain.User;
import com.donghaeng.dev.dto.UserLoginDto;
import com.donghaeng.dev.dto.UserResDto;
import com.donghaeng.dev.dto.UserSignUpDto;
import com.donghaeng.dev.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@Slf4j
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signUp")
    public ResponseEntity<User> signUp(@RequestBody UserSignUpDto userSignUpDto){
        userService.signUp(userSignUpDto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginDto userLoginDto, HttpSession session) {
        UserResDto user = userService.login(userLoginDto,session);

        if (user != null) {
            // 로그인 성공 시
            return ResponseEntity.ok("로그인 성공");
        } else {
            // 로그인 실패 시
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패");
        }
    }


}
