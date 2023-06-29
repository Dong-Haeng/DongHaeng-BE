package com.donghaeng.dev.dto;

import com.donghaeng.dev.domain.Question;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class QuestionResDto {
    private Long id;
    private String content;

    public QuestionResDto(Question question) {
        this.id = question.getId();
        this.content = question.getContent();
    }
}
