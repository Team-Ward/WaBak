package com.woowacourse.woowaquiz.answer.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.woowacourse.woowaquiz.answer.domain.model.Answer;
import com.woowacourse.woowaquiz.answer.domain.repository.AnswerRepository;
import com.woowacourse.woowaquiz.answer.service.dto.AnswerSaveRequestDto;
import com.woowacourse.woowaquiz.quiz.domain.model.Quiz;
import com.woowacourse.woowaquiz.quiz.domain.repository.QuizRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@ExtendWith(SpringExtension.class)
class AnswerControllerTest {
    private static final Gson gson = new Gson().newBuilder().setPrettyPrinting().create();
    private static final String ANSWER_BASE_URL = "/api/v1/answer";

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .build();
    }

    @AfterEach
    void tearDown() {
        answerRepository.deleteAll();
        quizRepository.deleteAll();
    }

    @DisplayName("문제에 대한 정답 저장")
    @Test
    void saveAnswer() throws Exception {
        //given
        Quiz quiz = Quiz.builder()
                .quizType("OX")
                .question("어찌, 내가 왕이 될 상인가?")
                .solution("O")
                .build();
        quiz = quizRepository.saveAndFlush(quiz);

        AnswerSaveRequestDto answerSaveRequestDto = AnswerSaveRequestDto.builder()
                .answer("사용자가 입력한 대답")
                .quizId(quiz.getId())
                .author("사용자이름")
                .build();

        //when
        MvcResult mvcResult = mockMvc.perform(post(ANSWER_BASE_URL)
                .content(gson.toJson(answerSaveRequestDto))
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();

        Long answerId = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), Long.class);
        List<Answer> all = answerRepository.findAll();
        answerRepository.flush();

        Answer savedAnswer = answerRepository.findById(answerId)
                .orElseThrow(NullPointerException::new);

        //then
        assertThat(all).hasSize(1);
        assertThat(all.get(0).getId()).isEqualTo(savedAnswer.getId());
        assertThat(all.get(0).getQuiz().getId()).isEqualTo(quiz.getId());
    }
}