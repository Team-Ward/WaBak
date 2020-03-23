package com.woowacourse.woowaquiz.quiz.domain;

import com.woowacourse.woowaquiz.common.LocalDateTimeUtils;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Arrays;

@Entity
@Table(name = "QUIZZES")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QUIZ_ID")
    private Long id;
    @Enumerated(value = EnumType.STRING)
    //TODO QuizInfo 라는 Value로 wrapping하자
    @Column(name = "QUIZ_TYPE")
    private QuizType quizType;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "QUESTION")
    private String question;
    @Column(name = "SOLUTION")
    private String solution;

    //TODO github 로그인으로 해결하자
    @Column(name = "AUTHOR")
    private String author;
    @Column(name = "ACTIVE")
    private boolean active;
    @Column(name = "CREATED_TIME")
    private LocalDateTime createdTime;

    @Builder
    public Quiz(String quizType, String title, String question, String solution, String author, boolean active, LocalDateTime createdTime) {
        this.quizType = QuizType.valueOf(quizType);
        validateSolution(question, solution);
        this.title = title;
        this.question = question;
        this.solution = solution;
        this.author = author;
        this.active = active;
        this.createdTime = LocalDateTimeUtils.now(createdTime);
    }

    //TODO enum 에게 위임하기
    private void validateSolution(String question, String solution) {
        if (this.quizType == QuizType.TYPING && !question.equals(solution)) {
            throw new IllegalArgumentException(String.format("questuion : %s, solutuin : %s%s TYPING 문제는 정답과 해답이 일치해야 합니다.", question, solution, System.lineSeparator()));
        }
        if (this.quizType == QuizType.OX && !Arrays.asList("O", "X").contains(solution)) {
            throw new IllegalArgumentException(String.format("solution : %s, OX 문제의 solution은 O, X만 허용됩니다.", solution));
        }
    }

    public boolean isCorrectAnswer(String answer) {
        return this.solution.equals(answer);
    }

    public void toggle() {
        this.active = !active;
    }

    public enum QuizType {TYPING, OX}
}
