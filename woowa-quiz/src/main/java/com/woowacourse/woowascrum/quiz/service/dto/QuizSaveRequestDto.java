package com.woowacourse.woowascrum.quiz.service.dto;

import com.woowacourse.woowascrum.quiz.domain.model.Quiz;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuizSaveRequestDto {
    private String quizType;
    private String title;
    private String question;
    private String solution;
    private String author;

    @Builder
    public QuizSaveRequestDto(String quizType, String title, String question, String solution, String author) {
        this.quizType = quizType;
        this.question = question;
        this.solution = solution;
        this.author = author;
        this.title = title;
    }

    public Quiz toEntity() {
        return Quiz.builder()
                .quizType(this.quizType)
                .title(this.title)
                .question(this.question)
                .solution(this.solution)
                .author(this.author)
                .active(false)
                .build();
    }
}
