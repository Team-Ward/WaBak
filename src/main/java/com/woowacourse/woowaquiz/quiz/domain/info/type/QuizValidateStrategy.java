package com.woowacourse.woowaquiz.quiz.domain.info.type;

interface QuizValidateStrategy {
    void validate(String question, String solution);
}
