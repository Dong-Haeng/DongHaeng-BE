package com.donghaeng.dev.service;

import com.donghaeng.dev.domain.Crew;
import com.donghaeng.dev.domain.Division;
import com.donghaeng.dev.domain.University;
import com.donghaeng.dev.domain.User;
import com.donghaeng.dev.dto.CrewListResponseDto;
import com.donghaeng.dev.dto.CrewRegisterRequestDto;
import com.donghaeng.dev.repository.CrewRepository;
import com.donghaeng.dev.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class CrewService {

    private final UserRepository userRepository;
    private final CrewRepository crewRepository;

    public Long register(User user, CrewRegisterRequestDto crewRegisterDto) {
        Crew crew = new Crew(user, crewRegisterDto);
        return crewRepository.save(crew).getId();
    }

    public CrewListResponseDto findAllDesc(University university, Optional<Division> division, Optional<Boolean> isRecruiting) {
        if (division.isEmpty() && isRecruiting.isEmpty()) {
            List<Crew> crews = crewRepository.findAllByUniversity(university);
            return new CrewListResponseDto(crews);
        }
        else if (division.isEmpty()) {
            List<Crew> crews = crewRepository.findAllByUniversityFilterIsRecruiting(university, isRecruiting.get());
            return new CrewListResponseDto(crews);
        }

        List<Crew> crews = crewRepository.findAllByUniversityFilterByDivisionAndIsRecruiting(university, division.get(), isRecruiting.get());
        return new CrewListResponseDto(crews);
    }
}
