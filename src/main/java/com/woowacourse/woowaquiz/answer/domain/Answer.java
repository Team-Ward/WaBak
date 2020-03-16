package com.woowacourse.woowaquiz.answer.domain;

import com.woowacourse.woowaquiz.quiz.domain.Quiz;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String answer;
    private String author;
    private LocalDateTime createdTime;

    @ManyToOne(fetch = FetchType.LAZY)
    private Quiz quiz;

    @Builder
    public Answer(String answer, String author, Quiz quiz, LocalDateTime createdTime) {
        this.answer = answer;
        this.author = author;
        this.quiz = quiz;
        this.createdTime = createdTime;
    }

    public boolean isCorrect() {
        return this.quiz.isCorrectAnswer(this.answer);
    }
}
