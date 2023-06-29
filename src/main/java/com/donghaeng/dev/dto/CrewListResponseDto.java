package com.donghaeng.dev.dto;

import com.donghaeng.dev.domain.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class CrewListResponseDto {

    private Long id;
    private String name;
    private String description;
    private boolean isRecruiting;
    private University university;
    private Division division;
    private String userName;

    public CrewListResponseDto(Crew crew) {
        this.id = crew.getId();
        this.name = crew.getName();
        this.description = crew.getDescription();
        this.isRecruiting = crew.isRecruiting();
        this.university = crew.getUniversity();
        this.division = crew.getDivision();
        this.userName = crew.getUser().getName();
    }
}
