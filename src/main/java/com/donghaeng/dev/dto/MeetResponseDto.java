package com.donghaeng.dev.dto;

import com.donghaeng.dev.domain.Meet;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class MeetResponseDto {
    Long id;
    String name;

    public MeetResponseDto(Meet group) {
        this.id = group.getId();
        this.name = group.getName();
    }
}
