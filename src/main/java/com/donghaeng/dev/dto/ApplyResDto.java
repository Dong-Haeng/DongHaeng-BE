package com.donghaeng.dev.dto;

import com.donghaeng.dev.domain.Crew;
import com.donghaeng.dev.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ApplyResDto {
    private Long crewId;
    private Long userId;
    private Long id;



    public ApplyResDto(Long id, Long userId, Long crewId) {
        this.id = id;
        this.userId = userId;
        this.crewId = crewId;
    }

}
