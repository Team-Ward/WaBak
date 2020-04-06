package com.woowacourse.woowascrum.quiz.domain.model.info.type;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OXValidationStrategyTest {

    @DisplayName("OX 문제는 답이 O 또는 X 여야만 한다.")
    @Test
    void validate() {
        String wrongAnswer = "Y";

        assertThatThrownBy(() -> QuizType.OX.validate("나는 배가 고프다", wrongAnswer))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("solution : %s, OX 문제의 solution은 O, X만 허용됩니다.", wrongAnswer);
    }
}