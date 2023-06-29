package com.donghaeng.dev.service;

import com.donghaeng.dev.domain.Status;
import com.donghaeng.dev.domain.User;
import com.donghaeng.dev.dto.UserLoginDto;
import com.donghaeng.dev.dto.UserResDto;
import com.donghaeng.dev.dto.UserSignUpDto;
import com.donghaeng.dev.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResDto signUp(UserSignUpDto userSignUpDto) {
        Optional<User> user = userRepository.findUserByEmail(userSignUpDto.getEmail());
        log.info("회원가입 서비스 실행");

        if (user.isEmpty()) {
            User newUser = User.builder()
                    .name(userSignUpDto.getName())
                    .email(userSignUpDto.getEmail())
                    .password(userSignUpDto.getPassword())
                    .phone(userSignUpDto.getPhone())
                    .status(Status.PENDING)
                    .university(userSignUpDto.getUniversity())
                    .build();

            userRepository.save(newUser);

            return UserResDto.builder()
                    .name(newUser.getName())
                    .email(newUser.getEmail())
                    .password(newUser.getPassword())
                    .phone(newUser.getPhone())
                    .status(newUser.getStatus())
                    .university(newUser.getUniversity())
                    .build();
        } else {
            throw new IllegalArgumentException("이미 존재하는 회원");
        }
    }

    public UserResDto login(UserLoginDto userLoginDto, HttpSession session) {
        Optional<User> userOptional = userRepository.findUserByEmail(userLoginDto.getEmail());

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            if (user.getPassword().equals(userLoginDto.getPassword())) {
                if (user.getCrews().isEmpty()) {
                    log.info("일반회원");
                    session.setAttribute("user", user);
                    return UserResDto.builder()
                            .id(user.getId())
                            .name(user.getName())
                            .email(user.getEmail())
                            .password(user.getPassword())
                            .phone(user.getPhone())
                            .status(user.getStatus())
                            .university(user.getUniversity())
                            .isPresident(false)
                            .build();
                } else {
                    log.info("동아리 임원");
                    session.setAttribute("user", user);
                    return UserResDto.builder()
                            .id(user.getId())
                            .name(user.getName())
                            .email(user.getEmail())
                            .password(user.getPassword())
                            .phone(user.getPhone())
                            .status(user.getStatus())
                            .university(user.getUniversity())
                            .isPresident(true)
                            .build();
                }
            }
        }
        return null;
    }



}
