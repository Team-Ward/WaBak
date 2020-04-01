package com.woowacourse.woowaquiz.quiz.domain.model.info.type;

public enum QuizType {
    TYPING(new TypingValidationStrategy()),
    OX(new OXValidationStrategy());

    private final QuizValidateStrategy quizValidateStrategy;

    QuizType(QuizValidateStrategy quizValidateStrategy) {
        this.quizValidateStrategy = quizValidateStrategy;
    }

    public void validate(String question, String solution) {
        this.quizValidateStrategy.validate(question, solution);
    }
}
