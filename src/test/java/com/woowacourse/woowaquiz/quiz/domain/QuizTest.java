package com.woowacourse.woowaquiz.quiz.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class QuizTest {

    @DisplayName("퀴즈 활성화 비활성화 토글 기능")
    @ParameterizedTest
    @CsvSource(value = {"true,false", "false,true"})
    void toggle(boolean initial, boolean result) {
        //given
        Quiz quiz = Quiz.builder()
                .quizType("TYPING")
                .question("어찌, 내가 왕이 될 상인가?")
                .solution("어찌, 내가 왕이 될 상인가?")
                .active(initial)
                .build();

        //when
        quiz.toggle();

        //then
        assertThat(quiz.getQuizInfo().isActive()).isEqualTo(result);
    }
}