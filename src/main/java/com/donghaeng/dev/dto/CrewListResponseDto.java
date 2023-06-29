package com.donghaeng.dev.dto;

import com.donghaeng.dev.domain.Crew;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class CrewListResponseDto {

    private List<Crew> crews;
    public CrewListResponseDto(List<Crew> crews) {
        this.crews = crews;
    }
}
