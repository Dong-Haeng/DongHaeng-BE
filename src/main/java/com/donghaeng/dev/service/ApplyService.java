package com.donghaeng.dev.service;

import com.donghaeng.dev.domain.*;
import com.donghaeng.dev.dto.ApplyResDto;
import com.donghaeng.dev.repository.ApplyRepository;
import com.donghaeng.dev.repository.CrewRepository;
import com.donghaeng.dev.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ApplyService {
    private final ApplyRepository applyRepository;
    private final CrewRepository crewRepository;
    private final UserRepository userRepository;

    public ResponseEntity<?> apply(Long userId, Long crewId, List<String> answers) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("user id 없음"));
        Crew crew = crewRepository.findById(crewId).orElseThrow(() -> new IllegalArgumentException("crew id 없음"));
        applyRepository.save(new Apply(user, crew, answers));
        return null;
    }
    public List<ApplyResDto> getApplicantsByCrew(Long crewId) {
        return applyRepository.findByCrewId(crewId)
                .stream()
                .map(apply -> new ApplyResDto(apply.getId(), apply.getId(), apply.getCrew().getId()))
                .collect(Collectors.toList());
    }

}
