package com.donghaeng.dev.controller;

import com.donghaeng.dev.domain.Crew;
import com.donghaeng.dev.dto.AnswerDto;
import com.donghaeng.dev.repository.CrewRepository;
import com.donghaeng.dev.service.AnswerService;
import com.donghaeng.dev.service.CrewService;
import com.donghaeng.dev.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/api")
@RequiredArgsConstructor
public class AnswerController {
    private final AnswerService AnswerService;
    private final QuestionService questionService;
    private final CrewRepository crewRepository;
    @PostMapping("/{crewId}/answer")
    public ResponseEntity<?>answer (@PathVariable Long crewId , @RequestBody List<AnswerDto> answerDto){
        AnswerService.registerAnswer(answerDto,crewId);
        return ResponseEntity.ok().build();
    }
}
