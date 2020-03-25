package com.woowacourse.woowaquiz.answer.domain;

import com.woowacourse.woowaquiz.common.LocalDateTimeUtils;
import com.woowacourse.woowaquiz.quiz.domain.Quiz;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ANSWER_ID")
    private Long id;

    @Column(name = "ANSWER")
    private String answer;

    //TODO github 로그인으로 해결하자
    @Column(name = "AUTHOR")
    private String author;

    @Column(name = "CREATED_TIME")
    private LocalDateTime createdTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "QUIZ_ID")
    private Quiz quiz;

    @Builder
    public Answer(String answer, String author, Quiz quiz, LocalDateTime createdTime) {
        this.answer = answer;
        this.author = author;
        this.quiz = quiz;
        this.createdTime = LocalDateTimeUtils.now(createdTime);
    }

    public boolean isCorrect() {
        return this.quiz.isCorrectAnswer(this.answer);
    }
}
