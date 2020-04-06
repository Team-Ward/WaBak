package com.woowacourse.woowascrum.answer.service.dto;

import com.woowacourse.woowascrum.answer.domain.model.Answer;
import com.woowacourse.woowascrum.quiz.domain.model.Quiz;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AnswerSaveRequestDto {
    private String answer;
    private String author;

    private Long quizId;

    @Builder
    public AnswerSaveRequestDto(String answer, String author, Long quizId) {
        Objects.requireNonNull(author, "답변 작성자가 비어있습니다.");
        Objects.requireNonNull(quizId, "Quiz Id가 비어있습니다.");
        this.answer = answer;
        this.author = author;
        this.quizId = quizId;
    }

    public Answer toEntity(Quiz quiz) {
        return Answer.builder()
                .answer(this.answer)
                .author(this.author)
                .quiz(quiz)
                .build();
    }
}
