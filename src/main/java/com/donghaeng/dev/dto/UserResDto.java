package com.donghaeng.dev.dto;

import com.donghaeng.dev.domain.Status;
import com.donghaeng.dev.domain.University;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class UserResDto {

    private Long id;
    private String name;
    private String email;
    private String password;
    private String phone;
    private Status status;
    private University university;
    private boolean isPresident;

    public UserResDto(Long id, String name, String email, String password, String phone, Status status, University university, boolean isPresident) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.status = status;
        this.university = university;
        this.isPresident = isPresident;
    }
}
