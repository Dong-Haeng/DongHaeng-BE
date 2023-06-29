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
    private boolean isPresident;

    public UserResDto(Long id, boolean isPresident) {
        this.id = id;
        this.isPresident = isPresident;
    }
}
