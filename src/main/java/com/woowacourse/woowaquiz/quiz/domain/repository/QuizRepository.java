package com.woowacourse.woowaquiz.quiz.domain.repository;

import com.woowacourse.woowaquiz.quiz.domain.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
}
