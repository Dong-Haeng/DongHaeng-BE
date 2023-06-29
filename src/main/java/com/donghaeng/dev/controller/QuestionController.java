package com.donghaeng.dev.controller;

import com.donghaeng.dev.domain.Crew;
import com.donghaeng.dev.domain.Question;
import com.donghaeng.dev.domain.User;
import com.donghaeng.dev.dto.QuestionDto;
import com.donghaeng.dev.dto.QuestionResDto;
import com.donghaeng.dev.repository.CrewRepository;
import com.donghaeng.dev.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/api")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;
    private final CrewRepository crewRepository;
    @PostMapping("/questions/{crewId}")
    public ResponseEntity<?> registerQuestions(@PathVariable Long crewId, @RequestBody List<QuestionDto> questionDtoList) {
        log.info(String.valueOf(questionDtoList));
        log.info(String.valueOf(crewId));
        Crew crew = crewRepository.findCrewById(crewId);

        log.info("동아리 존재여부 확인: {}", crew.getId());
        if (crew != null) {
            questionService.registerQuestion(questionDtoList, crew);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/questions/{crewId}")
    public ResponseEntity<?> getQuestion(@PathVariable Long crewId) {
        Optional<Crew> crewOptional = crewRepository.findById(crewId);

        if (crewOptional.isPresent()) {
            Crew crew = crewOptional.get();
            List<QuestionResDto> questionList = questionService.getQuestionsByCrew(crew);
            return ResponseEntity.ok(questionList);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
