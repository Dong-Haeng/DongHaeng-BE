package com.donghaeng.dev.repository;

import com.donghaeng.dev.domain.Crew;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CrewRepository extends JpaRepository<Crew, Long> {

}
