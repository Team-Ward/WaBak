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
    @Column(name = "QUIZ_TYPE")
    private QuizType quizType;
    @Column(name = "QUESTION")
    private String question;
    @Column(name = "SOLUTION")
    private String solution;
    @Column(name = "AUTHOR")
    private String author;
    @Column(name = "ACTIVE")
    private boolean active;
    @Column(name = "CREATED_TIME")
    private LocalDateTime createdTime;

    @Builder
    public Quiz(String quizType, String question, String solution, String author, boolean active, LocalDateTime createdTime) {
        this.quizType = QuizType.valueOf(quizType);
        this.question = question;
        this.solution = solution;
        this.author = author;
        this.active = active;
        this.createdTime = LocalDateTimeUtils.now(createdTime);
    }

    public boolean isCorrectAnswer(String answer) {
        return this.solution.equals(answer);
    }

    public void toggle() {
        this.active = !active;
    }

    public enum QuizType {TYPING, OX}
}
