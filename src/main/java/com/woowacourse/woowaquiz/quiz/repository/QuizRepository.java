package com.woowacourse.woowaquiz.quiz.repository;

import com.woowacourse.woowaquiz.quiz.domain.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
}
