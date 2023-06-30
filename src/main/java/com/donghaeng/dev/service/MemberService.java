package com.donghaeng.dev.service;

import com.donghaeng.dev.domain.Meet;
import com.donghaeng.dev.domain.Member;
import com.donghaeng.dev.domain.Scheduler;
import com.donghaeng.dev.repository.MeetRepository;
import com.donghaeng.dev.repository.MemberRepository;
import com.donghaeng.dev.repository.SchedulerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final MeetRepository meetRepository;
    private final SchedulerRepository schedulerRepository;

    public Long create(Long groupId, String name, String identifier) {
        Meet meet = meetRepository.findById(groupId).get();
        List<Scheduler> schedulers = schedulerRepository.findByIdentifier(identifier);

        return memberRepository.save(new Member(meet, name, schedulers)).getId();
    }
}
