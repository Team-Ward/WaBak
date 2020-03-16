package com.woowacourse.woowaquiz.answer.repository;

import com.woowacourse.woowaquiz.answer.domain.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
