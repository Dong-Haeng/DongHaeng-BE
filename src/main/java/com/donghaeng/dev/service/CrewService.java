package com.donghaeng.dev.service;

import com.donghaeng.dev.domain.Crew;
import com.donghaeng.dev.domain.User;
import com.donghaeng.dev.dto.CrewRegisterRequestDto;
import com.donghaeng.dev.repository.CrewRepository;
import com.donghaeng.dev.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
}
