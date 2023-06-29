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
    public ResponseEntity<Long> signUp(@RequestBody UserSignUpDto userSignUpDto) {
        return new ResponseEntity<>(userService.signUp(userSignUpDto).getId(), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResDto> login(@RequestBody UserLoginDto userLoginDto, HttpSession session) {
        UserResDto user = userService.login(userLoginDto,session);

        if (user != null) {
            // 로그인 성공 시
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            // 로그인 실패 시
            return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
        }
    }


}
