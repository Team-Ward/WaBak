package com.woowacourse.woowascrum.answer.domain;

import com.woowacourse.woowascrum.answer.domain.model.Answer;
import com.woowacourse.woowascrum.quiz.domain.model.Quiz;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class AnswerTest {

    @DisplayName("정답 입력시 퀴즈와 답을 비교하여 맞는지 확인")
    @ParameterizedTest
    @CsvSource(value = {"어찌, 내가 왕이 될 상인가?|true", "어짐ㄴ애가와이될상인가?|false"}, delimiter = '|')
    void isCorrect(String inputAnswer, boolean result) {
        //given
        String questionAndSolution = "어찌, 내가 왕이 될 상인가?";

        Quiz quiz = Quiz.builder()
                .quizType("TYPING")
                .active(true)
                .question(questionAndSolution)
                .solution(questionAndSolution)
                .build();

        Answer answer = Answer.builder()
                .answer(inputAnswer)
                .quiz(quiz)
                .build();

        //when
        boolean actual = answer.isCorrect();

        //then
        assertThat(actual).isEqualTo(result);
    }
}