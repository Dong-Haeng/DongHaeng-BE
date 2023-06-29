package com.donghaeng.dev.dto;

import com.donghaeng.dev.domain.University;
import com.donghaeng.dev.domain.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignUpDto {

    private String name;
    private String email;
    private String password;
    private String phone;
    private University university;

}


