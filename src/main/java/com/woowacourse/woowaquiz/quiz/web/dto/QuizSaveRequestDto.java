package com.woowacourse.woowaquiz.quiz.web.dto;

import com.woowacourse.woowaquiz.quiz.domain.Quiz;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuizSaveRequestDto {
    private String quizType;
    private String question;
    private String solution;
    private String author;

    @Builder
    public QuizSaveRequestDto(String quizType, String question, String solution, String author) {
        this.quizType = quizType;
        this.question = question;
        this.solution = solution;
        this.author = author;
    }

    public Quiz toEntity() {
        return Quiz.builder()
                .quizType(this.quizType)
                .question(this.question)
                .solution(this.solution)
                .author(this.author)
                .active(false)
                .build();
    }
}
