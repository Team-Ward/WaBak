package com.woowacourse.woowaquiz.quiz.domain.model.info.type;

class TypingValidationStrategy implements QuizValidateStrategy {
    @Override
    public void validate(String question, String solution) {
        if (!question.equals(solution)) {
            throw new IllegalArgumentException(String.format(
                    "questuion : %s, solutuin : %s%s TYPING 문제는 정답과 해답이 일치해야 합니다.",
                    question, solution, System.lineSeparator()));
        }
    }
}
