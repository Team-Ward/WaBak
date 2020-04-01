package com.woowacourse.woowaquiz.answer.domain.repository;

import com.woowacourse.woowaquiz.answer.domain.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
