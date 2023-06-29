package com.donghaeng.dev.service;

import com.donghaeng.dev.domain.Crew;
import com.donghaeng.dev.domain.Question;
import com.donghaeng.dev.domain.User;
import com.donghaeng.dev.dto.AnswerDto;
import com.donghaeng.dev.dto.QuestionDto;
import com.donghaeng.dev.repository.CrewRepository;
import com.donghaeng.dev.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AnswerService {
    private final HttpSession session;
    private final QuestionRepository questionRepository;
    private final CrewRepository crewRepository;

    public Long registerAnswer(AnswerDto answerDto) {

        User user = (User) session.getAttribute("user");
        Crew crew = crewRepository.findCrewByUser(user);
        if (user != null) {

            log.info("질문 답변 세션입니다.");

            List<Question> questions = questionRepository.findQuestionByCrew(crew);
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
}
