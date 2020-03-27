package com.woowacourse.woowaquiz.answer.domain;

import com.woowacourse.woowaquiz.generic.BaseEntity;
import com.woowacourse.woowaquiz.quiz.domain.Quiz;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AttributeOverride(name = "id", column = @Column(name = "ANSWER_ID"))
public class Answer extends BaseEntity {

    @Column(name = "ANSWER")
    private String answer;

    //TODO github 로그인으로 해결하자
    @Column(name = "AUTHOR")
    private String author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "QUIZ_ID")
    private Quiz quiz;

    @Builder
    public Answer(String answer, String author, Quiz quiz, LocalDateTime createdTime) {
        super(createdTime);
        this.answer = answer;
        this.author = author;
        this.quiz = quiz;
    }

    public boolean isCorrect() {
        return this.quiz.isCorrectAnswer(this.answer);
    }
}
