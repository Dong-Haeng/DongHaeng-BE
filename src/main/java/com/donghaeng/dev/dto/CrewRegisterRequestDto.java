package com.donghaeng.dev.dto;

import com.donghaeng.dev.domain.Division;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CrewRegisterRequestDto {

    private String name;
    private String description;
    private Division division;
    private boolean isRecruiting;
}
