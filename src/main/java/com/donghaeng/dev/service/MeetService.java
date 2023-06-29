package com.donghaeng.dev.service;

import com.donghaeng.dev.domain.Meet;
import com.donghaeng.dev.dto.MeetResponseDto;
import com.donghaeng.dev.repository.MeetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MeetService {

    private final MeetRepository groupRepository;
    public Long create(String name) {
        return groupRepository.save(new Meet(name)).getId();
    }

    public List<MeetResponseDto> get() {
        return groupRepository.findAll()
                .stream()
                .map(MeetResponseDto::new)
                .collect(Collectors.toList());
    }
}
