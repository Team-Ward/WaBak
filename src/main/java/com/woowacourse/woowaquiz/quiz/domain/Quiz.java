package com.woowacourse.woowaquiz.quiz.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String quizType;
    private String question;
    private String solution;
    private String author;
    private boolean active;

    @Builder
    public Quiz(String quizType, String question, String solution, String author, boolean active) {
        this.quizType = quizType;
        this.question = question;
        this.solution = solution;
        this.author = author;
        this.active = active;
    }

    public void toggle() {
        this.active = !active;
    }
}
