package com.donghaeng.dev.repository;

import com.donghaeng.dev.domain.Crew;
import com.donghaeng.dev.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question,Long> {
    List<Question> findQuestionByCrew(Crew crew);
}
