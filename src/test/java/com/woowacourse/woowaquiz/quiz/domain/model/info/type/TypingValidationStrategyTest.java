package com.woowacourse.woowaquiz.quiz.domain.model.info.type;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TypingValidationStrategyTest {

    @DisplayName("타이핑 문제는 질문과 정답이 같아야한다.")
    @Test
    void validate() {
        String question = "질문";
        String solution = "질문하고다름";

        assertThatThrownBy(() -> QuizType.TYPING.validate(question, solution))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("questuion : %s, solutuin : %s%s TYPING 문제는 정답과 해답이 일치해야 합니다.", question, solution, System.lineSeparator());
    }
}