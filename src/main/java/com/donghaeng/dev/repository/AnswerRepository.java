package com.donghaeng.dev.repository;

import com.donghaeng.dev.domain.Apply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Apply,Long> {
}
