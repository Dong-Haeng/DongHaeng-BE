package com.donghaeng.dev.service;

import com.donghaeng.dev.domain.Answer;
import com.donghaeng.dev.domain.Crew;
import com.donghaeng.dev.domain.Question;
import com.donghaeng.dev.domain.User;
import com.donghaeng.dev.dto.AnswerDto;
import com.donghaeng.dev.dto.QuestionDto;
import com.donghaeng.dev.dto.QuestionResDto;
import com.donghaeng.dev.repository.AnswerRepository;
import com.donghaeng.dev.repository.ApplyRepository;
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
    private final AnswerRepository answerRepository;
    private final CrewRepository crewRepository;
    private final ApplyRepository applyRepository;

    public Long registerAnswer(AnswerDto answerDto, Long crewId) {
        log.info("질문 답변 세션입니다.");
        User user = (User) session.getAttribute("user");
        Crew crew = crewRepository.findCrewById(crewId);
        log.info(String.valueOf(crew));

        if (user != null) {
            List<Question> questionList = questionRepository.findQuestionByCrew(crew);
            List<Answer> answerList = new ArrayList<>();

            for (Question question : questionList) {
                Long questionId = question.getId();
                String answer = answerDto.getContent();

                Answer newAnswer = new Answer();
                newAnswer.setContent(answer);
                answerRepository.save(newAnswer);

                answerList.add(newAnswer);
                log.info("Question ID: {}, Answer: {}", questionId, newAnswer.getContent());
            }

            // 추가된 부분: Answer 리스트를 반환하도록 수정
            return answerList.stream()
                    .map(Answer::getId)
                    .findFirst()
                    .orElse(null);
        }

        return null;
    }

}



