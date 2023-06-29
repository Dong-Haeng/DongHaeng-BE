package com.donghaeng.dev.service;

import com.donghaeng.dev.domain.*;
import com.donghaeng.dev.dto.AnswerDto;
import com.donghaeng.dev.dto.ApplyResDto;
import com.donghaeng.dev.dto.QuestionResDto;
import com.donghaeng.dev.repository.AnswerRepository;
import com.donghaeng.dev.repository.ApplyRepository;
import com.donghaeng.dev.repository.CrewRepository;
import com.donghaeng.dev.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ApplyService {
    private final ApplyRepository applyRepository;
    private final CrewRepository crewRepository;

    public ResponseEntity<?> apply(User user, Long crewId, List<String> answers) {
        Crew crew = crewRepository.findById(crewId).get();
        applyRepository.save(new Apply(user, crew, answers));
        return null;
    }
    public List<ApplyResDto> getApplicantsByCrew(Long crewId) {
        return applyRepository.findByCrewId(crewId)
                .stream()
                .map(apply -> new ApplyResDto(apply.getId(), apply.getUser().getId(), apply.getCrew().getId()))
                .collect(Collectors.toList());
    }

}
