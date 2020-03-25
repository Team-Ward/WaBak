package com.woowacourse.woowaquiz.quiz.domain.info;

import com.woowacourse.woowaquiz.quiz.domain.info.type.QuizType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuizInfo {
    @Enumerated(value = EnumType.STRING)
    @Column(name = "QUIZ_TYPE")
    private QuizType quizType;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "QUESTION")
    private String question;

    @Column(name = "SOLUTION")
    private String solution;

    @Column(name = "ACTIVE")
    private boolean active;

    @Builder
    public QuizInfo(String quizType, String title, String question, String solution, boolean active) {
        this.quizType = QuizType.valueOf(quizType);
        this.quizType.validate(question, solution);
        this.title = title;
        this.question = question;
        this.solution = solution;
        this.active = active;
    }

    public boolean isCorrectAnswer(String answer) {
        return this.solution.equals(answer);
    }

    public void toggle() {
        this.active = !active;
    }
}
