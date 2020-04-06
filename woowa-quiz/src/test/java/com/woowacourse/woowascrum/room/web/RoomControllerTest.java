package com.woowacourse.woowascrum.room.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.woowacourse.woowascrum.quiz.domain.model.Quiz;
import com.woowacourse.woowascrum.quiz.domain.model.info.type.QuizType;
import com.woowacourse.woowascrum.quiz.domain.repository.QuizRepository;
import com.woowacourse.woowascrum.room.domain.model.Room;
import com.woowacourse.woowascrum.room.domain.repository.RoomRepository;
import com.woowacourse.woowascrum.room.service.dto.RoomBundleResponseDto;
import com.woowacourse.woowascrum.room.service.dto.RoomSaveDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@ExtendWith(SpringExtension.class)
class RoomControllerTest {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final String ROOM_API_DEFAULT_URL = "http://localhost:8080/api/v1/room";

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .build();
    }

    @AfterEach
    void tearDown() {
        roomRepository.deleteAll();
        quizRepository.deleteAll();
    }

    @DisplayName("퀴즈가 등록된 방 생성")
    @Test
    void saveRoom() throws Exception {
        //given
        Quiz quiz = Quiz.builder()
                .active(true)
                .author("비밥")
                .quizType(QuizType.TYPING.name())
                .question("타이핑 문제")
                .solution("타이핑 문제")
                .title("문제의 제목")
                .build();
        quiz = quizRepository.saveAndFlush(quiz);

        RoomSaveDto roomSaveDto = RoomSaveDto.builder()
                .name("방 이름")
                .author("방장 유저")
                .quizIds(Arrays.asList(quiz.getId()))
                .build();

        //when
        MvcResult mvcResult = mockMvc.perform(post(ROOM_API_DEFAULT_URL)
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(roomSaveDto)))
                .andExpect(status().isCreated())
                .andReturn();

        Long savedId = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), Long.class);
        Room room = roomRepository.findByIdFetch(savedId)
                .orElseThrow(IllegalArgumentException::new);

        //then
        assertThat(room.getName()).isEqualTo(roomSaveDto.getName());
        assertThat(room.getQuizzes()).hasSize(1);
    }

    @DisplayName("등록된 전체 방 목록 정보 가져오기")
    @Test
    public void findAll() throws Exception {
        //given
        RoomSaveDto roomSaveDto = RoomSaveDto.builder()
                .name("방 이름")
                .author("방장 유저")
                .build();

        roomRepository.saveAll(Arrays.asList(
                roomSaveDto.toEntity(Arrays.asList()),
                roomSaveDto.toEntity(Arrays.asList()),
                roomSaveDto.toEntity(Arrays.asList())));
        roomRepository.flush();

        //when
        MvcResult mvcResult = mockMvc.perform(get(ROOM_API_DEFAULT_URL)
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        RoomBundleResponseDto roomBundleResponseDto = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), RoomBundleResponseDto.class);

        //given
        assertThat(roomBundleResponseDto.size()).isEqualTo(3);
    }
}