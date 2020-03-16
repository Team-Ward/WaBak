package com.woowacourse.woowaquiz.answer.service.dto;

import com.woowacourse.woowaquiz.answer.domain.Answer;
import com.woowacourse.woowaquiz.quiz.domain.Quiz;
import com.woowacourse.woowaquiz.quiz.domain.QuizRepository;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AnswerSaveRequestDto {
    private String answer;
    private String author;

    private Long quizId;

    public AnswerSaveRequestDto(String answer, String author, Long quizId) {
        Objects.requireNonNull(author, "답변 작성자가 비어있습니다.");
        Objects.requireNonNull(quizId, "Quiz Id가 비어있습니다.");
        this.answer = answer;
        this.author = author;
        this.quizId = quizId;
    }

    public Answer toEntity(QuizRepository quizRepository) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new IllegalArgumentException(String.format("%d 존재하지 않는 퀴즈 번호입니다.", this.quizId)));
        return Answer.builder()
                .answer(this.answer)
                .author(this.author)
                .quiz(quiz)
                .build();
    }
}
