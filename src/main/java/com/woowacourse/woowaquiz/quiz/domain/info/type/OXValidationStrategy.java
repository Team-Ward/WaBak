package com.woowacourse.woowaquiz.quiz.domain.info.type;

import java.util.Arrays;

class OXValidationStrategy implements QuizValidateStrategy {
    @Override
    public void validate(String question, String solution) {
        if (!Arrays.asList("O", "X").contains(solution)) {
            throw new IllegalArgumentException(String.format(
                    "solution : %s, OX 문제의 solution은 O, X만 허용됩니다.",
                    solution));
        }
    }
}
