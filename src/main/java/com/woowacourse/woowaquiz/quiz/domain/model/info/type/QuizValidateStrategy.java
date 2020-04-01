package com.woowacourse.woowaquiz.quiz.domain.model.info.type;

interface QuizValidateStrategy {
    void validate(String question, String solution);
}
