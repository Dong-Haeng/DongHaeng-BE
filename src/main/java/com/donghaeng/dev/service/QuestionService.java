package com.donghaeng.dev.service;

import com.donghaeng.dev.domain.Crew;
import com.donghaeng.dev.domain.Question;
import com.donghaeng.dev.domain.User;
import com.donghaeng.dev.dto.QuestionDto;
import com.donghaeng.dev.dto.QuestionResDto;
import com.donghaeng.dev.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final HttpSession session;
    public Long registerQuestion(List<QuestionDto> questionDtoList,Crew crew) {

        User user = (User) session.getAttribute("user");
        if (user != null && !user.getCrews().isEmpty()) {
            log.info("가입 질문 등록 세션입니다");

            List<Question> questions = new ArrayList<>();
            for (QuestionDto questionDto : questionDtoList) {
                Question question = new Question();
                question.setContent(questionDto.getContent());
                question.setCrew(crew);
                questions.add(question);
            }
            questionRepository.saveAll(questions);
        }
        return null;
    }

    public List<QuestionResDto> getQuestionsByCrew(Crew crew) {
        return questionRepository
                .findQuestionByCrew(crew)
                .stream()
                .map(QuestionResDto::new)
                .collect(Collectors.toList());
    }

}
