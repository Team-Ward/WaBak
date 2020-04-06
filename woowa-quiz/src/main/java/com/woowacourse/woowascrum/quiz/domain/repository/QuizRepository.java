package com.woowacourse.woowascrum.quiz.domain.repository;

import com.woowacourse.woowascrum.quiz.domain.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
}
