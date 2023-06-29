package com.donghaeng.dev.repository;

import com.donghaeng.dev.domain.Crew;
import com.donghaeng.dev.domain.University;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CrewRepository extends JpaRepository<Crew, Long> {

    List<Crew> findAllByUniversity(University university);
}
