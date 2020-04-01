package com.woowacourse.woowaquiz.quiz.domain.model;

import com.woowacourse.woowaquiz.generic.BaseEntity;
import com.woowacourse.woowaquiz.quiz.domain.model.info.QuizInfo;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "QUIZZES")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AttributeOverride(name = "id", column = @Column(name = "QUIZ_ID"))
public class Quiz extends BaseEntity {

    @Embedded
    private QuizInfo quizInfo;

    //TODO github 로그인으로 해결하자
    @Column(name = "AUTHOR")
    private String author;

    @Builder
    public Quiz(String quizType, String title, String question, String solution, String author, boolean active, LocalDateTime createdTime) {
        super(createdTime);
        this.quizInfo = QuizInfo.builder()
                .quizType(quizType)
                .title(title)
                .question(question)
                .solution(solution)
                .active(active)
                .build();
        this.author = author;
    }

    public boolean isCorrectAnswer(String answer) {
        return this.quizInfo.isCorrectAnswer(answer);
    }

    public void toggle() {
        quizInfo.toggle();
    }

}
