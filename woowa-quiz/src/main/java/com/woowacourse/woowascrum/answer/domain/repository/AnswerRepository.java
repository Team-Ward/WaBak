package com.woowacourse.woowascrum.answer.domain.repository;

import com.woowacourse.woowascrum.answer.domain.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
