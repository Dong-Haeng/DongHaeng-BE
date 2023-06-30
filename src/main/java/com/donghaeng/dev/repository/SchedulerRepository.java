package com.donghaeng.dev.repository;

import com.donghaeng.dev.domain.Scheduler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchedulerRepository extends JpaRepository<Scheduler, Long> {

    List<Scheduler> findByIdentifier(String identifier);
}
