package com.donghaeng.dev.repository;

import com.donghaeng.dev.domain.Apply;
import com.donghaeng.dev.domain.Crew;
import com.donghaeng.dev.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplyRepository extends JpaRepository<Apply,Long> {
    List<Apply> findByCrewId(Long crewId);
    Apply findApplyByUserAndCrew(User user, Crew crew);
}
