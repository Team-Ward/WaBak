package com.woowacourse.woowaquiz.quiz.domain;

import com.woowacourse.woowaquiz.common.LocalDateTimeUtils;
import com.woowacourse.woowaquiz.quiz.domain.info.QuizInfo;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
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

    @Embedded
    private QuizInfo quizInfo;

    //TODO github 로그인으로 해결하자
    @Column(name = "AUTHOR")
    private String author;

    @Column(name = "CREATED_TIME")
    private LocalDateTime createdTime;

    @Builder
    public Quiz(String quizType, String title, String question, String solution, String author, boolean active, LocalDateTime createdTime) {
        this.quizInfo = QuizInfo.builder()
                .quizType(quizType)
                .title(title)
                .question(question)
                .solution(solution)
                .active(active)
                .build();
        this.author = author;
        this.createdTime = LocalDateTimeUtils.now(createdTime);
    }

    public boolean isCorrectAnswer(String answer) {
        return this.quizInfo.isCorrectAnswer(answer);
    }

    public void toggle() {
        quizInfo.toggle();
    }

}
